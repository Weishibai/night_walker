package com.sunshine.publicserver.service.search;

import com.google.common.collect.*;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.common.vo.*;
import com.sunshine.publicserver.constants.Con;
import com.sunshine.publicserver.context.BusinessContext;
import com.sunshine.publicserver.context.MonitorContext;
import com.sunshine.publicserver.enums.Platform;
import com.sunshine.publicserver.fliggy.vo.FlightType;
import com.sunshine.publicserver.fliggy.vo.RoutingElement;
import com.sunshine.publicserver.fliggy.vo.SearchRequest;
import com.sunshine.publicserver.fliggy.vo.SearchResponse;
import com.sunshine.publicserver.service.IMarkupService;
import com.sunshine.publicserver.service.basic.BaseInfoService;
import com.sunshine.publicserver.service.cache.PriceCacheManager;
import com.sunshine.publicserver.service.impl.AdjustPriceService;
import com.sunshine.publicserver.service.strategy.WhiteListStrategyService;
import com.sunshine.publicserver.utils.HttpClientUtils;
import com.sunshine.publicserver.utils.TransferUtil;
import com.sunshine.publicserver.vo.MarkupResponse;
import com.sunshine.service.pojo.FareSearchReq;
import com.sunshine.service.pojo.FareSearchResponse;
import com.sunshine.service.pojo.FlightCombination;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class FliggySearchService implements ISearchService<SearchRequest, SearchResponse> {

    private static Logger LOGGER = LoggerFactory.getLogger(FliggySearchService.class);

    @Value("${fareSearchUrl}")
    private String fareSearchUrl;

    @Resource
    private WhiteListStrategyService whiteListStrategyService;

    @Resource
    private IMarkupService markupService;

    @Resource
    private BaseInfoService baseInfoService;

    @Resource
    private AdjustPriceService adjustPriceService;

    @Resource
    private PriceCacheManager priceCacheManager;

    private final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyyMMdd");

    @Override
    public Platform platform() {
        return Platform.FLIGGY;
    }

    private SearchResponse validate(SearchRequest request) {
        if (null == request) {
            return SearchResponse.make(1, "request is null");
        }

        if (StringUtils.isBlank(request.getCid())) {
            return SearchResponse.make(1, "cid is blank");
        }

        if (StringUtils.isBlank(request.getFromCity()) || StringUtils.isBlank(request.getToCity())) {
            return SearchResponse.make(1, "city is blank");
        }

        if (null == FlightType.codeOf(request.getTripType())) {
            return SearchResponse.make(1, "illegal flight type");
        }

        if ((FlightType.ONE_WAY.code() == request.getTripType() || FlightType.MULTI_TRIP.code() == request.getTripType())
                && StringUtils.isBlank(request.getFromDate())) {
            return SearchResponse.make(1, "from date cannot be blank");
        } else if ((FlightType.ROUND_TRIP.code() == request.getTripType())
                && (StringUtils.isBlank(request.getFromDate()) || StringUtils.isBlank(request.getRetDate()))) {
            return SearchResponse.make(1, "search date cannot be blank");
        }
        return null;
    }

    private Multimap<GDS, String> fetchTargetBranch(List<WhitelistPolicy> whiteLists) {
        final Multimap<GDS, String> multimap = ArrayListMultimap.create();

        for (WhitelistPolicy whiteList : whiteLists) {
            if (null != whiteList.getGds() && StringUtils.isNotBlank(whiteList.getTargetBranch())) {
                if (!multimap.containsValue(whiteList.getTargetBranch())) {
                    multimap.put(whiteList.getGds(), whiteList.getTargetBranch());
                }
            }
        }
        return multimap;
    }

    @Override
    public SearchResponse search(SearchRequest request) {
        SearchResponse resp = validate(request);
        if (resp != null) {
            return resp;
        }

        long st = System.currentTimeMillis();

        /* 获取机场-城市-国家映射 */
        List<InfoCenterData> fromFlightData = BusinessContext.queryInfocenter(InfoCenterData.make("", request.getFromCity(), ""));
        List<InfoCenterData> toFlightData = BusinessContext.queryInfocenter(InfoCenterData.make("", request.getToCity(), ""));
        st = MonitorContext.addRecord("query info center", st);

        if (CollectionUtils.isEmpty(fromFlightData) || CollectionUtils.isEmpty(toFlightData)) {
            return SearchResponse.make(1, "cannot fetch base info");
        }

        /* 获取白名单 */
        List<WhitelistPolicy> whiteLists = whiteListStrategyService.query(fromFlightData.get(0).getCountryCode(), toFlightData.get(0).getCountryCode());
        st = MonitorContext.addRecord("query whiteLists", st);

        if (CollectionUtils.isEmpty(whiteLists)) {
            return SearchResponse.make(2, "航线不在报价白名单");
        }

        /* gds - target branch */
        Multimap<GDS, String> targetBranchMap = fetchTargetBranch(whiteLists);

        /* query quota */
        boolean quotaResult = false;
        for (GDS gds : targetBranchMap.keySet()) {
            Map<String, QuotaPolicy> result = baseInfoService.queryQuota(gds, (List<String>) targetBranchMap.get(gds));
            if (MapUtils.isEmpty(result)) {
                return SearchResponse.make(2, "quota is insufficient");
            }

            for (Map.Entry<String, QuotaPolicy> entry : result.entrySet()) {
                if (entry.getValue() == null) {
                    continue;
                }

                boolean valid = baseInfoService.validAndUpdateTargetBranch(entry.getValue());
                LOGGER.debug("targetBranch {} is valid {}", entry.getKey(), valid);
                quotaResult |= valid;
            }

            /* async update quota */
            baseInfoService.updateQuota(Lists.newArrayList(result.values()));
        }

        if (!quotaResult) {
            return SearchResponse.make(2, "quota is insufficient");
        }
        st = MonitorContext.addRecord("query and update quota", st);

        List<RoutingElement> result = Lists.newArrayList();
        for (Map.Entry<GDS, String> entry : targetBranchMap.entries()) {
            Collection<String> targetBranches = targetBranchMap.get(entry.getKey());
            String targetBranch = Iterables.getFirst(targetBranches, "");
            if (StringUtils.isBlank(targetBranch)) {
                continue;
            }

            FareSearchReq req = new FareSearchReq();
            req.setFlightType(request.getTripType());
            req.setFromCity(request.getFromCity());
            req.setToCity(request.getToCity());
            req.setTargetBranch(entry.getKey() == GDS.Galileo ? Con.G_TB : Con.P_TB);
            req.setPcc(targetBranch);
            req.setProviderCode(entry.getKey() == GDS.Galileo ? "1G" : "1P");
            req.setFromDate(FORMATTER.parseDateTime(request.getFromDate()));
            if (request.getTripType() == 2) {
                req.setToDate(FORMATTER.parseDateTime(request.getRetDate()));
            }
            req.setUrl(fareSearchUrl);
            req.setAdultNum(1);
            req.setChildNum(0);

            /* 暂时请求一次 使用一个targetBranches */
            FareSearchResponse fareSearchResponse = priceCacheManager.fetchFare(req);
            st = MonitorContext.addRecord("query fare url", st);
            if (null == fareSearchResponse || CollectionUtils.isEmpty(fareSearchResponse.getFlightCombinations())) {
                continue;
            }

            /* markup 调用 */
            Map<Integer, MarkupResponse> markups = markupService.markup(fareSearchResponse);
            FareSearchResponse fareSearchResp = markupService.addPrice(fareSearchResponse, markups);
            st = MonitorContext.addRecord("mark up fare", st);
            String cacheKey = priceCacheManager.buildFareKey(req);
            List<RoutingElement> routingElements = TransferUtil.searchTransform(fareSearchResp, cacheKey);

            /* 汇率转换 */
            String currency = Iterables.getFirst(fareSearchResponse.getFlightCombinations(), null).getCurrency();
            baseInfoService.exchange(routingElements, currency);

            /* 调价 */
            adjustPriceService.adjustPrice(routingElements);

            /* 结果转换成飞猪所需 */
            fareSearchResp.setTbCode(targetBranch);
            result.addAll(routingElements);
        }

        if (CollectionUtils.isEmpty(result)) {
            return SearchResponse.make(999, "no fare");
        } else {
            SearchResponse response = SearchResponse.make(0, "success");
            response.setRoutings(result);
            return response;
        }
    }
}
