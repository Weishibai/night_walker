package com.sunshine.publicserver.service.cache;

import com.google.common.collect.Lists;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.publicserver.utils.HotDeployUtil;
import com.sunshine.publicserver.utils.HttpClientUtils;
import com.sunshine.service.pojo.FareSearchReq;
import com.sunshine.service.pojo.FareSearchResponse;
import com.sunshine.service.pojo.FlightCombination;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class PriceCacheManager {

    private static Logger LOGGER = LoggerFactory.getLogger(PriceCacheManager.class);

    @Resource
    private DistributeCacheService distributeCacheService;

    private static final String COUNTER_PREFIX = "add_";

    public String buildFareKey(FareSearchReq req) {
        if (null == req) {
            return StringUtils.EMPTY;
        }

        String key = req.getFromCity() + "/" + req.getToCity() + "/" + req.getFlightType();
        key += req.getFromDate().toString("yyyy-MM-dd");
        if (2 == req.getFlightType()) {
            key += req.getToDate().toString("yyyy-MM-dd");
        }
        LOGGER.debug("build fare cache key: {}", key);
        return key;
    }

    public FareSearchResponse fetchFare(FareSearchReq req) {
        if (null == req) {
            return null;
        }

        String key = buildFareKey(req);
        FareSearchResponse cacheFare = distributeCacheService.getCache(key, FareSearchResponse.class);
        if (null != cacheFare) {
            LOGGER.debug("fetch fare from cache");
            return cacheFare;
        }

        String httpResp = HttpClientUtils.post(req.getUrl(), JsonUtil.writeString(req), HttpClientUtils.DEFAULT_HEADER, 30000, 30000);

        /* 请求底层报价 */
        @SuppressWarnings("unchecked")
        HttpResponse<String> response = JsonUtil.readValue(httpResp, HttpResponse.class);
        if (null == response || !response.isSuccess() || null == response.getData()) {
            LOGGER.warn("未搜索到报价: {}", response);
            return new FareSearchResponse();
        }

        FareSearchResponse fareSearchResponse = null;
        try {
            fareSearchResponse = JsonUtil.OBJECT_MAPPER.readValue(response.getData(), FareSearchResponse.class);
        } catch (IOException e) {
            LOGGER.error("parse fare error: ", e);
        }

        /* 剪枝 */
        if (null != fareSearchResponse && org.apache.commons.collections.CollectionUtils.size(fareSearchResponse.getFlightCombinations()) > 30) {
            List<FlightCombination> preList = Lists.newArrayList();
            List<FlightCombination> origin = fareSearchResponse.getFlightCombinations();
            for (int i = 0; i < 30; i ++) {
                preList.add(origin.get(i));
            }
            fareSearchResponse.setFlightCombinations(preList);
        }
        int cacheTime = NumberUtils.toInt(HotDeployUtil.get("priceCacheTime"), 1200);
        distributeCacheService.putCache(key, fareSearchResponse, cacheTime);
        LOGGER.debug("query fare size {} result {} for key {}", fareSearchResponse.getFlightCombinations().size(), fareSearchResponse, key);
        return fareSearchResponse;
    }

    public void refreshIfMaintains(String key) {
        if (StringUtils.isBlank(key)) {
            return;
        }

        long count = distributeCacheService.add(COUNTER_PREFIX + key);
        if (count >= 10) {
            LOGGER.warn("target price {} failed exceed {} times to refresh", key, 10);
            boolean res = distributeCacheService.refresh(key);
            boolean counter = distributeCacheService.refresh(COUNTER_PREFIX + key);
            LOGGER.info("refresh cache fare: {} counter {}", res, counter);
        }
    }

}
