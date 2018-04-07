package com.travelport.uapi.request;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.sunshine.common.vo.TripType;
import com.sunshine.service.pojo.*;
import com.travelport.ResponseMapping.*;
import com.travelport.ResponseMapping.baseEntity.FlightCombine;
import com.travelport.schema.air_v42_0.*;
import com.travelport.schema.air_v42_0.FareInfo;
import com.travelport.schema.common_v42_0.ResponseMessage;
import com.travelport.tutorial.support.WSDLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigInteger;
import java.util.*;

public class LowFareSearch {

    private static final Logger LOGGER = LoggerFactory.getLogger(LowFareSearch.class);

    public static FareSearchResponse createLowFareDoubleSearchReq(String providerCode, String pcc, String targetBranch, String from, String to,
                                                                  String from1, String to1,
                                                                  Date date, Date date1, int adultNum, int childNum, String userName, String pwd) {
        LowFareSearchReq req = new LowFareSearchReq();
        req.setTargetBranch(targetBranch);
        req.setReturnBrandedFares(true);
        req.setMetaOptionIdentifier("99");
        req.setTraceId("fa454ac1-33fb-482c-9583-1dab417b420d");
        //添加节点 BillingPointOfSaleInfo
        AirReq.addPointOfSale(req, "uAPI");
        //添加节点 SearchAirLeg
        AirReq.addAirLeg(req, from, to, date);
        AirReq.addAirLeg(req, from1, to1, date1);
        AirReq.addSearchModifiersWithProviders(req, providerCode);
        if (adultNum > 0) {
            AirReq.addAdultPassengers(req, adultNum);
        }

        if (childNum > 0) {
            AirReq.addChildPassengers(req, childNum);
        }

        try {
            AirReq.addPricingModifiers(req, "-");
            AirReq.addPCC(req, providerCode, pcc);
            WSDLService.airShop.showXML(true, userName, pwd);
            LowFareSearchRsp rsp = WSDLService.airShop.get().service(req, null);
            //print results.. payload and trace ID arereq echoed back in response
            TravleUtils.AirSegmentMap segmentMap = new TravleUtils.AirSegmentMap();
            TravleUtils.FightDetailMap detailMap = new TravleUtils.FightDetailMap();
            TravleUtils.AirFareInfoMap fareInfoMap = new TravleUtils.AirFareInfoMap();
            AirSegmentList segmentList = rsp.getAirSegmentList();
            List<TypeBaseAirSegment> segments = segmentList.getAirSegment();
            for (TypeBaseAirSegment segment : segments) {
                segmentMap.add(segment);
            }
            FlightDetailsList detailsList = rsp.getFlightDetailsList();
            List<FlightDetails> fightdetails = detailsList.getFlightDetails();
            for (FlightDetails detail : fightdetails) {
                detailMap.add(detail);
            }
            FareInfoList infolist = rsp.getFareInfoList();
            List<FareInfo> infos = infolist.getFareInfo();
            for (FareInfo info : infos) {
                fareInfoMap.add(info);
            }
            FareSearchResponse fareSearchResponse = buildResp(providerCode, pcc, rsp, segmentMap, detailMap, fareInfoMap);
            fareSearchResponse.setFlyType(2);
            return fareSearchResponse;
        } catch (Exception e) {
            LOGGER.error("invoke low fare roundtrip error: ", e);
            return null;
        }
    }

    public static FareSearchResponse buildResp(String providerCode, String pcc, LowFareSearchRsp rsp, TravleUtils.AirSegmentMap segmentMap
            , TravleUtils.FightDetailMap detailMap, TravleUtils.AirFareInfoMap fareInfoMap) {
        FareSearchResponse response = new FareSearchResponse();
        List<ResponseMessage> messages = rsp.getResponseMessage();
        if (null != messages && messages.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (ResponseMessage msg : messages) {
                message.append(msg.getValue()).append("/");
            }
            return null;
        }

        response.setTbCode(pcc);
        response.setFlightCombinations(createCombinations(providerCode, rsp, segmentMap, detailMap, fareInfoMap));
        return response;
    }
//
//    public static List createFareList(List<AirPricingInfo> infos,TravleUtils.AirFareInfoMap fareInfoMap){
//        List<com.travelport.ResponseMapping.baseEntity.FareInfo> farelist=new ArrayList<com.travelport.ResponseMapping.baseEntity.FareInfo>();
//        for (AirPricingInfo info:infos) {
//            String Type=info.getPassengerType().get(0).getCode();
//            List<FareInfoRef> refs=info.getFareInfoRef();
//            String fareBasisCode="";
//            for (FareInfoRef ref:refs) {
//                FareInfo fareInfo=fareInfoMap.getByKey(ref.getKey());
//                String code=fareInfo.getFareBasis();
//                fareBasisCode+=code+"/";
//            }
//            fareBasisCode=fareBasisCode.substring(0,fareBasisCode.length()-1);
//            int branchTotalPrice=CommReq.getPrice(info.getApproximateTotalPrice());
//            int branchBasePrice=CommReq.getPrice(info.getApproximateBasePrice());
//            int branchTaxes=branchTotalPrice-branchBasePrice;
//            com.travelport.ResponseMapping.baseEntity.FareInfo inf=new com.travelport.ResponseMapping.baseEntity.FareInfo(Type,branchTotalPrice,branchBasePrice,branchTaxes,fareBasisCode);
//            farelist.add(inf);
//
//        }
//        return farelist;
//    }

    public static List<com.sunshine.service.pojo.FareInfo> createFareList(List<AirPricingInfo> infos, TravleUtils.AirFareInfoMap fareInfoMap) {
        List<com.sunshine.service.pojo.FareInfo> fareList = Lists.newArrayList();
        for (AirPricingInfo info : infos) {
            String type = info.getPassengerType().get(0).getCode();
            int branchTotalPrice = CommReq.getPrice(info.getApproximateTotalPrice());
            int branchBasePrice = CommReq.getPrice(info.getApproximateBasePrice());
            int branchTaxes = branchTotalPrice - branchBasePrice;

            List<FareInfoRef> refs = info.getFareInfoRef();
            StringBuilder fareBasisCode = new StringBuilder();
            for (FareInfoRef ref : refs) {
                FareInfo fareInfo = fareInfoMap.getByKey(ref.getKey());
                String code = fareInfo.getFareBasis();
                fareBasisCode.append(code).append("/");
            }
            String farebasis = fareBasisCode.substring(0, fareBasisCode.length() - 1);
            com.sunshine.service.pojo.FareInfo inf =
                    new com.sunshine.service.pojo.FareInfo(type, branchTotalPrice, branchBasePrice, branchTaxes, farebasis);
            fareList.add(inf);
        }
        return fareList;
    }

    public static List<Retreat> createRetreatlist(List<AirPricingInfo> infos) {
        List<Retreat> retreats = Lists.newArrayList();
        for (AirPricingInfo info : infos) {
            String Type = info.getPassengerType().get(0).getCode();
            String ChangePenalty = info.getChangePenalty() == null ? null : info.getChangePenalty().getAmount();
            String CancelPenalty = info.getCancelPenalty() == null ? null : info.getCancelPenalty().getAmount();
            String Currency = CommReq.getCurrency(CancelPenalty);
            Retreat treat = new Retreat(Type, ChangePenalty, CancelPenalty, Currency);
            retreats.add(treat);
        }
        return retreats;
    }

//    public static List<FlightCombine> createCombines(LowFareSearchRsp rsp, TravleUtils.AirSegmentMap segmentMap, TravleUtils.FightDetailMap detailMap) {
//        List<AirPricePoint> pointList = rsp.getAirPricePointList().getAirPricePoint();//每个point都是一个combine
//        List<FlightCombine> combines = new ArrayList<FlightCombine>();
//        for (AirPricePoint point : pointList) {
//            List<AirPricingInfo> infos = point.getAirPricingInfo();
//            AirPricingInfo info = infos.get(0);
//            List<AirSegment> airSegmentList = getSegmentCombine(info, segmentMap, detailMap);
//            for (AirSegment segment : airSegmentList) {
//                FlightCombine combine = new FlightCombine();
//                combine.setTotalPrice(point.getApproximateTotalPrice());
//                combine.setBasePrice(point.getApproximateBasePrice());
//                combine.setTaxes(point.getApproximateTaxes());
//                combine.setCurrency(CommReq.getCurrency(point.getApproximateBasePrice()));
//                List<com.sunshine.service.pojo.FareInfo> farelist = createFareList(infos);
//                combine.setAirSegment(segment.getAirSegment());
//                combine.setLasttickettime(info.getLatestTicketingTime());
//                combine.setPlatingcarrier(info.getPlatingCarrier());
//                combine.setRetreat(createRetreatlist(infos));
//                combine.setFarelist(farelist);
//                combines.add(combine);
//            }
//        }
//        return combines;
//    }

    public static List<FlightCombination> createCombinations(String providerCode, LowFareSearchRsp rsp, TravleUtils.AirSegmentMap segmentMap
            , TravleUtils.FightDetailMap detailMap, TravleUtils.AirFareInfoMap fareInfoMap) {
        List<AirPricePoint> pointList = rsp.getAirPricePointList().getAirPricePoint();//每个point都是一个combine
        List<FlightCombination> combines = Lists.newArrayList();
        for (AirPricePoint point : pointList) {
            List<AirPricingInfo> infos = point.getAirPricingInfo();
            AirPricingInfo info = infos.get(0);
            List<AirSegment> airSegmentList = getSegmentCombine(info, segmentMap, detailMap);
            List<com.sunshine.service.pojo.FareInfo> fareList = createFareList(infos, fareInfoMap);
            List<Retreat> retreatlist = createRetreatlist(infos);
            for (AirSegment segment : airSegmentList) {
                FlightCombination combine = new FlightCombination();
                combine.setTotalPrice(point.getApproximateTotalPrice());
                combine.setBasePrice(point.getApproximateBasePrice());
                combine.setTaxes(point.getApproximateTaxes());
                combine.setCurrency(CommReq.getCurrency(point.getApproximateBasePrice()));
                combine.setAirSegment(segment.getAirSegment());
                combine.setLasttickettime(info.getLatestTicketingTime());
                combine.setPlatingcarrier(info.getPlatingCarrier());
                combine.setRetreat(retreatlist);
                combine.setFarelist(fareList);
                combine.setProviderCode(providerCode);
                combines.add(combine);
            }
        }
        return combines;
    }

    public static List<AirSegment> getSegmentCombine(AirPricingInfo info, TravleUtils.AirSegmentMap segmentMap, TravleUtils.FightDetailMap detailMap) {
        List<FlightOption> options = info.getFlightOptionsList().getFlightOption();
        List<AirSegment> airSegmentList;
        if (options.size() == 1) {
            FlightOption option1 = options.get(0);
            airSegmentList = parseSingleOption(option1, segmentMap, detailMap);
            //只有去程
        } else {
            List<Option> option1 = options.get(0).getOption();//qu
            List<Option> option2 = options.get(1).getOption();//re
            airSegmentList = parseDoubleOption(option1, option2, segmentMap, detailMap);
        }
        return airSegmentList;
    }


    public static List<AirSegment> parseDoubleOption(List<Option> option1, List<Option> option2, TravleUtils.AirSegmentMap segmentMap, TravleUtils.FightDetailMap detailMap) {
        List<AirSegment> airSegmentList = Lists.newArrayList();//多个航班组合
        for (Option opt1 : option1) {
            for (Option opt2 : option2) {
                //3x3 9种
                AirSegment segment = createAirSegments(opt1, opt2, segmentMap, detailMap);
                airSegmentList.add(segment);
            }
        }
        return airSegmentList;
    }

    public static AirSegment createAirSegments(Option opt1, Option opt2, TravleUtils.AirSegmentMap segmentMap, TravleUtils.FightDetailMap detailMap) {
        AirSegment Airsegment = new AirSegment();
        List<BaseAirSegment> baseAirSegments1 = createAirSegment(opt1, segmentMap, detailMap);
        List<BaseAirSegment> baseAirSegments2 = createAirSegment(opt2, segmentMap, detailMap);
        baseAirSegments1.addAll(baseAirSegments2);
        Airsegment.setAirSegment(baseAirSegments1);
        return Airsegment;
    }

    public static List<AirSegment> parseSingleOption(FlightOption option, TravleUtils.AirSegmentMap segmentMap, TravleUtils.FightDetailMap detailMap) {
        List<Option> opts = option.getOption();//一个FlightOption 是一个组合
        List<AirSegment> airSegmentList = Lists.newArrayList();//多个航班组合
        for (Option opt : opts) {
            //每个opt是一种组合
            AirSegment airsegment = new AirSegment();
            List<BaseAirSegment> baseAirSegments = Lists.newArrayList();//一个航班组合
            List<BookingInfo> bookingInfos = opt.getBookingInfo();
            for (BookingInfo inf : bookingInfos) {
                BaseAirSegment segment = new BaseAirSegment();
                segment.setBookingCounts(inf.getBookingCount());
                segment.setBookingCode(inf.getBookingCode());
                segment.setCabinClass(inf.getCabinClass());

                TypeBaseAirSegment seg = segmentMap.getByKey(inf.getSegmentRef());
                segment.setGroup(seg.getGroup());
                segment.setCarrier(seg.getCarrier());
                segment.setFlightNumber(seg.getFlightNumber());
                segment.setOrigin(seg.getOrigin());
                segment.setDepartureTime(seg.getDepartureTime());
                segment.setDestination(seg.getDestination());
                segment.setArrivalTime(seg.getArrivalTime());

                CodeshareInfo shareinfo = seg.getCodeshareInfo();
                String operCarier = null;
                String operFlightNum = null;
                if (shareinfo != null) {
                    operCarier = shareinfo.getOperatingCarrier();
                    operFlightNum = shareinfo.getOperatingFlightNumber();
                }
                String operatingCarrier = operCarier == null ? "" : operCarier;
                String operatingFlightNumber = operFlightNum == null ? "" : operFlightNum;
                segment.setOperatingCarrier(operatingCarrier);
                segment.setOperatingFlightNumber(operatingFlightNumber);
                List<FlightDetailsRef> refs = seg.getFlightDetailsRef();
                String temp = "";
                for (int i = 0; i < refs.size() - 1; i++) {
                    String key = refs.get(i).getKey();
                    FlightDetails details = detailMap.getByKey(key);
                    temp += details.getDestination() + "/";
                }
                String stopCity = null;
                if (temp.equalsIgnoreCase("")) {
                    stopCity = "";
                } else {
                    stopCity = temp.substring(0, temp.length() - 1);
                }
                segment.setStopCity(stopCity);
                BigInteger flightTime = seg.getFlightTime();
                BigInteger distance = seg.getDistance();
                String equipment = seg.getEquipment();
                segment.setFlightTime(flightTime);
                segment.setDistance(distance);
                segment.setEquipment(equipment);

                String flightref = seg.getFlightDetailsRef().get(0).getKey();
                FlightDetails details = detailMap.getByKey(flightref);
                String originTermin = details.getOriginTerminal();
                String destTermin = details.getDestinationTerminal();
                segment.setOriginTerminal(originTermin == null ? "" : originTermin);
                segment.setDestinationTerminal(destTermin == null ? "" : destTermin);
                baseAirSegments.add(segment);
            }
            airsegment.setAirSegment(baseAirSegments);
            airSegmentList.add(airsegment);
        }
        return airSegmentList;
    }

    public static List<BaseAirSegment> createAirSegment(Option opt,TravleUtils.AirSegmentMap segmentMap,TravleUtils.FightDetailMap detailMap){
        List<BaseAirSegment> baseAirSegments=new ArrayList<BaseAirSegment>();//一个航班组合
        List<BookingInfo> bookingInfos=opt.getBookingInfo();
        for (BookingInfo inf:bookingInfos) {
            String bookingCounts=inf.getBookingCount();
            String bookingCode=inf.getBookingCode();
            String cabinClass=inf.getCabinClass();
            TypeBaseAirSegment seg=segmentMap.getByKey(inf.getSegmentRef());
            int group=seg.getGroup();
            String carrier=seg.getCarrier();
            String flightNumber=seg.getFlightNumber();
            String origin=seg.getOrigin();
            String destination=seg.getDestination();
            String departureTime=seg.getDepartureTime();
            String arrivalTime=seg.getArrivalTime();
            CodeshareInfo shareinfo=seg.getCodeshareInfo();
            String operatingCarrier=shareinfo==null?"":shareinfo.getOperatingCarrier()==null?"":shareinfo.getOperatingCarrier();
            String operatingFlightNumber=shareinfo==null?"":shareinfo.getOperatingFlightNumber()==null?"":shareinfo.getOperatingFlightNumber();
            List<FlightDetailsRef> refs=seg.getFlightDetailsRef();
            String temp="";
            for(int i =0;i<refs.size()-1;i++){
                String key= refs.get(i).getKey();
                FlightDetails details=detailMap.getByKey(key);
                temp+=details.getDestination()+"/";
            }
            String stopCity=null;
            if(temp.equalsIgnoreCase("")){
                stopCity="";
            }else {
                stopCity =temp.substring(0,temp.length()-1);
            }

            String flightref=seg.getFlightDetailsRef().get(0).getKey();
            FlightDetails details=detailMap.getByKey(flightref);

            /*segment.setOriginTerminal(originTermin==null?"":originTermin);
            segment.setDestinationTerminal(destTermin==null?"":destTermin);*/
            BigInteger flightTime=seg.getFlightTime();
            BigInteger distance=seg.getDistance();
            String equipment=seg.getEquipment();
            String originTermin=details.getOriginTerminal();
            String destTermin=details.getDestinationTerminal();
            String originTerminal=originTermin==null?"":originTermin;
            String destTerminal=destTermin==null?"":destTermin;
            BaseAirSegment segment=new BaseAirSegment(group,  carrier,  cabinClass,  bookingCounts,  flightNumber,  origin,  destination,  departureTime,  arrivalTime,  operatingCarrier,operatingFlightNumber,stopCity,bookingCode,flightTime,distance,equipment,originTerminal,destTerminal);
            baseAirSegments.add(segment);
        }
        return baseAirSegments;
    }

    public static FareSearchResponse fareSearch(FareSearchReq fareSearchReq, String usrname, String password) {
        if (null == fareSearchReq) {
            return null;
        }

        if (TripType.Single.getCode() == fareSearchReq.getFlightType()) {
            return createLowFareSingleSearchReq(fareSearchReq.getProviderCode(), fareSearchReq.getPcc(), fareSearchReq.getTargetBranch(), fareSearchReq.getFromCity(), fareSearchReq.getToCity()
                , fareSearchReq.getFromDate().toDate(), fareSearchReq.getAdultNum(), fareSearchReq.getChildNum(), usrname, password);
        } else if (TripType.Round.getCode() == fareSearchReq.getFlightType()) {
            return createLowFareDoubleSearchReq(fareSearchReq.getProviderCode(), fareSearchReq.getPcc(), fareSearchReq.getTargetBranch(), fareSearchReq.getFromCity(), fareSearchReq.getToCity(), fareSearchReq.getToCity(), fareSearchReq.getFromCity()
                    , fareSearchReq.getFromDate().toDate(), fareSearchReq.getToDate().toDate(), fareSearchReq.getAdultNum(), fareSearchReq.getChildNum(), usrname, password);
        }
        return null;
    }

    public static FareSearchResponse createLowFareSingleSearchReq(String providerCode, String pcc, String targetBranch, String from1, String to1,
                                                               Date date, int adultNum, int childNum, String userName, String pwd) {
        LowFareSearchReq req = new LowFareSearchReq();
        req.setTargetBranch(targetBranch);
        req.setReturnBrandedFares(true);
//        req.setMetaOptionIdentifier("D");
        req.setMetaOptionIdentifier("99");
        //添加节点 BillingPointOfSaleInfo
        AirReq.addPointOfSale(req,"uAPI");
        //添加节点 SearchAirLeg
        AirReq.addAirLeg(req, from1, to1, date);
        AirReq.addSearchModifiersWithProviders(req, providerCode);
        if (adultNum > 0) {
            AirReq.addAdultPassengers(req, adultNum);
        }
        if (childNum > 0) {
            AirReq.addChildPassengers(req, adultNum);
        }
        try {
            long st = System.currentTimeMillis();
            AirReq.addPricingModifiers(req, "-");
            AirReq.addPCC(req, providerCode, pcc);
            WSDLService.airShop.showXML(true, userName, pwd);
            LowFareSearchRsp rsp = WSDLService.airShop.get().service(req, null);
            LOGGER.info("low fare search total cost: {}ms", System.currentTimeMillis() - st);

            //print results.. payload and trace ID arereq echoed back in response
            TravleUtils.AirSegmentMap segmentMap = new TravleUtils.AirSegmentMap();
            TravleUtils.FightDetailMap detailMap = new TravleUtils.FightDetailMap();
            TravleUtils.AirFareInfoMap fareInfoMap = new TravleUtils.AirFareInfoMap();
            AirSegmentList segmentList = rsp.getAirSegmentList();
            List<TypeBaseAirSegment> segments = segmentList.getAirSegment();
            for (TypeBaseAirSegment segment : segments) {
                segmentMap.add(segment);
            }
            FlightDetailsList detailsList = rsp.getFlightDetailsList();
            List<FlightDetails> fightdetails = detailsList.getFlightDetails();
            for (FlightDetails detail : fightdetails) {
                detailMap.add(detail);
            }
            FareInfoList infolist = rsp.getFareInfoList();
            List<FareInfo> infos = infolist.getFareInfo();
            for (FareInfo info : infos) {
                fareInfoMap.add(info);
            }
            FareSearchResponse fareSearchResponse = buildResp(providerCode, pcc, rsp, segmentMap, detailMap, fareInfoMap);
            fareSearchResponse.setFlyType(1);
            return fareSearchResponse;
        } catch (Throwable e) {
            LOGGER.error("invoke low fare error: ", e);
        }
        return null;
    }


}
