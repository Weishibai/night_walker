package com.travelport.tutorial.support;

import com.travelport.service.air_v42_0.*;
import com.travelport.service.air_v42_0.AirService;
import com.travelport.service.hotel_v42_0.HotelDetailsServicePortType;
import com.travelport.service.hotel_v42_0.HotelMediaLinksServicePortType;
import com.travelport.service.hotel_v42_0.HotelSearchServicePortType;
import com.travelport.service.hotel_v42_0.HotelService;
import com.travelport.service.system_v32_0.SystemInfoPortType;
import com.travelport.service.system_v32_0.SystemPingPortType;
import com.travelport.service.system_v32_0.SystemService;
import com.travelport.service.system_v32_0.SystemTimePortType;
import com.travelport.service.universal_v42_0.*;
import com.travelport.service.util_v42_0.ReferenceDataRetrievePortType;
import com.travelport.service.util_v42_0.ReferenceDataRetrieveService;
import com.travelport.service.vehicle_v42_0.VehicleSearchServicePortType;
import com.travelport.service.vehicle_v42_0.VehicleService;
import com.travelport.uapi.request.TravelportConfig;
import com.travelport.util.PropertyUtil;

import java.net.MalformedURLException;
import java.net.URL;





public class WSDLService {

	static {
		int x = 0;

	}

	// wsdl文件目录
	static public String URLPREFIX = WSDLService.class.getClassLoader().getResource("wsdl") + "/";

	static public String SYSTEM_WSDL = "system_v32_0/System.wsdl";
	static public String AIR_WSDL = "air_v42_0/Air.wsdl";
	static public String HOTEL_WSDL = "hotel_v42_0/Hotel.wsdl";
	static public String VEHICLE_WSDL = "vehicle_v42_0/Vehicle.wsdl";
	static public String UNIVERSAL_WSDL = "universal_v42_0/UniversalRecord.wsdl";
	static public String UTIL_WSDL = "util_v42_0/Util.wsdl";


//	static public String ENDPOINT_PREFIX = TravelportConfig.ENDPOINT_PREFIX;
	public static String ENDPOINT_PREFIX = PropertyUtil.getProperty("endpointUrl");

	static public String SYSTEM_ENDPOINT = ENDPOINT_PREFIX + "SystemService";
	static public String AIR_ENDPOINT = ENDPOINT_PREFIX + "AirService";
	static public String HOTEL_ENDPOINT = ENDPOINT_PREFIX + "HotelService";
	static public String VEHICLE_ENDPOINT = ENDPOINT_PREFIX + "VehicleService";
	static public String UNIVERSAL_ENDPOINT = ENDPOINT_PREFIX + "UniversalRecordService";
	static public String UTIL_ENDPOINT = ENDPOINT_PREFIX + "UtilService";
	


	public static ServiceWrapper<HotelService> hotel = new ServiceWrapper<HotelService>(WSDLService.HOTEL_WSDL, HotelService.class);

	public static ServiceWrapper<AirService> air = new ServiceWrapper<AirService>(
			WSDLService.AIR_WSDL, AirService.class);
	public static ServiceWrapper<SystemService> sys = new ServiceWrapper<SystemService>(
			WSDLService.SYSTEM_WSDL, SystemService.class);
	public static ServiceWrapper<VehicleService> vehicle = new ServiceWrapper<VehicleService>(
			WSDLService.VEHICLE_WSDL, VehicleService.class);
	public static ServiceWrapper<com.travelport.service.universal_v42_0.HotelService> universalHotel = new ServiceWrapper<com.travelport.service.universal_v42_0.HotelService>(
			WSDLService.UNIVERSAL_WSDL, com.travelport.service.universal_v42_0.HotelService.class);
	
	public static ServiceWrapper<com.travelport.service.universal_v42_0.AirService> universalAir = new ServiceWrapper<com.travelport.service.universal_v42_0.AirService>(
			WSDLService.UNIVERSAL_WSDL, com.travelport.service.universal_v42_0.AirService.class);
	public static ServiceWrapper<UniversalRecordService> universal = new ServiceWrapper<UniversalRecordService>(
			WSDLService.UNIVERSAL_WSDL, UniversalRecordService.class);
	public static ServiceWrapper<ReferenceDataRetrieveService> referenceDataRetrieve = new ServiceWrapper<ReferenceDataRetrieveService>(
			WSDLService.UTIL_WSDL, ReferenceDataRetrieveService.class);

	public static ServiceWrapper<UniversalRecordCancelService> universalCancel = new ServiceWrapper<UniversalRecordCancelService>(
			WSDLService.UNIVERSAL_WSDL, UniversalRecordCancelService.class);

	public static PortWrapper<AirLowFareSearchPortType, AirService> airShop = new PortWrapper<AirLowFareSearchPortType, AirService>(
			air, AirLowFareSearchPortType.class, AIR_ENDPOINT);
	public static PortWrapper<AirLowFareSearchAsynchPortType, AirService> airShopAsync = new PortWrapper<AirLowFareSearchAsynchPortType, AirService>(
			air, AirLowFareSearchAsynchPortType.class, AIR_ENDPOINT);
	public static PortWrapper<AirAvailabilitySearchPortType, AirService> airAvailability = new PortWrapper<AirAvailabilitySearchPortType, AirService>(
			air, AirAvailabilitySearchPortType.class, AIR_ENDPOINT);
	public static PortWrapper<AirRetrieveLowFareSearchPortType, AirService> airRetrieve = new PortWrapper<AirRetrieveLowFareSearchPortType, AirService>(
			air, AirRetrieveLowFareSearchPortType.class, AIR_ENDPOINT);
	public static PortWrapper<AirPricePortType, AirService> airPrice = new PortWrapper<AirPricePortType, AirService>(
			air, AirPricePortType.class, AIR_ENDPOINT);
	public static PortWrapper<AirCreateReservationPortType, com.travelport.service.universal_v42_0.AirService> airReserve = new PortWrapper<AirCreateReservationPortType, com.travelport.service.universal_v42_0.AirService>(
			universalAir, AirCreateReservationPortType.class, AIR_ENDPOINT);
	public static PortWrapper<AirTicketingPortType, AirService> airTicket = new PortWrapper<AirTicketingPortType, AirService>(
			air, AirTicketingPortType.class, AIR_ENDPOINT);


	public static PortWrapper<SystemPingPortType, SystemService> sysPing = new PortWrapper<SystemPingPortType, SystemService>(
			sys, SystemPingPortType.class, SYSTEM_ENDPOINT);
	public static PortWrapper<SystemInfoPortType, SystemService> sysInfo = new PortWrapper<SystemInfoPortType, SystemService>(
			sys, SystemInfoPortType.class, SYSTEM_ENDPOINT);
	public static PortWrapper<SystemTimePortType, SystemService> sysTime = new PortWrapper<SystemTimePortType, SystemService>(
			sys, SystemTimePortType.class, SYSTEM_ENDPOINT);


	public static PortWrapper<HotelReservationServicePortType, com.travelport.service.universal_v42_0.HotelService> hotelReserve = new PortWrapper<HotelReservationServicePortType, com.travelport.service.universal_v42_0.HotelService>(
			universalHotel, HotelReservationServicePortType.class, HOTEL_ENDPOINT);
	public static PortWrapper<HotelSearchServicePortType, HotelService> hotelShop = new PortWrapper<HotelSearchServicePortType, HotelService>(
			hotel, HotelSearchServicePortType.class, HOTEL_ENDPOINT);
	public static PortWrapper<HotelDetailsServicePortType, HotelService> hotelDetails = new PortWrapper<HotelDetailsServicePortType, HotelService>(
			hotel, HotelDetailsServicePortType.class, HOTEL_ENDPOINT);
	public static PortWrapper<HotelCancelServicePortType, HotelService> hotelCancel = new PortWrapper<HotelCancelServicePortType, HotelService>(
			hotel, HotelCancelServicePortType.class, HOTEL_ENDPOINT);
	public static PortWrapper<HotelMediaLinksServicePortType, HotelService> hotelMedia = new PortWrapper<HotelMediaLinksServicePortType, HotelService>(
			hotel, HotelMediaLinksServicePortType.class, HOTEL_ENDPOINT);


	public static PortWrapper<VehicleSearchServicePortType, VehicleService> vehicleSearch = new PortWrapper<VehicleSearchServicePortType, VehicleService>(
			vehicle, VehicleSearchServicePortType.class, VEHICLE_ENDPOINT);

	public static PortWrapper<UniversalRecordRetrieveServicePortType, UniversalRecordService> universalRetrieve = new PortWrapper<UniversalRecordRetrieveServicePortType, UniversalRecordService>(
			universal, UniversalRecordRetrieveServicePortType.class, UNIVERSAL_ENDPOINT);
	public static PortWrapper<UniversalRecordCancelServicePortType, UniversalRecordCancelService> universalCance= new PortWrapper<UniversalRecordCancelServicePortType, UniversalRecordCancelService>(
			universalCancel, UniversalRecordCancelServicePortType.class, UNIVERSAL_ENDPOINT);

	public static PortWrapper<ReferenceDataRetrievePortType, ReferenceDataRetrieveService> referenceRetrieve = new PortWrapper<ReferenceDataRetrievePortType, ReferenceDataRetrieveService>(
			referenceDataRetrieve, ReferenceDataRetrievePortType.class, UTIL_ENDPOINT);



	public static void checkProperties() {
		String user= TravelportConfig.USER_NAME;
		String pwd=TravelportConfig.PASS_WORD;
		String gds=TravelportConfig.GDS;
		String tb=TravelportConfig.TARGET_BRANCH;
		if ((user == null) || (pwd == null) || (gds == null) || (tb == null)) {
			throw new RuntimeException(
					"请设置TravelPortConfig文件中的"
							+ "USER_NAME"
							+ ","
							+ "PASS_WORD"
							+ ","
							+ "GDS"
							+ ","
							+ "TARGET_BRANCH"
							);
		}
	}


	public static URL getURLForWSDL(String wsdlFileInThisProject) {
		try {
			//System.out.println(URLPREFIX + wsdlFileInThisProject);
			URL url = new URL(URLPREFIX + wsdlFileInThisProject);
			return url;
		} catch (MalformedURLException e) {
			throw new RuntimeException("在此路径下没有找到wsdl文件:" + URLPREFIX + AIR_WSDL);

		}
	}


}
