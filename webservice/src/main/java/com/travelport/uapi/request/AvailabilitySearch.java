package com.travelport.uapi.request;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.sunshine.service.pojo.VerifyResp;
import com.travelport.ResponseMapping.AvaiableResponse;
import com.travelport.ResponseMapping.baseEntity.BaseAirSegment;
import com.travelport.ResponseMapping.baseEntity.PricingInfo;
import com.travelport.schema.air_v42_0.*;
import com.travelport.tutorial.support.WSDLService;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvailabilitySearch {

    public static VerifyResp transferAirPriceResp(String pcc, AirPriceRsp pricersp, boolean status) {
        List<AirPriceResult> price = pricersp.getAirPriceResult();
        AirPricingSolution soln = CommReq.getLowPriceSoln(price);
        VerifyResp verifyResp = new VerifyResp();

        verifyResp.setTbCode(pcc);
        verifyResp.setTotalPrice(soln.getApproximateTotalPrice());
        verifyResp.setBasePrice(soln.getApproximateBasePrice());
        verifyResp.setTaxes(soln.getApproximateTaxes());
        verifyResp.setCurrency(CommReq.getCurrency(soln.getApproximateBasePrice()));
        verifyResp.setPlatingCarrier(soln.getAirPricingInfo().get(0).getPlatingCarrier());
        verifyResp.setAirPricingInfo(fetchPriceinfos(soln));
        verifyResp.setAirSegment(getAvailableSegmentList(pricersp, status));
        return verifyResp;
    }

    public static String getJsonStr(AirPriceRsp pricersp,boolean status){

        List<AirPriceResult> price=pricersp.getAirPriceResult();
        AirPricingSolution soln=CommReq.getLowPriceSoln(price);
        AvaiableResponse response=new AvaiableResponse();
        response.setTbCode("自定义");
        response.setTotalPrice(soln.getApproximateTotalPrice());
        response.setBasePrice(soln.getApproximateBasePrice());
        response.setTaxes(soln.getApproximateTaxes());
        response.setCurrency(CommReq.getCurrency(soln.getApproximateBasePrice()));
        response.setPlatingCarrier(soln.getAirPricingInfo().get(0).getPlatingCarrier());
        response.setAirPricingInfo(getAvailablePricingList(soln));
        response.setAirSegment(getAvailableSegmentList(pricersp,soln,status));
        Gson gson=new Gson();
        String jsonStr=gson.toJson(response);
        System.out.println(jsonStr);
        return jsonStr;
    }

    public static List<com.sunshine.service.pojo.BaseAirSegment> getAvailableSegmentList(AirPriceRsp pricersp, boolean status) {
        List<com.sunshine.service.pojo.BaseAirSegment> returnList = Lists.newArrayList();
        List<TypeBaseAirSegment> segments = pricersp.getAirItinerary().getAirSegment();
        for (TypeBaseAirSegment segment : segments) {
            com.sunshine.service.pojo.BaseAirSegment targetSegment = new com.sunshine.service.pojo.BaseAirSegment();
            targetSegment.setGroup(segment.getGroup());
            targetSegment.setCarrier(segment.getCarrier());
            targetSegment.setFlightNumber(segment.getFlightNumber());
            CodeshareInfo shareinfo = segment.getCodeshareInfo();
            String operCarier = null;
            String operFlightNum = null;
            if (shareinfo != null) {
                operCarier = shareinfo.getOperatingCarrier();
                operFlightNum = shareinfo.getOperatingFlightNumber();
            }
            String operatingCarrier = operCarier == null ? "" : operCarier;
            String operatingFlightNumber = operFlightNum == null ? "" : operFlightNum;
            targetSegment.setOperatingCarrier(operatingCarrier);
            targetSegment.setOperatingFlightNumber(operatingFlightNumber);
            targetSegment.setOrigin(segment.getOrigin());
            targetSegment.setDestination(segment.getDestination());
            targetSegment.setDepartureTime(segment.getDepartureTime());
            targetSegment.setArrivalTime(segment.getArrivalTime());
            targetSegment.setBookingCode(segment.getClassOfService());
            targetSegment.setCabinClass(segment.getCabinClass());
            targetSegment.setStatus(status);
            targetSegment.setStatusCode(segment.getStatus());
            returnList.add(targetSegment);
        }
        return returnList;
    }

    public static List<BaseAirSegment> getAvailableSegmentList(AirPriceRsp pricersp,AirPricingSolution soln,boolean status){
        List<BaseAirSegment> returnList=new ArrayList<BaseAirSegment>();
        List<TypeBaseAirSegment> segments=pricersp.getAirItinerary().getAirSegment();
        for (TypeBaseAirSegment segment:segments) {
             BaseAirSegment segment1=new BaseAirSegment();
             segment1.setGroup(segment.getGroup());
             segment1.setCarrier(segment.getCarrier());
             segment1.setFlightNumber(segment.getFlightNumber());
             //segment1.setCabinClass(segment.getCabinClass());
//            String[] cabinAndBook=booksMap.get(segment.getKey()).split("-");
//            segment1.setCabinClass(cabinAndBook[0]);
//             segment1.setBookingCode(cabinAndBook[1]);
            CodeshareInfo shareinfo=segment.getCodeshareInfo();
            String operCarier=null;
            String operFlightNum=null;
            if (shareinfo!=null){
                operCarier=shareinfo.getOperatingCarrier();
                operFlightNum=shareinfo.getOperatingFlightNumber();
            }
            String operatingCarrier=operCarier==null?"":operCarier;
            String operatingFlightNumber=operFlightNum==null?"":operFlightNum;
             segment1.setOperatingCarrier(operatingCarrier);
             segment1.setOperatingFlightNumber(operatingFlightNumber);
             segment1.setOrigin(segment.getOrigin());
             segment1.setDestination(segment.getDestination());
             segment1.setDepartureTime(segment.getDepartureTime());
             segment1.setArrivalTime(segment.getArrivalTime());
             boolean stats =status;
             segment1.setStatus(stats);
            returnList.add(segment1);
        }
        return returnList;
    }

    public static List<com.sunshine.service.pojo.PricingInfo> fetchPriceinfos(AirPricingSolution soln) {
        List<com.sunshine.service.pojo.PricingInfo> airPricingInfo = Lists.newArrayList();//code  fare count branch branchbase branchTaxes
        List<AirPricingInfo> infos = soln.getAirPricingInfo();
        for (AirPricingInfo info : infos) {
            List<PassengerType> passengerTypes = info.getPassengerType();
            String code = passengerTypes.get(0).getCode();
            int count = passengerTypes.size();
            String farebasiscode = "";
            List<FareInfo> fareInfos = info.getFareInfo();
            for (FareInfo fareInfo : fareInfos) {
                farebasiscode += fareInfo.getFareBasis() + "/";
            }
            farebasiscode = farebasiscode.substring(0, farebasiscode.length() - 1);
            String branchTotalPrice = info.getApproximateTotalPrice();
            String branchBasePrice = info.getApproximateBasePrice();
            String branchTaxes = info.getApproximateTaxes();
            com.sunshine.service.pojo.PricingInfo pricingInfo = new com.sunshine.service.pojo.PricingInfo();
            pricingInfo.setCode(code);
            pricingInfo.setCount(count);
            pricingInfo.setFareBasisCode(farebasiscode);
            pricingInfo.setBranchBasePrice(branchBasePrice);
            pricingInfo.setBranchTotalPrice(branchTotalPrice);
            pricingInfo.setBranchTaxes(branchTaxes);
            airPricingInfo.add(pricingInfo);
        }
        return airPricingInfo;
    }

    public static List getAvailablePricingList(AirPricingSolution soln){
        List<PricingInfo> airPricingInfo=new ArrayList<PricingInfo>();//code  fare count branch branchbase branchTaxes
        List<AirPricingInfo> infos= soln.getAirPricingInfo();
        for (AirPricingInfo info: infos) {
            List<PassengerType> passengerTypes=info.getPassengerType();
            String code=passengerTypes.get(0).getCode();
            int count=passengerTypes.size();
            String farebasiscode="";
            List<FareInfo> fareInfos=info.getFareInfo();
            for (FareInfo fareInfo:fareInfos) {
                farebasiscode+=fareInfo.getFareBasis()+"/";
            }
            farebasiscode=farebasiscode.substring(0,farebasiscode.length()-1);
            String branchTotalPrice=info.getApproximateTotalPrice();
            String branchBasePrice=info.getApproximateBasePrice();
            String branchTaxes=info.getApproximateTaxes();
            PricingInfo pricingInfo=new PricingInfo();
            pricingInfo.setCode(code);
            pricingInfo.setCount(count);
            pricingInfo.setFareBasisCode(farebasiscode);
            pricingInfo.setBranchBasePrice(branchBasePrice);
            pricingInfo.setBranchTotalPrice(branchTotalPrice);
            pricingInfo.setBranchTaxes(branchTaxes);
            airPricingInfo.add(pricingInfo);
        }
        return airPricingInfo;
    }


}
