package com.sunshine.publicserver.utils;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.publicserver.constants.Con;
import com.sunshine.publicserver.fliggy.vo.*;
import com.sunshine.service.pojo.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TransferUtil {

    public static void fillCabinInfos(RoutingElement routingElement, List<BaseAirSegment> airSegments) {
        List<SegmentElement> fromSegments = routingElement.getFromSegments();
        List<SegmentElement> retSegments = routingElement.getRetSegments();

        List<SegmentElement> flatSegments = Lists.newArrayList();
        flatSegments.addAll(fromSegments);
        if (!CollectionUtils.isEmpty(retSegments)) {
            flatSegments.addAll(retSegments);
        }

        for (int i = 0; i < airSegments.size(); i ++) {
            BaseAirSegment baseAirSegment = airSegments.get(i);
            SegmentElement segmentElement = flatSegments.get(i);
            if (StringUtils.isBlank(baseAirSegment.getBookingCode())) {
                baseAirSegment.setBookingCode(segmentElement.getCabin());
            }
        }
    }

    public static AirSegment verifyReqTransfer(RoutingElement routingElement) {
        AirSegment flightInfo = new AirSegment();
        List<BaseAirSegment> segments = Lists.newArrayList();
        flightInfo.setAirSegment(segments);

        for (SegmentElement segmentElement : routingElement.getFromSegments()) {
            segments.add(baseTransfer(segmentElement));
        }

        if (null != routingElement.getRetSegments()) {
            for (SegmentElement segmentElement : routingElement.getRetSegments()) {
                segments.add(baseTransfer(segmentElement));
            }
        }
        return flightInfo;

    }

    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMddHHmm");

    private static BaseAirSegment baseTransfer(SegmentElement segmentElement) {
        BaseAirSegment targetSegment = new BaseAirSegment();
        targetSegment.setBookingCode(segmentElement.getCabin());
        targetSegment.setCarrier(segmentElement.getCarrier());
        targetSegment.setFlightNumber(segmentElement.getFlightNumber());
        targetSegment.setOrigin(segmentElement.getDepAirport());
        targetSegment.setDestination(segmentElement.getArrAirport());

        DateTime dep = formatter.parseDateTime(segmentElement.getDepTime());
        DateTime arr = formatter.parseDateTime(segmentElement.getArrTime());

        String depTime = dep.toString(invFormatter).replace(" ", "T");
        String arrTime = arr.toString(invFormatter).replace(" ", "T");
        targetSegment.setDepartureTime(depTime);
        targetSegment.setArrivalTime(arrTime);


        targetSegment.setStopCity(segmentElement.getStopCities());
        targetSegment.setOperatingCarrier(segmentElement.getOperatingCarrier());
        targetSegment.setOperatingFlightNumber(segmentElement.getOperatingFlightNo());
        targetSegment.setDestinationTerminal(segmentElement.getDepartureTerminal());
        targetSegment.setDestinationTerminal(segmentElement.getArrivingTerminal());
        targetSegment.setEquipment(segmentElement.getAircraftCode());
        targetSegment.setCabinClass(CabinClass.invCodeOf(segmentElement.getCabinClass()));
        return targetSegment;
    }

    public static PayResponse payTransform(PayResp origin, PayRequest payRequest) {
        PayResponse response = PayResponse.make(0, "success");
        response.setSessionId(payRequest.getSessionId());
        response.setOrderNo(payRequest.getOrderNo());
        response.setPnrCode(payRequest.getPnrCode());
        response.setTicketingGds("OT");
        RoutingElement routing = payRequest.getRouting();

        /* 更新价格 */
        for (PricingInfo pricingInfo : origin.getAirPricingInfo()) {
            if (StringUtils.equalsIgnoreCase(pricingInfo.getCode(), "CHD")) {
                routing.setChildPrice((int) NumberUtils.toDouble(pricingInfo.getBranchBasePrice()));
                routing.setChildTax((int) NumberUtils.toDouble(pricingInfo.getBranchTaxes()));
            } else {
                routing.setAdultPrice((int) NumberUtils.toDouble(pricingInfo.getBranchBasePrice()));
                routing.setAdultTax((int) NumberUtils.toDouble(pricingInfo.getBranchTaxes()));
            }
        }
        response.setRouting(routing);
        return response;
    }

    public static OrderResponse orderTransform(OrderResp origin, RoutingElement routingElement
            , String sessionId) {
        OrderResponse response = OrderResponse.make(0, "success");
        response.setSessionId(sessionId);
        response.setRouting(routingElement);

        /* 更新价格 */
        for (PricingInfo pricingInfo : origin.getAirPricingInfo()) {
            if (StringUtils.equalsIgnoreCase(pricingInfo.getCode(), "CHD")) {
                routingElement.setChildPrice((int) NumberUtils.toDouble(pricingInfo.getBranchBasePrice()));
                routingElement.setChildTax((int) NumberUtils.toDouble(pricingInfo.getBranchTaxes()));
            } else {
                routingElement.setAdultPrice((int) NumberUtils.toDouble(pricingInfo.getBranchBasePrice()));
                routingElement.setAdultTax((int) NumberUtils.toDouble(pricingInfo.getBranchTaxes()));
            }
        }

        response.setMaxSeats(9); /* todo */
        response.setBookingGds("OT");
        response.setOrderNo(Base64Util.wrapEncode(JsonUtil.writeString(origin.getPnrCodes()), Con.DATA_TOKEN));  /* todo */
        response.setPnrCode(origin.getPnrCodes().get(1));  /* todo */
        return response;
    }

    public static VerifyResponse verifyTransform(VerifyResp origin, String fareCacheKey, RoutingElement search) {
        if (CollectionUtils.isEmpty(origin.getAirSegment())) {
            return VerifyResponse.make(3, "lack of cabin");
        }

        if (CollectionUtils.isEmpty(origin.getAirPricingInfo())) {
            return VerifyResponse.make(4, "lack of price");
        }

        if (StringUtils.isBlank(origin.getTaxes())) {
            return VerifyResponse.make(5, "lack of taxes");
        }

        List<PricingInfo> airPricingInfo = origin.getAirPricingInfo();
        VerifyResponse response = VerifyResponse.make(0, "success");
        response.setRule(search.getRule());
        response.setMaxSeats(9);
        response.setRouting(search);

        /* 更新价格 */
        for (PricingInfo pricingInfo : airPricingInfo) {
            if (StringUtils.equalsIgnoreCase(pricingInfo.getCode(), "CHD")) {
                search.setChildPrice((int) NumberUtils.toDouble(pricingInfo.getBranchBasePrice()));
                search.setChildTax((int) NumberUtils.toDouble(pricingInfo.getBranchTaxes()));
            } else {
                search.setAdultPrice((int) NumberUtils.toDouble(pricingInfo.getBranchBasePrice()));
                search.setAdultTax((int) NumberUtils.toDouble(pricingInfo.getBranchTaxes()));
            }
        }
        response.setSessionId("fliggy_" + System.currentTimeMillis() + "_1");

        /* build data */
        FliggyDataInfo data = new FliggyDataInfo();
        data.setTbCode(origin.getTbCode());
        data.setFareCacheKey(fareCacheKey);
        data.setProviderCode(origin.getProviderCode());
        data.setFareBasis(Lists.transform(airPricingInfo, new Function<PricingInfo, String>() {
            @Override
            public String apply(PricingInfo input) {
                return input.getFareBasisCode();
            }
        }));
        data.setSessionId(response.getSessionId());
        search.setData(Base64Util.wrapEncode(JsonUtil.writeString(data), Con.DATA_TOKEN));
        return response;
    }

    private static final DateTimeFormatter invFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {

        String time = "2017-07-31T04:06:27-06:00";
        System.out.println(transferTime(time));

//        DateTime dep = formatter.parseDateTime("201802230740");
//        String s = dep.toString(invFormatter);
//
//        DateTime arr = formatter.parseDateTime("201802231140");

        String hah = "EVwHkG+gHmEYUhyQznBFbDS1Fi6Quxk2NYwKe7RB0QxBZa3riOTYSQLfsOL87Y5u0Jt7KKla/y74wPhDkqGAxjDPnNWHEBnYI+OCbfdRddEqOLjbMSgplJzxNQMsNfOevz94nVe4xDphf06LPQ7YpQ==";
        System.out.println(Base64Util.wrapDecode(hah, Con.DATA_TOKEN));


        FliggyDataInfo data = new FliggyDataInfo();
//        data.setTbCode("8Y01");
//        data.setProviderCode("1G");
        data.setTbCode("4EU");
        data.setProviderCode("1P");
        System.out.println(Base64Util.wrapEncode(JsonUtil.writeString(data), Con.DATA_TOKEN));
    }

    public static List<RoutingElement> searchTransform(FareSearchResponse response, String cacheKey) {

        List<FlightCombination> flightCombinations = response.getFlightCombinations();
        List<RoutingElement> routingElements = Lists.newArrayList();

        for (FlightCombination flightCombination : flightCombinations) {
            List<FareInfo> farelist = flightCombination.getFarelist();
            RoutingElement routingElement = new RoutingElement();

            for (FareInfo fareInfo : farelist) {
                if (StringUtils.equalsIgnoreCase(fareInfo.getType(), "ADT")) {
                    routingElement.setAdultPrice(fareInfo.getBranchBasePrice());
                    routingElement.setAdultTax(fareInfo.getBranchTaxes());
                } else {
                    routingElement.setChildPrice(fareInfo.getBranchBasePrice());
                    routingElement.setChildTax(fareInfo.getBranchTaxes());
                }
            }
            routingElement.setNationalityType(0);
            routingElement.setNationality(StringUtils.EMPTY);
            routingElement.setSuitAge(StringUtils.EMPTY);
            routingElement.setApplyType(0);
            routingElement.setPriceType(0);
            routingElement.setAdultTaxType(0);
            routingElement.setChildTaxType(0);
            routingElement.setMinPassengerCount(1);
            routingElement.setMaxPassengerCount(9);
            routingElement.setGvChildRule(1);

            /* 构建退改签 */
            RuleElement ruleElement = new RuleElement();
            ruleElement.setHasRefund(1);
            ruleElement.setRefundCurrency(StringUtils.EMPTY);
            ruleElement.setRefund("*-2-*");
            ruleElement.setPartRefundCurrency(StringUtils.EMPTY);
            ruleElement.setPartRefund(0);
            /* todo check */
            ruleElement.setPartRefundPrice(StringUtils.EMPTY);
            ruleElement.setPartRefundSeg("ALL");
            ruleElement.setHasEndorse(1);
            ruleElement.setEndorseCurrency(StringUtils.EMPTY);
            ruleElement.setEndorse("*-2-*");

            if (1 == response.getFlyType()) {
                ruleElement.setPartEndorse(0);
            } else {
                ruleElement.setPartEndorse(1);
            }
            ruleElement.setPartEndorseCurrency(StringUtils.EMPTY);
            ruleElement.setPartEndorsePrice(StringUtils.EMPTY);
            ruleElement.setEndorsement(0);
            ruleElement.setHasBaggage(1);
            ruleElement.setBaggage("-;-;-;-");
            ruleElement.setHasNoShow(0);
            ruleElement.setNoShowLimitTime(0);
            ruleElement.setNoShowCurrency(StringUtils.EMPTY);
            ruleElement.setSpecialNoShow(0);
            ruleElement.setPenalty(StringUtils.EMPTY);
            /* todo 传啥？ */
            ruleElement.setOther("");
            routingElement.setRule(ruleElement);


            /* 构建航段 */
            List<SegmentElement> fromSegments = Lists.newArrayList();
            for (BaseAirSegment airSegment : flightCombination.getAirSegment()) {
                if (airSegment.getGroup() == 0) {
                    fromSegments.add(buildSegment(airSegment));
                }
            }
            routingElement.setFromSegments(fromSegments);

            routingElement.setRetSegments(Lists.<SegmentElement>newArrayList());
            if (response.getFlyType() == 2) {
                List<SegmentElement> retSegments = routingElement.getRetSegments();
                for (BaseAirSegment airSegment : flightCombination.getAirSegment()) {
                    if (airSegment.getGroup() == 1) {
                        retSegments.add(buildSegment(airSegment));
                    }
                }
                routingElement.setRetSegments(retSegments);
            }


            /* add data */
            FliggyDataInfo data = new FliggyDataInfo();
            data.setTbCode(response.getTbCode());
            data.setProviderCode(flightCombination.getProviderCode());
            data.setFareCacheKey(cacheKey);
            data.setFareBasis(Lists.transform(farelist, new Function<FareInfo, String>() {
                @Override
                public String apply(FareInfo input) {
                    return input.getFareBasisCode();
                }
            }));

            data.setExtMap(Maps.<String, String>newHashMap());
            MapUtils.safeAddToMap(data.getExtMap(), "timeZone", Iterables.getFirst(fromSegments, null).getTimeZone());
            routingElement.setData(Base64Util.wrapEncode(JsonUtil.writeString(data), Con.DATA_TOKEN));
            routingElements.add(routingElement);
        }
        return routingElements;
    }

    private static String transferTime(String origin) {
        String[] result = origin.split("T");
        String pre = result[0].replaceAll("-", "");
        String post = result[1].substring(0, 5).replaceAll(":", "");
        return pre + post;
    }

    private static SegmentElement buildSegment(BaseAirSegment airSegment) {
        SegmentElement segmentElement = new SegmentElement();
        segmentElement.setCarrier(airSegment.getCarrier());
        segmentElement.setFlightNumber(airSegment.getCarrier() + airSegment.getFlightNumber());
        segmentElement.setDepAirport(airSegment.getOrigin());
        segmentElement.setArrAirport(airSegment.getDestination());

        /* todo check time format */

        segmentElement.setDepTime(transferTime(airSegment.getDepartureTime()));
        segmentElement.setArrTime(transferTime(airSegment.getArrivalTime()));
        segmentElement.setTimeZone(fetchTimeZone(airSegment));
        segmentElement.setStopCities(airSegment.getStopCity());
        segmentElement.setCodeShare(StringUtils.isNotBlank(airSegment.getOperatingCarrier()));
        segmentElement.setOperatingCarrier(airSegment.getOperatingCarrier());
        segmentElement.setOperatingFlightNo(airSegment.getOperatingFlightNumber());
        segmentElement.setDepartureTerminal(airSegment.getOriginTerminal());
        segmentElement.setArrivingTerminal(airSegment.getDestinationTerminal());
        segmentElement.setCabin(airSegment.getBookingCode());
        segmentElement.setAircraftCode(airSegment.getEquipment());
        segmentElement.setSeatCount(StringUtils.EMPTY);
        segmentElement.setCabinClass(CabinClass.codeOf(airSegment.getCabinClass()));
        return segmentElement;
    }

    private static String fetchTimeZone(BaseAirSegment airSegment) {
        if (null == airSegment) {
            return "+08:00";
        }

        String departureTime = airSegment.getDepartureTime();
        int index = StringUtils.lastIndexOf(departureTime, "+");
        if (-1 == index) {
            index = StringUtils.lastIndexOf(departureTime, "-");
        }

        if (-1 == index) {
            return "+08:00";
        }

        return StringUtils.substring(departureTime, index);
    }



    public static List<PricingInfo> removeCurrencySuffix(List<PricingInfo> origins) {
        if (CollectionUtils.isEmpty(origins)) {
            return origins;
        }

        for (PricingInfo pricingInfo : origins) {
            if (!org.apache.commons.lang.math.NumberUtils.isNumber(pricingInfo.getBranchBasePrice())) {
                pricingInfo.setBranchBasePrice(org.apache.commons.lang.StringUtils.substring(pricingInfo.getBranchBasePrice(), 3));
            }

            if (!org.apache.commons.lang.math.NumberUtils.isNumber(pricingInfo.getBranchTotalPrice())) {
                pricingInfo.setBranchTotalPrice(org.apache.commons.lang.StringUtils.substring(pricingInfo.getBranchTotalPrice(), 3));
            }

            if (!org.apache.commons.lang.math.NumberUtils.isNumber(pricingInfo.getBranchTaxes())) {
                pricingInfo.setBranchTaxes(org.apache.commons.lang.StringUtils.substring(pricingInfo.getBranchTaxes(), 3));
            }
        }
        return origins;
    }


}
