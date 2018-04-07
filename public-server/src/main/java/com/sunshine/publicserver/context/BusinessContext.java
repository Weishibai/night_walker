package com.sunshine.publicserver.context;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.common.vo.ExchangeInfo;
import com.sunshine.common.vo.InfoCenterData;
import com.sunshine.publicserver.constants.Con;
import com.sunshine.publicserver.service.basic.BaseInfoService;
import com.sunshine.publicserver.utils.Base64Util;
import com.sunshine.publicserver.utils.HttpClientUtils;
import com.sunshine.publicserver.utils.SpringWrapper;
import com.sunshine.service.pojo.FareSearchReq;
import com.sunshine.service.pojo.FareSearchResponse;
import com.sunshine.service.pojo.FlightCombination;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class BusinessContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessContext.class);


    public static final Splitter DATA_SPLITTER = Splitter.on(CharMatcher.anyOf("~")).trimResults().omitEmptyStrings();

    public static final Splitter FAREBISIS_SPLITTER = Splitter.on(CharMatcher.anyOf("^")).trimResults().omitEmptyStrings();

    private static final String REQUEST_ID = "requestId";

    public static void setRequestId(String requestId) {
        MDC.put(REQUEST_ID, requestId);
    }

    public static String requestId() {
        return MDC.get(REQUEST_ID);
    }

    public static void releaseResource() {
        MDC.remove(REQUEST_ID);
    }

    private static final LoadingCache<FareSearchReq, FareSearchResponse> fareInfoCache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(30000)
            .build(new CacheLoader<FareSearchReq, FareSearchResponse>() {
        @Override
        public FareSearchResponse load(FareSearchReq key) throws Exception {
            if (null == key) {
                LOGGER.debug("key is null");
                return null;
            }

            String httpResp = HttpClientUtils.post(key.getUrl(), JsonUtil.writeString(key), HttpClientUtils.DEFAULT_HEADER, 30000, 30000);

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
            LOGGER.debug("query fare size {} result {} for key {}", fareSearchResponse.getFlightCombinations().size(), fareSearchResponse, key);
            return fareSearchResponse;
        }
    });

    private static final LoadingCache<ExchangeInfo, List<ExchangeInfo>> exchangeCache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build(new CacheLoader<ExchangeInfo, List<ExchangeInfo>>() {
        @Override
        public List<ExchangeInfo> load(ExchangeInfo key) throws Exception {
            if (null == key) {
                LOGGER.debug("key is null");
                return Collections.emptyList();
            }

            BaseInfoService baseInfoService = SpringWrapper.getBean(BaseInfoService.class);
            List<ExchangeInfo> exchangeInfos = baseInfoService.queryExchange(key.getSrcCurrency(), key.getDstCurrency());
            LOGGER.debug("query exchange result {} for key {}", exchangeInfos, key);
            return exchangeInfos;
        }
    });

    private static final LoadingCache<InfoCenterData, List<InfoCenterData>> infoCenterCache = CacheBuilder.newBuilder().build(new CacheLoader<InfoCenterData, List<InfoCenterData>>() {
        @Override
        public List<InfoCenterData> load(InfoCenterData key) throws Exception {
            if (null == key) {
                LOGGER.debug("key is null");
                return Collections.emptyList();
            }
            BaseInfoService baseInfoService = SpringWrapper.getBean(BaseInfoService.class);

            List<InfoCenterData> datas = baseInfoService.queryInfoCenter(key.getAirportCode(), key.getCityCode());
            LOGGER.debug("info center result {} for key {}", datas, key);
            if (CollectionUtils.isEmpty(datas)) {
                return Collections.emptyList();
            }
            return datas;
        }
    });

    public static List<InfoCenterData> queryInfocenter(InfoCenterData key) {
        try {
            return infoCenterCache.get(key);
        } catch (ExecutionException e) {
            return Collections.emptyList();
        }
    }

    public static List<ExchangeInfo> queryExchange(String srcCur) {
        try {
            return exchangeCache.get(ExchangeInfo.make(srcCur, "CNY"));
        } catch (ExecutionException e) {
            return Collections.emptyList();
        }
    }

    public static FareSearchResponse queryFareInfo(FareSearchReq req) {
        try {
            return fareInfoCache.get(req);
        } catch (ExecutionException e) {
            return new FareSearchResponse();
        }
    }


    public static void main(String[] args) {
        String dd = "EVwHkG+gHmEYUhyQznBFbAkIjrGxx3Q0Fa4kQB7EnpYskSU15C1nIwdr06spodMFBxKKscURXwb+j7Dr6qhgMfhH7eXgRxede6C3cBhy2gcWYGQjDBzyDE5SExEAtGmgMmWV4i044esYm\n" +
                "L4DJmTYZcG6XgGYuAyvY+N6HTVG8/lcqqbFtxStCzV42lEiJkQ5";
        System.out.println(Base64Util.wrapDecode(dd, Con.DATA_TOKEN));


        String gg = "{\"cid\":\"fliggy\",\"tripType\":1,\"sessionId\":\"fliggy_1516719768774_1\",\"routing\":{\"data\":\"EVwHkG+gHmEYUhyQznBFbOZn1msnSnWRT/YnVIa2Lf8qJVkBtTkvyhRfCSWNvK3Fwd828ygb1NEUJwQjYeXQyZcFTaxrEmDyJeQZi3HqZz+/kzDfyGlk8Om4frOtvUPnw/uwwYNtHqdouMrWAi2c4S/c2tAxqZLe9vKhaEkq0s0=\",\"fromSegments\":[{\"carrier\":\"SQ\",\"flightNumber\":\"SQ865\",\"depAirport\":\"HKG\",\"depTime\":\"201802101850\",\"arrAirport\":\"SIN\",\"arrTime\":\"201802102250\",\"codeShare\":false,\"cabin\":\"B\",\"aircraftCode\":\"77W\"}],\"retSegments\":[]},\"passengers\":[{\"name\":\"SUN/PENG\",\"ageType\":0,\"birthday\":\"19950201\",\"gender\":\"M\",\"cardNum\":\"E12345678\",\"cardType\":\"PP\",\"cardIssuePlace\":\"CN\",\"cardExpired\":\"20270210\",\"nationality\":\"CN\"}],\"contact\":{\"name\":\"SUNPENG\"},\"passengerAuxes\":[]}";


        String text = "{\"cid\":\"fliggy\",\"tripType\":1,\"sessionId\":\"1234567\",\"routing\":{\"data\":\"ao2HcVmoEn7s+NAp55uHG7/mfHPq1/DDX5NAMau3u0dh1I06k48CblP45tUQWaCEUZufbTIl/nOwW5G9cc7tsnjD8GmrskRQRhdcSbAHwFny5PidwsZEg39NB87gSQcG\",\"fromSegments\":[{\"carrier\":\"CX\",\"flightNumber\":\"CX5906\",\"depAirport\":\"HKG\",\"depTime\":\"201801081900\",\"arrAirport\":\"PEK\",\"arrTime\":\"201801082210\",\"codeShare\":false,\"cabin\":\"Y\",\"aircraftCode\":\"333\"}],\"retSegments\":[]},\"passengers\":[{\"name\":\"peng/sun\",\"ageType\":0,\"birthday\":\"19950201\",\"gender\":\"M\",\"cardNum\":\"E12345678\",\"cardType\":\"PP\",\"cardIssuePlace\":\"CN\",\"cardExpired\":\"20200201\",\"nationality\":\"CN\"}],\"contact\":{\"name\":\"sunpeng\"},\"passengerAuxes\":[]}";
//        String s = Base64Util.wrapEncode(text, Con.DATA_TOKEN);
//        System.out.println(s);

//        String text = "{\"cid\":\"fliggy\",\"tripType\":1,\"sessionId\":\"12313323\",\"orderNo\":\"123456789\",\"pnrCode\":\"GG9B1B\",\"routing\":{\"data\":\"ao2HcVmoEn7s+NAp55uHG7/mfHPq1/DDX5NAMau3u0dh1I06k48CblP45tUQWaCEUZufbTIl/nOwW5G9cc7tsnjD8GmrskRQRhdcSbAHwFny5PidwsZEg39NB87gSQcG\",\"fromSegments\":[{\"carrier\":\"CX\",\"flightNumber\":\"CX5906\",\"depAirport\":\"HKG\",\"depTime\":\"201801081900\",\"arrAirport\":\"PEK\",\"arrTime\":\"201801082210\",\"codeShare\":false,\"cabin\":\"Y\",\"aircraftCode\":\"333\"}],\"retSegments\":[]}}";
        String s = Base64Util.wrapEncode(gg, Con.DATA_TOKEN);
        System.out.println(s);
    }

}
