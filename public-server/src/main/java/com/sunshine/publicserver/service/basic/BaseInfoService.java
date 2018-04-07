package com.sunshine.publicserver.service.basic;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.common.vo.ExchangeInfo;
import com.sunshine.common.vo.GDS;
import com.sunshine.common.vo.InfoCenterData;
import com.sunshine.common.vo.QuotaPolicy;
import com.sunshine.publicserver.context.BusinessContext;
import com.sunshine.publicserver.context.MonitorContext;
import com.sunshine.publicserver.fliggy.vo.RoutingElement;
import com.sunshine.publicserver.utils.HttpClientUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class BaseInfoService {

    private static Logger LOGGER = LoggerFactory.getLogger(BaseInfoService.class);

    private static final String DEFAULT_CURRENCY = "CNY";

    private ListeningExecutorService threadPool = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    @Value("${exchangeUrl}")
    private String exchangeUrl;

    @Value("${infocenterUrl}")
    private String infoCenterUrl;

    @Value("${quotaQueryUrl}")
    private String quotaQueryUrl;

    @Value("${quotaUpdateUrl}")
    private String quotaUpdateUrl;

    @SuppressWarnings("unchecked")
    public List<ExchangeInfo> queryExchange(String srcCurrency, String dstCurrency) {
        ExchangeInfo exchangeInfo = ExchangeInfo.make(srcCurrency, dstCurrency);
        String resp = HttpClientUtils.post(exchangeUrl, JsonUtil.writeString(exchangeInfo), HttpClientUtils.DEFAULT_HEADER, 2000, 3000);
        LOGGER.debug("query exchange result: {}", resp);
        HttpResponse<String> httpResponse = (HttpResponse<String>) JsonUtil.readValue(resp, HttpResponse.class);
        if (httpResponse == null) {
            return Collections.emptyList();
        }

        try {
            return JsonUtil.OBJECT_MAPPER.readValue(httpResponse.getData(), new TypeReference<List<ExchangeInfo>>(){});
        } catch (IOException e) {
            return Collections.emptyList();
        }

    }

    @SuppressWarnings("unchecked")
    public List<InfoCenterData> queryInfoCenter(String airportCode, String cityCode) {
        InfoCenterData infoCenterData = InfoCenterData.make(airportCode, cityCode, "");
        String resp = HttpClientUtils.post(infoCenterUrl, JsonUtil.writeString(infoCenterData), HttpClientUtils.DEFAULT_HEADER, 3500, 3500);
        LOGGER.debug("query info center result: {}", resp);
        HttpResponse<String> httpResponse = (HttpResponse<String>) JsonUtil.readValue(resp, HttpResponse.class);
        if (httpResponse == null) {
            return Collections.emptyList();
        }

        try {
            return JsonUtil.OBJECT_MAPPER.readValue(httpResponse.getData(), new TypeReference<List<InfoCenterData>>(){});
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public void exchange(List<RoutingElement> routingElements, final String currency) {
        if (CollectionUtils.isEmpty(routingElements)) {
            return;
        }

        final CountDownLatch latch = new CountDownLatch(routingElements.size());
        long st = System.currentTimeMillis();
        for (final RoutingElement routingElement : routingElements) {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        double adultPrice = routingElement.getAdultPrice();
                        double childPrice = routingElement.getChildPrice();
                        double adultTax = routingElement.getAdultTax();
                        double childTax = routingElement.getChildTax();

                        List<ExchangeInfo> exchangeInfos = BusinessContext.queryExchange(currency);
                        ExchangeInfo targetExchange = exchangeInfos.get(0);
                        double rate = targetExchange.getRate();
                        routingElement.setAdultPrice((int) (adultPrice * rate));
                        routingElement.setAdultTax((int) (adultTax * rate));
                        routingElement.setChildPrice((int) (childPrice * rate));
                        routingElement.setChildTax((int) (childTax * rate));
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        try {
            latch.await(3, TimeUnit.SECONDS);
            MonitorContext.addRecord("query exchange total", st);
        } catch (Throwable e) {
            LOGGER.error("wait exchange error: ", e);
        }
    }

    public Map<String, QuotaPolicy> queryQuota(GDS gds, List<String> targetBranches) {
        if (gds == null || CollectionUtils.isEmpty(targetBranches)) {
            return Collections.emptyMap();
        }

        Map<String, QuotaPolicy> resultMap = Maps.newHashMap();
        for (String targetBranch : targetBranches) {
            QuotaPolicy quotaPolicy = new QuotaPolicy();
            quotaPolicy.setGds(gds);
            quotaPolicy.setStatus(1);
            quotaPolicy.setTargetBranch(targetBranch);

            String resp = HttpClientUtils.post(quotaQueryUrl, JsonUtil.writeString(quotaPolicy), HttpClientUtils.DEFAULT_HEADER, 2000, 2000);
            LOGGER.debug("query quota resp: {}", resp);

            @SuppressWarnings("unchecked")
            HttpResponse<String> httpResponse = JsonUtil.readValue(resp, HttpResponse.class);
            if (httpResponse == null || !httpResponse.isSuccess() || StringUtils.isBlank(httpResponse.getData())) {
                resultMap.put(targetBranch, null);
                continue;
            }

            List<QuotaPolicy> quotaPolicies = null;
            try {
                quotaPolicies = JsonUtil.OBJECT_MAPPER.readValue(httpResponse.getData(), new TypeReference<List<QuotaPolicy>>(){});
            } catch (IOException e) {}
            resultMap.put(targetBranch, Iterables.getFirst(quotaPolicies, null));
        }
        return resultMap;
    }

    public boolean validAndUpdateTargetBranch(QuotaPolicy quotaPolicy) {
        if (quotaPolicy == null) {
            return false;
        }

        if (quotaPolicy.getLimit() < quotaPolicy.getSurplus()) {
            quotaPolicy.setStatus(0);
            return false;
        }

        if (quotaPolicy.getStatus() != 1) {
            return false;
        }

        quotaPolicy.setSurplus(quotaPolicy.getSurplus() - 1);
        if (quotaPolicy.getSurplus() <= 0) {
            quotaPolicy.setStatus(0);
            return false;
        }
        return true;
    }

    public void updateQuota(final List<QuotaPolicy> quotaPolicies) {
        if (CollectionUtils.isEmpty(quotaPolicies)) {
            return;
        }

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                LOGGER.debug("upsert quota policies: {}", quotaPolicies);
                String resp = HttpClientUtils.post(quotaUpdateUrl, JsonUtil.writeString(quotaPolicies), HttpClientUtils.DEFAULT_HEADER, 1000, 1000);
                LOGGER.debug("upsert quota resp: {}", resp);

                @SuppressWarnings("unchecked")
                HttpResponse<String> httpResponse = JsonUtil.readValue(resp, HttpResponse.class);
                if (httpResponse == null || !httpResponse.isSuccess() || StringUtils.isBlank(httpResponse.getData())) {
                    return;
                }

                try {
                    List<Long> ids = JsonUtil.OBJECT_MAPPER.readValue(httpResponse.getData(), new TypeReference<List<Long>>(){});
                    LOGGER.debug("execute update effect ids: {}", ids);
                } catch (IOException e) {}
            }
        });

    }
}
