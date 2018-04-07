package com.travelport.uapi.request;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.travelport.schema.air_v42_0.*;
import com.travelport.schema.air_v42_0.AirSearchModifiers.PreferredProviders;
import com.travelport.schema.common_v42_0.*;
import com.travelport.schema.common_v42_0.TypeFlexibleTimeSpec.SearchExtraDays;


public class AirReq {


	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static void addItinerary(BaseAirPriceReq req, AirItinerary ai) {
		req.setAirItinerary(ai);
	}
	public static void addItinerary(BaseAirPriceReq req,List<TypeBaseAirSegment> ls) {
		AirItinerary ai=new AirItinerary();
		for (TypeBaseAirSegment AirSegment: ls) {
			ai.getAirSegment().add(AirSegment);
		}
		req.setAirItinerary(ai);
	}
	public static void addPointOfSale(BaseSearchReq req, String appName ) {
		BillingPointOfSaleInfo posInfo = new BillingPointOfSaleInfo();
		posInfo.setOriginApplication(appName);
		req.setBillingPointOfSaleInfo(posInfo);
	}
	
	public static void addPointOfSale(BaseCoreReq req, String appName ) {
		BillingPointOfSaleInfo posInfo = new BillingPointOfSaleInfo();
		posInfo.setOriginApplication(appName);
		req.setBillingPointOfSaleInfo(posInfo);
	}


	public static void addAdultPassengers(BaseLowFareSearchReq request, int n) {
		for (int i = 0; i < n; ++i) {
			SearchPassenger adult = new SearchPassenger();
			adult.setCode("ADT");
			request.getSearchPassenger().add(adult);
		}
	}



	public static SearchAirLeg createLeg(String originAirportCode,
			String destAirportCode) {
		TypeSearchLocation originLoc = new TypeSearchLocation();
		TypeSearchLocation destLoc = new TypeSearchLocation();


		Airport origin = new Airport(), dest = new Airport();
		origin.setCode(originAirportCode);
		dest.setCode(destAirportCode);

		originLoc.setAirport(origin);
		destLoc.setAirport(dest);

		return createLeg(originLoc, destLoc);
	}

	public static SearchAirLeg createLeg(TypeSearchLocation originLoc,
			TypeSearchLocation destLoc) {
		SearchAirLeg leg = new SearchAirLeg();

		leg.getSearchDestination().add(destLoc);
		leg.getSearchOrigin().add(originLoc);	
		
		AirLegModifiers legModifiers = new AirLegModifiers();

		legModifiers.setMaxJourneyTime(new Integer("10"));
		FlightType flightType = new FlightType();
		flightType.setMaxStops(new Integer("1"));
		
		legModifiers.setFlightType(flightType);
		
		leg.setAirLegModifiers(legModifiers);
		
		return leg;
	}
	

	public static TypeSearchLocation createLocationNear(String cityOrAirportCode) {
		TypeSearchLocation result = new TypeSearchLocation();

		CityOrAirport place = new CityOrAirport();
		place.setCode(cityOrAirportCode);
		place.setPreferCity(true);
		result.setCityOrAirport(place);

		Distance dist = new Distance();
		dist.setUnits("mi");
		dist.setValue(BigInteger.valueOf(50));
		result.setDistance(dist);
		
		return result;
	}
	 


	public static void addEconomyPreferred(SearchAirLeg outbound) {
		AirLegModifiers modifiers = new AirLegModifiers();
		PreferredCabins cabins = new PreferredCabins();
		CabinClass econ = new CabinClass();
		econ.setType("Economy");

		cabins.setCabinClass(econ);
		modifiers.setPreferredCabins(cabins);
		outbound.setAirLegModifiers(modifiers);
	}

	public static void addDepartureDate(SearchAirLeg outbound, String departureDate) {

		TypeFlexibleTimeSpec noFlex = new TypeFlexibleTimeSpec();
		
		SearchExtraDays extraDays = new SearchExtraDays();
		extraDays.setDaysAfter(new Integer("3"));
		extraDays.setDaysBefore(new Integer("3"));
		
		noFlex.setPreferredTime(departureDate);
		outbound.getSearchDepTime().add(noFlex);
	}


	public static AirSearchModifiers createModifiersWithProviders(String ... providerCode) {
		AirSearchModifiers modifiers = new AirSearchModifiers();
		PreferredProviders providers = new PreferredProviders();
		for (int i=0; i<providerCode.length;++i) {
			Provider p = new Provider();
			p.setCode(providerCode[i]);
			providers.getProvider().add(p);
			break;
		}
		modifiers.setPreferredProviders(providers);
		
		PermittedCarriers permittedCarriers = new PermittedCarriers();
		
		Carrier c = new Carrier();
		c.setCode("QF");
		permittedCarriers.getCarrier().add(c);
		
		modifiers.setPermittedCarriers(permittedCarriers);
		
		ProhibitedCarriers prohibitedCarriers = new ProhibitedCarriers();
		
		Carrier c1 = new Carrier();
		c1.setCode("JQ");
		prohibitedCarriers.getCarrier().add(c1);
		
		return modifiers;
	}

	public static SearchAirLeg createSearchLeg(String originAirportCode, String destAirportCode) {
		// TODO Auto-generated method stub
		TypeSearchLocation originLoc = new TypeSearchLocation();
		TypeSearchLocation destLoc = new TypeSearchLocation();

		Airport origin = new Airport(), dest = new Airport();
		origin.setCode(originAirportCode);
		dest.setCode(destAirportCode);

		originLoc.setAirport(origin);
		destLoc.setAirport(dest);

		return createSearchLeg(originLoc, destLoc);
	}

	private static SearchAirLeg createSearchLeg(TypeSearchLocation originLoc,
			TypeSearchLocation destLoc) {
		SearchAirLeg leg = new SearchAirLeg();

		leg.getSearchDestination().add(destLoc);
		leg.getSearchOrigin().add(originLoc);	
		
		
		return leg;
	}

	public static void addSearchDepartureDate(SearchAirLeg outbound,
			String departureDate) {

		TypeFlexibleTimeSpec noFlex = new TypeFlexibleTimeSpec();
		noFlex.setPreferredTime(departureDate);
		outbound.getSearchDepTime().add(noFlex);
	}

	public static void addSearchEconomyPreferred(SearchAirLeg outbound) {

		AirLegModifiers modifiers = new AirLegModifiers();
		PreferredCabins cabins = new PreferredCabins();
		CabinClass econ = new CabinClass();
		econ.setType("Economy");

		cabins.setCabinClass(econ);
		modifiers.setPreferredCabins(cabins);
		outbound.setAirLegModifiers(modifiers);
	}

	/* "1P" "1G" "4EU" */
	public static void addPCC(BaseLowFareSearchReq req, String providerCode, String pccCode) {
		PCC pcc = new PCC();
		OverridePCC overPcc = new OverridePCC();
		overPcc.setProviderCode(providerCode);
		overPcc.setPseudoCityCode(pccCode);
		pcc.setOverridePCC(overPcc);
		req.setPCC(pcc);
	}

		/* "1P" "1G" "4EU" */
	public static void addPCC(AirPriceReq req, String providerCode, String pccCode) {
		PCC p = new PCC();
		OverridePCC overPcc = new OverridePCC();
		overPcc.setProviderCode(providerCode);
		overPcc.setPseudoCityCode(pccCode);
		p.setOverridePCC(overPcc);
		req.setPCC(p);
	}

	public static void addChildPassengers(BaseLowFareSearchReq request, int n) {
		for (int i = 0; i < n; ++i) {
			SearchPassenger child = new SearchPassenger();
			child.setCode("CNN");
			child.setAge(new BigInteger("8"));
			request.getSearchPassenger().add(child);
		}
	}

	public static void addPricingModifiers(AirPriceReq req) {
			AirPricingModifiers modifiers = new AirPricingModifiers();
		modifiers.setInventoryRequestType(TypeInventoryRequest.DIRECT_ACCESS);
		modifiers.setFaresIndicator(TypeFaresIndicator.PUBLIC_AND_PRIVATE_FARES);
		req.setAirPricingModifiers(modifiers);
	}
	public static void addPricingModifiers(BaseLowFareSearchReq req,String code) {

		AirPricingModifiers modifiers=new AirPricingModifiers();
		AccountCode accountcode=new AccountCode();
		accountcode.setCode(code);
		AirPricingModifiers.AccountCodes  aa =new AirPricingModifiers.AccountCodes();
		aa.getAccountCode().add(accountcode);
		modifiers.setAccountCodes(aa);
		modifiers.setETicketability(TypeEticketability.REQUIRED);
		modifiers.setFaresIndicator(TypeFaresIndicator.PUBLIC_AND_PRIVATE_FARES);
		req.setAirPricingModifiers(modifiers);
	}
	public static void addPassengers(AirPriceReq req,List<SearchPassenger> ls) {
		for (SearchPassenger searchPassenger:ls) {
			req.getSearchPassenger().add(searchPassenger);
		}
	}
	public static void addPricingCommand(AirPriceReq req,List<AirSegmentPricingModifiers> ls) {
		AirPricingCommand command=new AirPricingCommand();
		command.getAirSegmentPricingModifiers().addAll(ls);
		req.getAirPricingCommand().add(command);
	}
	public static void addSegmentPricingModifiers(AirPriceReq req,List<AirSegmentPricingModifiers> pricingModifierss) {
		AirPricingCommand airPricingCommand=new AirPricingCommand();
		airPricingCommand.getAirSegmentPricingModifiers().addAll(pricingModifierss);
		req.getAirPricingCommand().add(airPricingCommand);
	}

	public static void addFormOfPayment(AirPriceReq req) {
		FormOfPayment fop=new FormOfPayment();
		fop.setType("Credit");
		req.getFormOfPayment().add(fop);
	}
	public static void addSpecificAirSegment(AirSearchReq req,List<SearchSpecificAirSegment> AirSegments) {
		req.getSearchSpecificAirSegment().addAll(AirSegments);
	}
	public static void addSearchModifiers(AirSearchReq req) {
		AirSearchModifiers asm=new AirSearchModifiers();
		PreferredProviders ap=new PreferredProviders();
		Provider p=new Provider();
		p.setCode("1G");
		ap.getProvider().add(p);
		asm.setPreferredProviders(ap);
		req.setAirSearchModifiers(asm);
	}
	public static void addAirLeg(LowFareSearchReq req,String originAirportCode,
								  String destAirportCode,Date date) {
		SearchAirLeg leg=new SearchAirLeg();
		TypeSearchLocation originLoc = new TypeSearchLocation();
		TypeSearchLocation destLoc = new TypeSearchLocation();

		CityOrAirport origin = new CityOrAirport(), dest = new CityOrAirport();
		origin.setCode(originAirportCode);
		origin.setPreferCity(true);
		dest.setCode(destAirportCode);
		originLoc.setCityOrAirport(origin);
		dest.setPreferCity(true);
		destLoc.setCityOrAirport(dest);

		leg.getSearchOrigin().add(originLoc);
		leg.getSearchDestination().add(destLoc);
		leg.getSearchDepTime().add(createDepTime(date));
		req.getSearchAirLeg().add(leg);
	}

	public static void addAirLeg(LowFareSearchReq req,String originAirportCode,Date date) {
		SearchAirLeg leg=new SearchAirLeg();
		TypeSearchLocation originLoc = new TypeSearchLocation();
		CityOrAirport origin = new CityOrAirport();
		origin.setCode(originAirportCode);
		origin.setPreferCity(true);
		originLoc.setCityOrAirport(origin);
		leg.getSearchOrigin().add(originLoc);
		leg.getSearchDepTime().add(createDepTime(date));
		req.getSearchAirLeg().add(leg);
	}
	public static  TypeFlexibleTimeSpec createDepTime(Date date){
		TypeFlexibleTimeSpec time=new TypeFlexibleTimeSpec();
		time.setPreferredTime(dateFormat.format(date));
		return time;
	}

	public static void addSearchModifiersWithProviders(BaseAirSearchReq req, String providerCode) {
		AirSearchModifiers modifiers = new AirSearchModifiers();
		PreferredProviders providers = new PreferredProviders();
		Provider p = new Provider();
		p.setCode(providerCode);
		providers.getProvider().add(p);
		modifiers.setPreferredProviders(providers);
		req.setAirSearchModifiers(modifiers);
	}
	public static String resolveExceptionMsg(Exception e){
		Fault fault=new Fault();
		fault.setSuccess(false);
		fault.setMessage(e.getMessage());
		Gson gson=new Gson();
		String jsonStr=gson.toJson(fault);
		System.out.println(jsonStr);
		return jsonStr;
	}
	public static String resolveRspFaultMsg(BaseRsp rsp){
		List<ResponseMessage> messages=rsp.getResponseMessage();
		String jsonStr=null;
		if (messages.size()>0){
			String message="";
			for (ResponseMessage msg:messages) {
				message+=msg.getValue()+"/";
			}
			Fault fault=new Fault();
			fault.setSuccess(false);
			fault.setMessage(message);
			Gson gson=new Gson();
			jsonStr=gson.toJson(fault);
			System.out.println(jsonStr);
		}
		return jsonStr;
	}
}
