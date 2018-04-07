package com.travelport.uapi.request;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.sunshine.service.pojo.ApiPassenger;
import com.sunshine.service.pojo.PayResp;
import com.travelport.ResponseMapping.RecordRetrieveResponse;
import com.travelport.ResponseMapping.baseEntity.BaseAirSegment;
import com.travelport.ResponseMapping.baseEntity.PricingInfo;
import com.travelport.ResponseMapping.baseEntity.Traveler;
import com.travelport.controller.BaseApiController;
import com.travelport.schema.air_v42_0.*;
import com.travelport.schema.common_v42_0.*;
import com.travelport.schema.universal_v42_0.UniversalRecord;
import com.travelport.schema.universal_v42_0.UniversalRecordRetrieveReq;
import com.travelport.schema.universal_v42_0.UniversalRecordRetrieveRsp;
import com.travelport.service.universal_v42_0.UniversalRecordArchivedFaultMessage;
import com.travelport.service.universal_v42_0.UniversalRecordFaultMessage;
import com.travelport.service.universal_v42_0.UniversalRecordRetrieveServicePortType;
import com.travelport.tutorial.support.WSDLService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UniversalRecordRetrieve {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversalRecordRetrieve.class);

    public static PayResp transferResp(UniversalRecordRetrieveRsp urRetrieveRsp) {
        if (CollectionUtils.isNotEmpty(urRetrieveRsp.getResponseMessage())) {
            for (ResponseMessage responseMessage : urRetrieveRsp.getResponseMessage()) {
                if (StringUtils.equalsIgnoreCase(responseMessage.getType(), "error")) {
                    LOGGER.error("read pnr not success: {}", urRetrieveRsp.getResponseMessage());
                    return null;
                }
            }
        }

        PayResp payResp = new PayResp();
        UniversalRecord record = urRetrieveRsp.getUniversalRecord();
        List<String> pnrCodes = Lists.newArrayList(record.getLocatorCode(), record.getProviderReservationInfo().get(0).getLocatorCode()
                , record.getAirReservation().get(0).getLocatorCode());

        payResp.setPnrCodes(pnrCodes);
        payResp.setSupplierCode(CommReq.getSupplierCode(record));
        payResp.setAirSegment(fetchAirSegments(record));
        fetchPriceInfo(payResp, record);
        payResp.setApiPassengers(getPassengers(record));
        return payResp;
    }

    public static String getJsonStr(UniversalRecordRetrieveRsp urRetrieveRsp) {
        String fault = AirReq.resolveRspFaultMsg(urRetrieveRsp);
        if (fault != null) {
            return fault;
        }
        RecordRetrieveResponse response = new RecordRetrieveResponse();
        UniversalRecord record = urRetrieveRsp.getUniversalRecord();
        response.setPnrCode1(record.getLocatorCode());
        response.setPnrCode2(record.getProviderReservationInfo().get(0).getLocatorCode());
        response.setPnrCode3(record.getAirReservation().get(0).getLocatorCode());
        response.setSupplierCode(CommReq.getSupplierCode(record));
        response.setAirSegment(getAirSegmentList(record));
        addPricingMessage(response, record);
        response.setBookingTraveler(getBookingTravlerList(record));
        Gson gson = new Gson();
        String jsonStr = gson.toJson(response);
        System.out.println(jsonStr);
        return jsonStr;
    }

    public static List<ApiPassenger> getPassengers(UniversalRecord record) {
        List<ApiPassenger> passengers = Lists.newArrayList();
        List<BookingTraveler> travelers = record.getBookingTraveler();
        for (BookingTraveler traveler : travelers) {
            BookingTravelerName name = traveler.getBookingTravelerName();
            ApiPassenger passenger = new ApiPassenger();
            passenger.setType(traveler.getTravelerType());
            passenger.setGender(traveler.getGender());
            passenger.setFirstName(name.getFirst());
            passenger.setLastName(name.getLast());
            passengers.add(passenger);
        }
        return passengers;
    }

    public static List getBookingTravlerList(UniversalRecord record) {
        List<Traveler> bookingTraveler = new ArrayList<Traveler>();
        List<BookingTraveler> travelers = record.getBookingTraveler();
        for (BookingTraveler traveler : travelers) {
            Traveler traveler1 = new Traveler();
            traveler1.setTravelerType(traveler.getTravelerType());
            traveler1.setGender(traveler.getGender());
            BookingTravelerName name = traveler.getBookingTravelerName();
            traveler1.setFirstName(name.getFirst());
            traveler1.setLastName(name.getLast());
            bookingTraveler.add(traveler1);
        }
        return bookingTraveler;
    }

    public static void fetchPriceInfo(PayResp payResp, UniversalRecord record) {
        int totalPrice = 0;
        int basePrice = 0;
        int taxes = 0;
        String currency = null;
        String platingCarrier = StringUtils.EMPTY;
        List<com.sunshine.service.pojo.PricingInfo> airPricingInfo = Lists.newArrayList();
        List<AirReservation> reservations = record.getAirReservation();
        for (AirReservation reservate : reservations) {
            List<AirPricingInfo> infos = reservate.getAirPricingInfo();
            for (AirPricingInfo info : infos) {
                com.sunshine.service.pojo.PricingInfo customInfo = new com.sunshine.service.pojo.PricingInfo();
                String branchBasePrice = info.getApproximateBasePrice();
                String branchTotalPrice = info.getApproximateTotalPrice();
                String branchTaxes = String.valueOf(CommReq.getPrice(branchTotalPrice) - CommReq.getPrice(branchBasePrice));
                totalPrice += CommReq.getPrice(branchTotalPrice);
                basePrice += CommReq.getPrice(branchBasePrice);
                taxes += CommReq.getPrice(branchTaxes);
                customInfo.setBranchBasePrice(branchBasePrice);
                customInfo.setBranchTotalPrice(branchTotalPrice);
                customInfo.setBranchTaxes(branchTaxes);
                customInfo.setCode(info.getPassengerType().get(0).getCode());
                customInfo.setCount(info.getPassengerType().size());
                StringBuilder farebasiscode = new StringBuilder();
                List<FareInfo> fares = info.getFareInfo();
                for (FareInfo fare : fares) {
                    farebasiscode.append(fare.getFareBasis()).append("/");
                }

                if (StringUtils.isBlank(platingCarrier) && StringUtils.isNotBlank(info.getPlatingCarrier())) {
                    platingCarrier = info.getPlatingCarrier();
                }

                if (currency == null) {
                    currency = CommReq.getCurrency(branchBasePrice);
                }
                customInfo.setFareBasisCode(farebasiscode.substring(0, farebasiscode.length() - 1));
                airPricingInfo.add(customInfo);
            }
        }
        payResp.setPlatingCarrier(platingCarrier);
        payResp.setAirPricingInfo(airPricingInfo);
        payResp.setBasePrice(basePrice);
        payResp.setTotalPrice(totalPrice);
        payResp.setTaxes(taxes);
        payResp.setCurrency(currency);
    }

    public static void addPricingMessage(RecordRetrieveResponse response, UniversalRecord record) {
        int totalPrice = 0;
        int basePrice = 0;
        int taxes = 0;
        String currency = null;
        List<PricingInfo> airPricingInfo = new ArrayList<PricingInfo>();
        List<AirReservation> reservations = record.getAirReservation();
        for (AirReservation reservate : reservations) {
            List<AirPricingInfo> infos = reservate.getAirPricingInfo();
            for (AirPricingInfo info : infos) {
                PricingInfo customInfo = new PricingInfo();
                String branchBasePrice = info.getApproximateBasePrice();
                String branchTotalPrice = info.getApproximateTotalPrice();
                String branchTaxes = String.valueOf(CommReq.getPrice(branchTotalPrice) - CommReq.getPrice(branchBasePrice));
                totalPrice += CommReq.getPrice(branchTotalPrice);
                basePrice += CommReq.getPrice(branchBasePrice);
                taxes += CommReq.getPrice(branchTaxes);
                customInfo.setBranchBasePrice(branchBasePrice);
                customInfo.setBranchTotalPrice(branchTotalPrice);
                customInfo.setBranchTaxes(branchTaxes);
                customInfo.setCode(info.getPassengerType().get(0).getCode());
                customInfo.setCount(info.getPassengerType().size());
                StringBuilder farebasiscode = new StringBuilder();
                List<FareInfo> fares = info.getFareInfo();
                for (FareInfo fare : fares) {
                    farebasiscode.append(fare.getFareBasis()).append("/");
                }
                if (currency == null) {
                    currency = CommReq.getCurrency(branchBasePrice);
                }
                customInfo.setFareBasisCode(farebasiscode.substring(0, farebasiscode.length() - 1));
                airPricingInfo.add(customInfo);
            }
        }
        response.setAirPricingInfo(airPricingInfo);
        response.setBasePrice(basePrice);
        response.setTotalPrice(totalPrice);
        response.setTaxes(taxes);
        response.setCurrency(currency);
    }

    public static List<com.sunshine.service.pojo.BaseAirSegment> fetchAirSegments(UniversalRecord record) {
        List<com.sunshine.service.pojo.BaseAirSegment> airSegment = Lists.newArrayList();
        List<AirReservation> reservations = record.getAirReservation();
        for (AirReservation reservate : reservations) {
            List<TypeBaseAirSegment> segments = reservate.getAirSegment();
            for (TypeBaseAirSegment segment : segments) {
                com.sunshine.service.pojo.BaseAirSegment segment1 = new com.sunshine.service.pojo.BaseAirSegment();
                segment1.setGroup(segment.getGroup());
                segment1.setCarrier(segment.getCarrier());
                segment1.setFlightNumber(segment.getFlightNumber());
                segment1.setOrigin(segment.getOrigin());
                segment1.setCabinClass(segment.getCabinClass());
                segment1.setBookingCode(segment.getClassOfService());
                List<FlightDetails> details = segment.getFlightDetails();
                segment1.setEquipment(details.get(0).getEquipment());
                segment1.setOriginTerminal(details.get(0).getOriginTerminal());
                segment1.setDestination(segment.getDestination());
                segment1.setDestinationTerminal(details.get(details.size() - 1).getDestinationTerminal());
                segment1.setDepartureTime(segment.getDepartureTime());
                segment1.setArrivalTime(segment.getArrivalTime());
                segment1.setStatusCode(segment.getStatus());
                CodeshareInfo shareinfo = segment.getCodeshareInfo();
                String operCarier = null;
                String operFlightNum = null;
                if (shareinfo != null) {
                    operCarier = shareinfo.getOperatingCarrier();
                    operFlightNum = shareinfo.getOperatingFlightNumber();
                }
                segment1.setOperatingCarrier(operCarier == null ? "" : operCarier);
                segment1.setOperatingFlightNumber(operFlightNum == null ? "" : operFlightNum);
                airSegment.add(segment1);
            }
        }
        return airSegment;
    }

    public static List getAirSegmentList(UniversalRecord record) {
        List<BaseAirSegment> airSegment = new ArrayList<BaseAirSegment>();
        List<AirReservation> reservations = record.getAirReservation();
        for (AirReservation reservate : reservations) {
            List<TypeBaseAirSegment> segments = reservate.getAirSegment();
            for (TypeBaseAirSegment segment : segments) {
                BaseAirSegment segment1 = new BaseAirSegment();
                segment1.setGroup(segment.getGroup());
                segment1.setCarrier(segment.getCarrier());
                segment1.setFlightNumber(segment.getFlightNumber());
                segment1.setOrigin(segment.getOrigin());
                segment1.setCabinClass(segment.getCabinClass());
                segment1.setBookingCode(segment.getClassOfService());
                List<FlightDetails> details = segment.getFlightDetails();
                segment1.setEquipment(details.get(0).getEquipment());
                segment1.setOriginTerminal(details.get(0).getOriginTerminal());
                segment1.setDestination(segment.getDestination());
                segment1.setDestinationTerminal(details.get(details.size() - 1).getDestinationTerminal());
                segment1.setDepartureTime(segment.getDepartureTime());
                segment1.setArrivalTime(segment.getArrivalTime());
                CodeshareInfo shareinfo = segment.getCodeshareInfo();
                String operCarier = null;
                String operFlightNum = null;
                if (shareinfo != null) {
                    operCarier = shareinfo.getOperatingCarrier();
                    operFlightNum = shareinfo.getOperatingFlightNumber();
                }
                segment1.setOperatingCarrier(operCarier == null ? "" : operCarier);
                segment1.setOperatingFlightNumber(operFlightNum == null ? "" : operFlightNum);
                airSegment.add(segment1);
            }
        }
        return airSegment;
    }

    public static PayResp readPnr(String pnrCode, String targetBranch, String usrname, String passwd) {
        UniversalRecordRetrieveReq req = new UniversalRecordRetrieveReq();
        req.setAuthorizedBy("user");
        req.setTargetBranch(targetBranch);
        req.setTraceId("trace");
        AirReq.addPointOfSale(req, "UAPI");
        req.setUniversalRecordLocatorCode(pnrCode);

        try {
            WSDLService.universalRetrieve.showXML(true, usrname, passwd);
            UniversalRecordRetrieveServicePortType urRetrieve = WSDLService.universalRetrieve.get();
            UniversalRecordRetrieveRsp urRetrieveRsp = urRetrieve.service(req, null, null);
            return transferResp(urRetrieveRsp);
        } catch (Exception e) {
            LOGGER.error("read pnr error: ", e);
            return null;
        }
    }

    public static String getRecordRetrieveJson(String pnrCode, String userName, String pwd) {
        //创建请求
        UniversalRecordRetrieveReq req = new UniversalRecordRetrieveReq();
        req.setAuthorizedBy("user");
        req.setTargetBranch("P7055095");
        req.setTraceId("trace");
        AirReq.addPointOfSale(req, "UAPI");
        req.setUniversalRecordLocatorCode(pnrCode);//pnrcode
        UniversalRecordRetrieveRsp urRetrieveRsp = null;
        try {

            WSDLService.universalRetrieve.showXML(true, userName, pwd);
            UniversalRecordRetrieveServicePortType urRetrieve = WSDLService.universalRetrieve.get();
            urRetrieveRsp = urRetrieve.service(req, null, null);
            return getJsonStr(urRetrieveRsp);
        } catch (Exception e) {
            //e.printStackTrace();
            return AirReq.resolveExceptionMsg(e);
        }

    }

}
