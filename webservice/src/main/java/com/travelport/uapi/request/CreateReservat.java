package com.travelport.uapi.request;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.sunshine.service.pojo.ApiPassenger;
import com.sunshine.service.pojo.OrderReq;
import com.sunshine.service.pojo.OrderResp;
import com.sunshine.service.pojo.VerifyReq;
import com.travelport.ResponseMapping.CreateReservateResponse;
import com.travelport.ResponseMapping.baseEntity.BaseAirSegment;
import com.travelport.ResponseMapping.baseEntity.PricingInfo;
import com.travelport.schema.air_v42_0.*;
import com.travelport.schema.common_v42_0.*;
import com.travelport.schema.universal_v42_0.AirCreateReservationReq;
import com.travelport.schema.universal_v42_0.AirCreateReservationRsp;
import com.travelport.schema.universal_v42_0.UniversalRecord;
import com.travelport.service.universal_v42_0.AirCreateReservationPortType;
import com.travelport.service.universal_v42_0.AirFaultMessage;
import com.travelport.service.universal_v42_0.AvailabilityFaultMessage;
import com.travelport.tutorial.support.WSDLService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.xml.datatype.DatatypeConfigurationException;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.*;

public class CreateReservat {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateReservat.class);

    public static void main(String[] args) {

    }

    public static List<Passenger> transferPassengers(List<ApiPassenger> apiPassengers) {
        List<Passenger> passengers = Lists.newArrayList();
        try {
            for (ApiPassenger apiPassenger : apiPassengers) {
                Passenger ps = new Passenger();
                ps.setAge(new BigInteger(String.valueOf(apiPassenger.getAge())));
                ps.setBirthDay(apiPassenger.getBirthday());
                ps.setEmailId(apiPassenger.getEmailId());
                ps.setEmailType(apiPassenger.getEmailType());
                ps.setFirstName(apiPassenger.getFirstName());
                ps.setLastName(apiPassenger.getLastName());
                ps.setNike(apiPassenger.getNickname());
                ps.setGender(apiPassenger.getGender());
                ps.setId(apiPassenger.getId());
                ps.setType(apiPassenger.getType());
                ps.setFreeText(apiPassenger.getPassport());
                passengers.add(ps);
            }
        } catch (Exception e) {
            LOGGER.error("create passenger error: ", e);
            throw new RuntimeException("create passenger error");
        }
        return passengers;
    }

    public static OrderResp createPnr(OrderReq orderRequest, String usrname, String passwd) {
        VerifyReq verifyReq = new VerifyReq();
        verifyReq.setTargetBranch(orderRequest.getTargetBranch());
        verifyReq.setAirSegment(orderRequest.getAirSegment());
        verifyReq.setFlightType(orderRequest.getFlightType());
        verifyReq.setFareBasis(orderRequest.getFareBasis());
        verifyReq.setAdultNum(orderRequest.getAdultNum());
        verifyReq.setChildNum(orderRequest.getChildNum());
        verifyReq.setProviderCode(orderRequest.getProviderCode());
        verifyReq.setPcc(orderRequest.getPcc());

        AirPriceRsp airPriceRsp = AirPrice._verify(verifyReq, usrname, passwd);

        if (null == airPriceRsp) {
            LOGGER.error("生单占座失败");
            return null;
        }

        /* piece of garbage */
        String departureTime = Iterables.getFirst(orderRequest.getAirSegment().getAirSegment(), null).getDepartureTime();
        List<String> segmentStrings = CreateReservat.getSegmentRefs(airPriceRsp);
        Address address=new Address("Home","123 Dalton Drive", "Calgary","AB","T2P1K6","CA");
        String areaCode = "852";
        String countryCode = "";
        String location = "HKG";
        String number = "68586434";

        /* transfer passengers */
        List<ApiPassenger> apiPassengers = orderRequest.getPassengers();
        List<Passenger> passengers = transferPassengers(apiPassengers);
        LOGGER.info("build passengers: {}", passengers);

        AirCreateReservationRsp createPnrResp = _createPnr(orderRequest.getProviderCode(), airPriceRsp, orderRequest.getTargetBranch(), segmentStrings, address, passengers
                , areaCode, countryCode, location, number, departureTime, usrname, passwd);

        return transferResp(createPnrResp, airPriceRsp);
    }

    private static AirCreateReservationRsp _createPnr(String providerCode, AirPriceRsp rsp, String targetBranch, List<String> segmentStrings,
                                                      Address address, List<Passenger> passengers, String areaCode,
                                                      String countryCode, String location, String number, String departTime, String userName, String pwd) {

        try {
            AirCreateReservationReq req=new AirCreateReservationReq();
            //req.setTraceId("zxcvbnm==");
            req.setTraceId("fa454ac1-33fb-482c-9583-1dab417b420d");
            req.setTargetBranch(targetBranch);
            req.setAuthorizedBy("user");
            AirReq.addPointOfSale(req,"UAPI");

            List<Passenger> adultPassenger = Lists.newArrayList();
            List<Passenger> childPassenger = Lists.newArrayList();
            Map<String, List<Passenger>> adultRefs = Maps.newHashMap();
            Map<String, List<Passenger>> childRefs = Maps.newHashMap();
            //自定义bookingTravlerRef 也就是id字段
            for (int i = 0; i < passengers.size(); i++) {
                Passenger pass=passengers.get(i);
                if (i==0){
                    CommReq.addBookingTravler(req,pass,address,areaCode,countryCode,location,number,createSSRS(segmentStrings,pass.getFreeText()),departTime);
                }else{
                    CommReq.addBookingTravler(req,pass,null,areaCode,countryCode,location,number,createSSRS(segmentStrings,pass.getFreeText()),departTime);
                }
                if (pass.getType().equalsIgnoreCase("ADT")){
                    adultPassenger.add(pass);
                }else {
                    childPassenger.add(pass);
                }
            }
            adultRefs.put("ADT",adultPassenger);
            childRefs.put("CNN",childPassenger);
            CommReq.addContinueCheckOverride(req);
            req.getFormOfPayment().add(CommReq.createFormOfPayment("Cash","jwt2mcK1Qp27I2xfpcCtAw=="));
            AirItinerary itinerary=rsp.getAirItinerary();
            List<TypeBaseAirSegment> ls=itinerary.getAirSegment();
            TravleUtils.AirSegmentMap segmentMap=new TravleUtils.AirSegmentMap();
            for (TypeBaseAirSegment type: ls) {
                type.setCodeshareInfo(null);
                segmentMap.add(type);
            }
            List<AirPriceResult> price = rsp.getAirPriceResult();
            AirPricingSolution soln = CommReq.getLowPriceSoln(price);
            soln = removeAndStitue(soln, segmentMap, adultRefs, childRefs);
            if (soln.getOptionalServices() != null) {
                soln.setOptionalServices(null);
            }
            List<AirPricingInfo> airPricingInfo = soln.getAirPricingInfo();
            req.setAirPricingSolution(soln);
            soln.setAirPricingInfo(null);
            soln.setFareNote(null);
            soln.setHostToken(null);
            /* add price info modifiers */
//            for (AirPricingInfo airPricingInfo : soln.getAirPricingInfo()) {
//                AirPricingModifiers modifier = new AirPricingModifiers();
//                modifier.setFaresIndicator(TypeFaresIndicator.PUBLIC_AND_PRIVATE_FARES);
//                airPricingInfo.setAirPricingModifiers(modifier);
//            }
            CommReq.addActionStatus(req, providerCode);
            WSDLService.airReserve.showXML(true, userName, pwd);
            AirCreateReservationPortType resv = WSDLService.airReserve.get();
            AirCreateReservationRsp reservationRsp = resv.service(req, null);
            soln.setAirPricingInfo(airPricingInfo);
            return reservationRsp;
        } catch (Exception e) {
            LOGGER.error("create pnr error", e);
            return null;
        }
    }

    public static String getAirCreateRspJson(AirPriceRsp rsp, String targetBranch, List<String> segmentStrings,
                                             Address address, List<Passenger> passengers, String areaCode,
                                             String countryCode, String location, String number, String departTime, String userName, String pwd)  {
        AirCreateReservationRsp createPnrResp = _createPnr("1G", rsp, targetBranch, segmentStrings, address, passengers, areaCode, countryCode, location, number, departTime, userName, pwd);
        try {
            //把createReservationRsp转换为 格式化Gson的自定义类
            return getJsonStr(createPnrResp);
        } catch (Exception e) {
            return AirReq.resolveExceptionMsg(e);
        }
    }

    public static OrderResp transferResp(AirCreateReservationRsp airCreateReservationResp, AirPriceRsp airPriceRsp) {
        OrderResp orderResp = new OrderResp();
        if (CollectionUtils.isNotEmpty(airCreateReservationResp.getResponseMessage())) {
            for (ResponseMessage responseMessage : airCreateReservationResp.getResponseMessage()) {
                if (StringUtils.equalsIgnoreCase(responseMessage.getType(), "error")) {
                    LOGGER.error("api create pnr error: {}", airCreateReservationResp.getResponseMessage());
                    return null;
                }
            }
        }

        UniversalRecord record = airCreateReservationResp.getUniversalRecord();
        orderResp.setPnrCodes(Lists.newArrayList(record.getLocatorCode(), record.getProviderReservationInfo().get(0).getLocatorCode()
                , record.getAirReservation().get(0).getLocatorCode()));

        orderResp.setSupplierCode(CommReq.getSupplierCode(record));
        orderResp.setAirSegment(fetchSegments(record));
        addPricing(orderResp, record, airPriceRsp);
        return orderResp;
    }

    public static String getJsonStr(AirCreateReservationRsp rsp){
        CreateReservateResponse response=new CreateReservateResponse();
        List<ResponseMessage> messages=rsp.getResponseMessage();
        if (messages.size()>0){
            String message="";
            for (ResponseMessage msg:messages) {
                message+=msg.getValue()+"/";
            }
            Map<String,String> map=new HashMap<String,String>();
            map.put("false",message);
            String jsonSt=new Gson().toJson(map);
            System.out.println(jsonSt);
            return jsonSt;
        }
        UniversalRecord record=rsp.getUniversalRecord();
        response.setPnrCode1(record.getLocatorCode());
        response.setPnrCode2(record.getProviderReservationInfo().get(0).getLocatorCode());
        response.setPnrCode3(record.getAirReservation().get(0).getLocatorCode());
        response.setSupplierCode(CommReq.getSupplierCode(record));
        response.setAirSegment(getReserSegmentList(record));
        addPricingMessage(response,record);
        Gson gson =new Gson();
        String jsonStr=gson.toJson(response);
        System.out.println(jsonStr);
        return jsonStr;
    }

    public static void addPricing(OrderResp orderResp, UniversalRecord record, AirPriceRsp airPriceRsp) {
        int totalPrice = 0;
        int basePrice = 0;
        int taxes = 0;
        String currency = "";
        List<com.sunshine.service.pojo.PricingInfo> airPricingInfo = Lists.newArrayList();
        List<AirReservation> reservations = record.getAirReservation();
        List<AirPriceResult> airPriceResult = airPriceRsp.getAirPriceResult();
        AirPricingSolution soln = CommReq.getLowPriceSoln(airPriceResult);
        List<AirPricingInfo> airPriceInfo = soln.getAirPricingInfo();
        orderResp.setPlatingCarrier(soln.getAirPricingInfo().get(0).getPlatingCarrier());

        for (AirPricingInfo info : airPriceInfo) {
            com.sunshine.service.pojo.PricingInfo customInfo = new com.sunshine.service.pojo.PricingInfo();
            String branchBasePrice = info.getApproximateBasePrice();
            String branchTotalPrice = info.getApproximateTotalPrice();
            String branchTaxes = String.valueOf(CommReq.getPrice(branchTotalPrice) - CommReq.getPrice(branchBasePrice));
            totalPrice += CommReq.getPrice(branchTotalPrice);
            basePrice += CommReq.getPrice(branchBasePrice);
            taxes += CommReq.getPrice(branchTaxes);
            currency = CommReq.getCurrency(branchBasePrice);
            customInfo.setBranchBasePrice(branchBasePrice);
            customInfo.setBranchTotalPrice(branchTotalPrice);
            customInfo.setBranchTaxes(branchTaxes);
            customInfo.setCode(info.getPassengerType().get(0).getCode());
            customInfo.setCount(info.getPassengerType().size());
            String farebasiscode = "";
            List<FareInfo> fares = info.getFareInfo();
            for (FareInfo fare : fares) {
                farebasiscode += fare.getFareBasis() + "/";
            }
            customInfo.setFareBasisCode(farebasiscode.substring(0, farebasiscode.length() - 1));
            airPricingInfo.add(customInfo);
        }
        orderResp.setAirPricingInfo(airPricingInfo);
        orderResp.setBasePrice(basePrice);
        orderResp.setTotalPrice(totalPrice);
        orderResp.setTaxes(taxes);
        orderResp.setCurrency(currency);
    }

    public static void addPricingMessage(CreateReservateResponse response, UniversalRecord record){
        int totalPrice=0;
        int basePrice=0;
        int taxes=0;
        String currency="";
        List<PricingInfo> airPricingInfo=new ArrayList<PricingInfo>();
        List<AirReservation> reservations=record.getAirReservation();
        for (AirReservation reservate:reservations) {
            response.setPlatingCarrier(reservate.getTicketingModifiers().get(0).getPlatingCarrier());
            List<AirPricingInfo> infos=reservate.getAirPricingInfo();
            for (AirPricingInfo info:infos) {
                PricingInfo customInfo=new PricingInfo();
                String branchBasePrice=info.getApproximateBasePrice();
                String branchTotalPrice=info.getApproximateTotalPrice();
                String branchTaxes=String.valueOf(CommReq.getPrice(branchTotalPrice)-CommReq.getPrice(branchBasePrice));
                totalPrice+=CommReq.getPrice(branchTotalPrice);
                basePrice+=CommReq.getPrice(branchBasePrice);
                taxes+=CommReq.getPrice(branchTaxes);
                currency=CommReq.getCurrency(branchBasePrice);
                customInfo.setBranchBasePrice(branchBasePrice);
                customInfo.setBranchTotalPrice(branchTotalPrice);
                customInfo.setBranchTaxes(branchTaxes);
                customInfo.setCode(info.getPassengerType().get(0).getCode());
                customInfo.setCount(info.getPassengerType().size());
                String farebasiscode="";
                List<FareInfo> fares=info.getFareInfo();
                for (FareInfo fare:fares) {
                    farebasiscode+=fare.getFareBasis()+"/";
                }
                customInfo.setFareBasisCode(farebasiscode.substring(0,farebasiscode.length()-1));
                airPricingInfo.add(customInfo);
            }
        }
        response.setAirPricingInfo(airPricingInfo);
        response.setBasePrice(basePrice);
        response.setTotalPrice(totalPrice);
        response.setTaxes(taxes);
        response.setCurrency(currency);
    }

    public static List<com.sunshine.service.pojo.BaseAirSegment> fetchSegments(UniversalRecord record) {
        List<com.sunshine.service.pojo.BaseAirSegment> lists = Lists.newArrayList();
        List<AirReservation> reservations=record.getAirReservation();
        for (AirReservation reservation:reservations) {
            List<TypeBaseAirSegment> segmentist=reservation.getAirSegment();
            for (TypeBaseAirSegment segment:segmentist) {
                com.sunshine.service.pojo.BaseAirSegment segment1 = new com.sunshine.service.pojo.BaseAirSegment();
                segment1.setGroup(segment.getGroup());
                segment1.setCarrier(segment.getCarrier());
                segment1.setFlightNumber(segment.getFlightNumber());
                segment1.setOrigin(segment.getOrigin());
                segment1.setCabinClass(segment.getCabinClass());
                segment1.setBookingCode(segment.getClassOfService());
                List<FlightDetails> details=segment.getFlightDetails();
                segment1.setOriginTerminal(details.get(0).getOriginTerminal());
                segment1.setDestination(segment.getDestination());
                segment1.setEquipment(details.get(0).getEquipment());
                CodeshareInfo shareinfo=segment.getCodeshareInfo();
                String operCarier=null;
                String operFlightNum=null;
                if (shareinfo!=null){
                    operCarier=shareinfo.getOperatingCarrier();
                    operFlightNum=shareinfo.getOperatingFlightNumber();
                }
                segment1.setOperatingCarrier(operCarier==null?"":operCarier);
                segment1.setOperatingFlightNumber(operFlightNum==null?"":operFlightNum);
                segment1.setDestinationTerminal(details.get(details.size()-1).getDestinationTerminal());
                segment1.setDepartureTime(segment.getDepartureTime());
                segment1.setArrivalTime(segment.getArrivalTime());
                lists.add(segment1);
            }
        }
        return lists;
    }

    public static List getReserSegmentList(UniversalRecord record){
        List<BaseAirSegment> airSegment=new ArrayList<BaseAirSegment>();
        List<AirReservation> reservations=record.getAirReservation();
        for (AirReservation reservation:reservations) {
            List<TypeBaseAirSegment> segmentist=reservation.getAirSegment();
            for (TypeBaseAirSegment segment:segmentist) {
                BaseAirSegment segment1=new BaseAirSegment();
                segment1.setGroup(segment.getGroup());
                segment1.setCarrier(segment.getCarrier());
                segment1.setFlightNumber(segment.getFlightNumber());
                segment1.setOrigin(segment.getOrigin());
                segment1.setCabinClass(segment.getCabinClass());
                segment1.setBookingCode(segment.getClassOfService());
                List<FlightDetails> details=segment.getFlightDetails();
                segment1.setOriginTerminal(details.get(0).getOriginTerminal());
                segment1.setDestination(segment.getDestination());
                segment1.setEquipment(details.get(0).getEquipment());
                CodeshareInfo shareinfo=segment.getCodeshareInfo();
                String operCarier=null;
                String operFlightNum=null;
                if (shareinfo!=null){
                    operCarier=shareinfo.getOperatingCarrier();
                    operFlightNum=shareinfo.getOperatingFlightNumber();
                }
                segment1.setOperatingCarrier(operCarier==null?"":operCarier);
                segment1.setOperatingFlightNumber(operFlightNum==null?"":operFlightNum);
                segment1.setDestinationTerminal(details.get(details.size()-1).getDestinationTerminal());
                segment1.setDepartureTime(segment.getDepartureTime());
                segment1.setArrivalTime(segment.getArrivalTime());
                airSegment.add(segment1);
            }
        }
        return airSegment;
    }




    public static List<SSR> createSSRS(List<String> segmentsRefs,String freeText){
        List<SSR> ssrs=new ArrayList<SSR>();
        for (String segment:segmentsRefs) {
            ssrs.add(CommReq.createSSR(segment,freeText));
        }
        return ssrs;
    }
    public static List<String>  getSegmentRefs(AirPriceRsp rsp){
               List<String> Stringrefs=new ArrayList<String>();
        AirPriceResult result=rsp.getAirPriceResult().get(0);
        AirPricingSolution soln=result.getAirPricingSolution().get(0);
        List<AirSegmentRef> refs=soln.getAirSegmentRef();
        for (AirSegmentRef ref:refs) {
            Stringrefs.add(ref.getKey());
        }
        return Stringrefs;
    }

    private static AirPricingSolution removeAndStitue(AirPricingSolution soln,TravleUtils.AirSegmentMap segmentMap,Map<String,List<Passenger>> adultRefs,Map<String,List<Passenger>> childRefs){
        List<AirSegmentRef> refs=soln.getAirSegmentRef();
        List<String> segmentrefs=new ArrayList<String>();
        List<TypeBaseAirSegment> segments=new ArrayList<TypeBaseAirSegment>();
        if(refs != null){
            for (AirSegmentRef ref:refs) {
                soln.getAirSegment().add(segmentMap.getByKey(ref.getKey()));
                segmentrefs.add(ref.getKey());
            }
            refs.clear();
        }
        List<AirPricingInfo> airinfos=soln.getAirPricingInfo();
        for (AirPricingInfo info:airinfos) {
            info.setPlatingCarrier(null);
            List<PassengerType>  passtypes=info.getPassengerType();
            for (int i=0;i<passtypes.size();i++){
                PassengerType type=passtypes.get(i);
                if (type.getCode().equalsIgnoreCase("ADT")){
                    Passenger pass=adultRefs.get("ADT").get(i);
                    type.setBookingTravelerRef(pass.getId());
                    type.setAge(pass.getAge());
                }else if(type.getCode().equalsIgnoreCase("CNN")){
                    Passenger pass=childRefs.get("CNN").get(i);
                    type.setBookingTravelerRef(pass.getId());
                    type.setAge(pass.getAge());
                }
            }
        }
        return soln;
    }

}
