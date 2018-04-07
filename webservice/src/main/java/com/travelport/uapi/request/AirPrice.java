package com.travelport.uapi.request;

import com.google.common.collect.Lists;
import com.sunshine.service.pojo.AirSegment;
import com.sunshine.service.pojo.BaseAirSegment;
import com.sunshine.service.pojo.VerifyReq;
import com.sunshine.service.pojo.VerifyResp;
import com.travelport.ResponseMapping.AvaiableResponse;
import com.travelport.schema.air_v42_0.AirPriceReq;
import com.travelport.schema.air_v42_0.AirPriceRsp;
import com.travelport.schema.air_v42_0.AirSegmentPricingModifiers;
import com.travelport.schema.air_v42_0.BookingCode;
import com.travelport.schema.air_v42_0.Connection;
import com.travelport.schema.air_v42_0.TypeBaseAirSegment;
import com.travelport.schema.common_v42_0.SearchPassenger;
import com.travelport.service.air_v42_0.AirFaultMessage;
import com.travelport.tutorial.support.WSDLService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirPrice {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirPrice.class);

    public static TypeBaseAirSegment createAirSegment(String key, String AvailabilitySource,
                                                      String Group, String Carrier, String FlightNumber,
                                                      String Origin, String Destination, String DepartureTime,
                                                      String ArrivalTime, String ProviderCode, boolean hasConnection, String cabin) {
        TypeBaseAirSegment t = new TypeBaseAirSegment();
        t.setKey(key);
        t.setAvailabilitySource(AvailabilitySource);
        t.setGroup(Integer.parseInt(Group));
        t.setCarrier(Carrier);
        t.setFlightNumber(FlightNumber);
        t.setOrigin(Origin);
        t.setDestination(Destination);
        t.setDepartureTime(DepartureTime);
        t.setArrivalTime(ArrivalTime);
        t.setProviderCode(ProviderCode);
        if (hasConnection) {
            t.setConnection(new Connection());
        }
        t.setClassOfService(cabin);
        return t;
    }
    public static SearchPassenger createSearchPassenger(String Code,String BookingTravelerRef){
        SearchPassenger schpasss=new SearchPassenger();
        schpasss.setCode(Code);
        if (Code.equalsIgnoreCase("CNN")){
            schpasss.setAge(new BigInteger("8"));
        }
        schpasss.setBookingTravelerRef(BookingTravelerRef);
        return schpasss;
    }

    public static AirSegmentPricingModifiers createSegmentPricingModifier(String AirSegmentRef, String basiscode, String Code) {
        AirSegmentPricingModifiers pricingModifiers = new AirSegmentPricingModifiers();
        pricingModifiers.setAirSegmentRef(AirSegmentRef);
        pricingModifiers.setFareBasisCode(basiscode);
        AirSegmentPricingModifiers.PermittedBookingCodes permittedBookingCode = new AirSegmentPricingModifiers.PermittedBookingCodes();
        BookingCode code = new BookingCode();
        code.setCode(Code);
        permittedBookingCode.getBookingCode().add(code);
        pricingModifiers.setPermittedBookingCodes(permittedBookingCode);
        return pricingModifiers;
    }

    public static AirPriceRsp _verify(VerifyReq verifyReq, String usrname, String password) {
        AirSegment flightInfo = verifyReq.getAirSegment();

        int count = 1;
        List<AirSegmentPricingModifiers> modifiers = Lists.newArrayList();
        List<TypeBaseAirSegment> targetSegments = Lists.newArrayList();
        for (BaseAirSegment flightSegment : flightInfo.getAirSegment()) {
            modifiers.add(AirPrice.createSegmentPricingModifier(String.valueOf(count)
                    ,null, flightSegment.getBookingCode()));
            boolean conn = count < flightInfo.getAirSegment().size() && flightSegment.getGroup() == flightInfo.getAirSegment().get(count).getGroup();

            /* 机场三字码 */
            targetSegments.add(AirPrice.createAirSegment(String.valueOf(count), "A", String.valueOf(flightSegment.getGroup()), flightSegment.getCarrier(), StringUtils.substring(flightSegment.getFlightNumber(), 2)
                    , flightSegment.getOrigin(), flightSegment.getDestination(), flightSegment.getDepartureTime(), flightSegment.getArrivalTime(), verifyReq.getProviderCode(), conn, flightSegment.getBookingCode()));
            count++;
        }

        Map<String, Object> airPriceReqMap = buildAndInvokeAirPrice(verifyReq.getProviderCode(), verifyReq.getPcc(), verifyReq.getTargetBranch(), verifyReq.getAdultNum(), verifyReq.getChildNum()
                , targetSegments, modifiers, usrname, password);
        if (MapUtils.isEmpty(airPriceReqMap)) {
            LOGGER.error("empty air price response");
            return null;
        }

        if (airPriceReqMap.containsKey("3319")) {
            LOGGER.error("air price response code 3319");
            return null;
        } else if (airPriceReqMap.containsKey("false")) {
            LOGGER.error("air price response is false");
            return null;
        }

        return (AirPriceRsp) airPriceReqMap.get("true");
    }

    public static VerifyResp verify(VerifyReq verifyReq, String usrname, String password) {
        AirPriceRsp airPriceRsp = _verify(verifyReq, usrname, password);
        VerifyResp verifyResp = AvailabilitySearch.transferAirPriceResp(verifyReq.getPcc(), airPriceRsp, true);
        verifyResp.setProviderCode(verifyReq.getProviderCode());
        verifyResp.setTripType(verifyReq.getFlightType());
        return verifyResp;
    }

    public static Map<String, Object> buildAndInvokeAirPrice(String providerCode, String pcc, String targetBranch, int adultNum, int childNum, List<TypeBaseAirSegment> segments, List<AirSegmentPricingModifiers> modifiers, String userName, String pwd) {
        List<SearchPassenger> Passengers = createPassenger(adultNum, childNum);
        return askAirPriceReq(providerCode, pcc, targetBranch, segments, Passengers, modifiers, userName, pwd);
    }

    public static Map<String,AirPriceRsp>  createAirPriceReqMap(int adultNum,int childNum,List<TypeBaseAirSegment> Segments,List<AirSegmentPricingModifiers> modifiers,String userName,String pwd){
        //下面key 为自定义编号

        List<SearchPassenger> Passengers=createPassenger(adultNum,childNum);
        Map priceRspMap=askAirPriceReq("","", TravelportConfig.TARGET_BRANCH,Segments,Passengers,modifiers,userName,pwd);
        return  priceRspMap;
    }
    public static List<SearchPassenger> createPassenger(int AdultNum,int childNum){
        List<SearchPassenger> Passengers= new ArrayList<>();
        //乘客类型 以及编号
        int count =1;
        for (int i=0;i<AdultNum;i++,count++){
            Passengers.add(createSearchPassenger("ADT",String.valueOf(count)));
        }
        for (int i=0;i<childNum;i++,count++){
            Passengers.add(createSearchPassenger("CNN",String.valueOf(count)));
        }
        return Passengers;
    }
    public  static Map<String, Object> askAirPriceReq(String providerCode, String pcc, String targetBranch, List<TypeBaseAirSegment> Segments,
                                                      List<SearchPassenger> Passengers, List<AirSegmentPricingModifiers> pricingModifiers, String userName, String pwd) {
        Map<String, Object> rspMap = new HashMap<>();
        try {
            //设置属性
            AirPriceReq req = new AirPriceReq();
            req.setTraceId("f11f70b6-f232-413a-8a18-53dbb220cfb4");
            req.setAuthorizedBy("Travelport");
            req.setTargetBranch(targetBranch);
            //添加节点 BillingPointOfSaleInfo
            AirReq.addPointOfSale(req, "uAPI");
            //添加节点  AirItinerary
            AirReq.addItinerary(req, Segments);
            //添加节点 AirPricingModifiers
            AirReq.addPricingModifiers(req);
            //添加节点  SearchPassenger
            AirReq.addPassengers(req, Passengers);
            //添加节点 AirPricingCommand
            AirReq.addPricingCommand(req, pricingModifiers);
            AirReq.addPCC(req, providerCode, pcc);
            /*//添加节点 FormOfPayment  可有可无
            AirReq.addFormOfPayment(req);*/
            WSDLService.airPrice.showXML(true, userName, pwd);
            AirPriceRsp rsp = WSDLService.airPrice.get().service(req, null);
            rspMap.put("true", rsp);
            return rspMap;
        } catch (AirFaultMessage e) {
            LOGGER.error("basic create pnr failed: ", e);
            String errorInfo = "booking class not available";
            String errorcode = e.getFaultInfo().getCode();
            if ("3319".equalsIgnoreCase(errorcode)) {
                rspMap.put("3319", errorInfo);
                return rspMap;
            }
        } catch (Exception e) {
            rspMap.put("false", e.getMessage());
            LOGGER.error("basic create pnr failed: ", e);
        }
        return rspMap;
    }


}
