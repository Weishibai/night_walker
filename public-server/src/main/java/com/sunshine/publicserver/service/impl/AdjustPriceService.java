package com.sunshine.publicserver.service.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.common.vo.*;
import com.sunshine.publicserver.context.BusinessContext;
import com.sunshine.publicserver.context.MonitorContext;
import com.sunshine.publicserver.fliggy.vo.RoutingElement;
import com.sunshine.publicserver.fliggy.vo.SegmentElement;
import com.sunshine.publicserver.utils.HttpClientUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.codehaus.jackson.type.TypeReference;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class AdjustPriceService {

    private static Logger LOGGER = LoggerFactory.getLogger(AdjustPriceService.class);

    private ListeningExecutorService threadPool = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    @Value("${adjustPriceUrl}")
    private String adjustPriceUrl;

    public void syncAdjustPrice(List<RoutingElement> routingElements) {
        if (CollectionUtils.isEmpty(routingElements)) {
            LOGGER.warn("empty routing for adjust price");
            return;
        }

        for (RoutingElement routingElement : routingElements) {
            List<SegmentElement> fromSegments = routingElement.getFromSegments();
            List<SegmentElement> retSegments = routingElement.getRetSegments();
            PricePolicy request = new PricePolicy();
            request.setTripType(CollectionUtils.isEmpty(retSegments) ? TripType.Single : TripType.Round);
            request.setGds(GDS.ALL);
            String depAirport = fromSegments.get(0).getDepAirport();
            String arrAirport = fromSegments.get(fromSegments.size() - 1).getArrAirport();
            List<InfoCenterData> depData = BusinessContext.queryInfocenter(InfoCenterData.make(depAirport, "", ""));
            List<InfoCenterData> arrData = BusinessContext.queryInfocenter(InfoCenterData.make(arrAirport, "", ""));
            request.setCarrier(fromSegments.get(0).getCarrier());
            request.setFromCity(depData.get(0).getCityCode() + "/" + depData.get(0).getCountryCode());
            request.setToCity(arrData.get(0).getCityCode()+ "/" + arrData.get(0).getCountryCode());
            request.setPlatform(Platform.Fliggy);
            String requestStr = JsonUtil.writeString(request);
            if (StringUtils.isBlank(requestStr)) {
                LOGGER.info("request json is null");
                return;
            }

            String resp = HttpClientUtils.post(adjustPriceUrl, JsonUtil.writeString(request), HttpClientUtils.DEFAULT_HEADER, 2000, 4000);
            @SuppressWarnings("unchecked")
            HttpResponse<String> httpResponse = JsonUtil.readValue(resp, HttpResponse.class);
            if (null == httpResponse || !httpResponse.isSuccess() || StringUtils.isBlank(httpResponse.getData())) {
                LOGGER.info("adjust price error resp: {}", httpResponse);
                return;
            }

            List<PricePolicy> pricePolicies = null;
            try {
                pricePolicies = JsonUtil.OBJECT_MAPPER.readValue(httpResponse.getData(), new TypeReference<List<PricePolicy>>(){});
            } catch (IOException e) {}

            if (CollectionUtils.isEmpty(pricePolicies)) {
                LOGGER.info("no adjust price policy for {}", routingElement);
                return;
            }

            LOGGER.info("adjust policies: {}", pricePolicies);

            List<PricePolicy> targets = fetchBeforeSalesPolicies(pricePolicies);
            LOGGER.info("fetch advanced sale adjust polices: {}", targets);

            double targetPrice = fetchTargetPrice(CollectionUtils.isNotEmpty(targets) ? targets : pricePolicies
                    , fromSegments.get(0), routingElement);

            routingElement.setAdultPrice((int) targetPrice);
        }
    }

    public void adjustPrice(List<RoutingElement> routingElements) {
        if (CollectionUtils.isEmpty(routingElements)) {
            LOGGER.warn("empty routing for adjust price");
            return;
        }

        final CountDownLatch latch = new CountDownLatch(routingElements.size());
        long st = System.currentTimeMillis();
        for (final RoutingElement routingElement : routingElements) {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<SegmentElement> fromSegments = routingElement.getFromSegments();
                        List<SegmentElement> retSegments = routingElement.getRetSegments();
                        PricePolicy request = new PricePolicy();
                        request.setTripType(CollectionUtils.isEmpty(retSegments) ? TripType.Single : TripType.Round);
                        request.setGds(GDS.ALL);
                        String depAirport = fromSegments.get(0).getDepAirport();
                        String arrAirport = fromSegments.get(fromSegments.size() - 1).getArrAirport();
                        List<InfoCenterData> depData = BusinessContext.queryInfocenter(InfoCenterData.make(depAirport, "", ""));
                        List<InfoCenterData> arrData = BusinessContext.queryInfocenter(InfoCenterData.make(arrAirport, "", ""));
                        request.setCarrier(fromSegments.get(0).getCarrier());
                        request.setFromCity(depData.get(0).getCityCode() + "/" + depData.get(0).getCountryCode());
                        request.setToCity(arrData.get(0).getCityCode()+ "/" + arrData.get(0).getCountryCode());
                        request.setPlatform(Platform.Fliggy);
                        String requestStr = JsonUtil.writeString(request);
                        if (StringUtils.isBlank(requestStr)) {
                            LOGGER.debug("request json is null");
                            return;
                        }

                        String resp = HttpClientUtils.post(adjustPriceUrl, JsonUtil.writeString(request), HttpClientUtils.DEFAULT_HEADER, 4000, 8000);
                        @SuppressWarnings("unchecked")
                        HttpResponse<String> httpResponse = JsonUtil.readValue(resp, HttpResponse.class);
                        if (null == httpResponse || !httpResponse.isSuccess() || StringUtils.isBlank(httpResponse.getData())) {
                            LOGGER.debug("adjust price error resp: {}", httpResponse);
                            return;
                        }

                        List<PricePolicy> pricePolicies = null;
                        try {
                            pricePolicies = JsonUtil.OBJECT_MAPPER.readValue(httpResponse.getData(), new TypeReference<List<PricePolicy>>(){});
                        } catch (IOException e) {}

                        if (CollectionUtils.isEmpty(pricePolicies)) {
                            LOGGER.debug("no adjust price policy for {}", routingElement);
                            return;
                        }

                        List<PricePolicy> targets = fetchBeforeSalesPolicies(pricePolicies);
                        LOGGER.debug("fetch advanced sale adjust polices: {}", targets);
                        double targetPrice = fetchTargetPrice(CollectionUtils.isNotEmpty(targets) ? targets : pricePolicies, fromSegments.get(0), routingElement);
                        routingElement.setAdultPrice((int) targetPrice);
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        try {
            latch.await(8, TimeUnit.SECONDS);
            MonitorContext.addRecord("adjust price total", st);
        } catch (Throwable e) {
            LOGGER.error("wait adjust price error: ", e);
        }
    }

    private boolean filterCabin(PricePolicy policy, RoutingElement routingElement) {
        Set<String> cabins = policy.getSpaces();
        if (null == cabins) {
            return false;
        }

        if (policy.getSpaces().contains("*")) {
            return true;
        }

        List<SegmentElement> fromSegments = routingElement.getFromSegments();
        List<SegmentElement> retSegments = routingElement.getRetSegments();

        for (SegmentElement fromSegment : fromSegments) {
            if (!cabins.contains(fromSegment.getCabin())) {
                LOGGER.info("policy {} not match cabin {}", policy.getId(), fromSegment.getCabin());
                return false;
            }
        }

        if (null != retSegments) {
            for (SegmentElement retSegment : retSegments) {
                if (!cabins.contains(retSegment.getCabin())) {
                    LOGGER.info("policy {} not match cabin {}", policy.getId(), retSegment.getCabin());
                    return false;
                }
            }
        }
        return true;
    }

    private double fetchTargetPrice(List<PricePolicy> targets, SegmentElement first, RoutingElement routingElement) {
        double targetPrice = 10000000.0;
        for (PricePolicy policy : targets) {
            if (!maintainSaleDaysLimit(policy, first)) {
                continue;
            }

            if (!filterCabin(policy, routingElement)) {
                continue;
            }

            double adjustPrice = adjustCore(routingElement, policy);
            if (adjustPrice > 0 && adjustPrice - targetPrice < 1e-06) {
                targetPrice = adjustPrice;
            }
        }
        targetPrice = (targetPrice - 9999999.0 > 0.0 ? routingElement.getAdultPrice() : targetPrice);
        LOGGER.info("final adjust price {}", targetPrice);
        return targetPrice;
    }

    private List<PricePolicy> fetchBeforeSalesPolicies(List<PricePolicy> pricePolicies) {
        if (CollectionUtils.isEmpty(pricePolicies)) {
            return Collections.emptyList();
        }

        return Lists.newArrayList(Iterables.filter(pricePolicies, new Predicate<PricePolicy>() {
            @Override
            public boolean apply(PricePolicy input) {
                return input.getAdvanceSaleDays() > 0;
            }
        }));
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyyMMdd HHmm");

    private static final long BEIJING_TIME_OFFSET = -8 * 60 * 60 * 1000L;

    private boolean maintainSaleDaysLimit(PricePolicy policy, SegmentElement segment) {
        if (null == policy || null == segment) {
            return false;
        }

        int advanceSaleDays = policy.getAdvanceSaleDays();

        if (advanceSaleDays < 0) {
            LOGGER.warn("advanceSaleDays is negative {}", policy);
            return false;
        }

        if (0 == advanceSaleDays) {
            return true;
        }

        try {
            DateTime current = DateTime.now();

            String depTime = segment.getDepTime();
            String timeZone = segment.getTimeZone();

            long timeOffSet = (NumberUtils.toLong(timeZone.substring(1, 3)) * 60 + NumberUtils.toLong(timeZone.substring(4, 6))) * 60 * 1000;
            if (timeZone.charAt(0) == '+') {
                timeOffSet = -timeOffSet;
            }

            DateTime dateTime = FORMATTER.parseDateTime(StringUtils.substring(depTime, 0, 8) + " " + StringUtils.substring(depTime, 8));
            DateTime pekTime = new DateTime(dateTime.getMillis() + timeOffSet - BEIJING_TIME_OFFSET);
            LOGGER.info("diff pek time {} current {}", pekTime, current);
            return Days.daysBetween(current, pekTime).getDays() >= advanceSaleDays;
        } catch (Exception e) {
            LOGGER.error("maintainSaleDaysLimit error: ", e);
            return true;
        }
    }



    private double adjustCore(RoutingElement routingElement, PricePolicy pricePolicy) {
        double adultPrice = routingElement.getAdultPrice();
        LOGGER.info("before adjust {}", adultPrice);

        double addMoney = pricePolicy.getExpense() != null ? pricePolicy.getExpense().doubleValue() : 0.0;
        double rate = pricePolicy.getRate() != null ? pricePolicy.getRate().doubleValue() : 0.0;

        adultPrice = adultPrice * (1.0 + rate / 100) + addMoney;
        adultPrice += subsides(routingElement, pricePolicy);
        LOGGER.info("id {} rate {} addMoney {} subsides {} after adjust {}", pricePolicy.getId(), rate, addMoney, pricePolicy.getSubsidies(), adultPrice);
        return adultPrice;
    }

    private double subsides(RoutingElement routingElement, PricePolicy pricePolicy) {
        double subsides = (null == pricePolicy.getSubsidies() ? 0 : pricePolicy.getSubsidies().doubleValue());
        int segs = CollectionUtils.size(routingElement.getFromSegments());
        if (CollectionUtils.isNotEmpty(routingElement.getRetSegments())) {
            segs += CollectionUtils.size(routingElement.getRetSegments());
        }
        return segs * subsides;
    }

}
