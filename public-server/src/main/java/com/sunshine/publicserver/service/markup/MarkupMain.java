package com.sunshine.publicserver.service.markup;

import com.alibaba.fastjson.JSONObject;
import com.sunshine.publicserver.service.markup.pojo.markup.MarkupParameter;
import com.sunshine.publicserver.service.markup.pojo.markup.MarkupResult;
import com.sunshine.publicserver.service.markup.util.MarkupParameterXmlUtils;
import com.sunshine.publicserver.service.markup.util.NewWhiteListURL;
import com.sunshine.publicserver.service.markup.util.XmlConvertJsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MarkupMain {

	private final static String URL = "http://120.76.25.174:8080/MarkupSvc_OTA/Xml/yzK9QIkEoZIKtGzt0TELpQ%3d%3d";

	private static Logger LOGGER = LoggerFactory.getLogger(MarkupMain.class);

	public static MarkupResult markup(MarkupParameter markupParameterVo)
			throws Exception {
		String params = getXmlInfo(markupParameterVo);
		String xml = NewWhiteListURL.http(params, URL);
		if (StringUtils.isBlank(xml)) {
			LOGGER.error("null xml result");
			return null;
		}
		String json = XmlConvertJsonUtils.parseJSON(xml);
		MarkupResult respStr = JSONObject.parseObject(json, MarkupResult.class);
		return respStr;
	}

	private static String getXmlInfo(MarkupParameter markupParameterVo)
			throws Exception {
		if (null == markupParameterVo) {
			throw new Exception("MarkupParameter不能为空");
		}
		if (null == markupParameterVo.getTripType() || markupParameterVo.getTripType() =="") {
			throw new Exception("TripType不能为空");
		}
		if (null ==markupParameterVo.getAirline() ||markupParameterVo.getAirline() =="") {
			throw new Exception("Airline不能为空");
		}
		if (null == markupParameterVo.getDepartureDateTime() || markupParameterVo.getDepartureDateTime() =="") {
			throw new Exception("DepartureDateTime不能为空");
		}
		if (null ==markupParameterVo.getDepartureAirport()||markupParameterVo.getDepartureAirport()=="") {
			throw new Exception("DepartureAirport不能为空");
		}
		if (null ==markupParameterVo.getDestinations()) {
			throw new Exception("Destinations不能为空");
		}
		if (null ==markupParameterVo.getBookingClasses()) {
			throw new Exception("BookingClasses不能为空");
		}
		if (null ==markupParameterVo.getFareBasises()) {
			throw new Exception("FareBasises不能为空");
		}
		if (null ==markupParameterVo.getFlightNumbers()) {
			throw new Exception("FlightNumbers不能为空");
		}
		if (null ==markupParameterVo.getMarketAirlines()) {
			throw new Exception("MarketAirlines不能为空");
		}
		return MarkupParameterXmlUtils.createXml(markupParameterVo);
	}

	public static void main(String[] args) throws Exception {
		MarkupParameter markupParameterVo = new MarkupParameter();
		markupParameterVo.setId(1232);
		markupParameterVo.setFareType("PublishedFare");
		markupParameterVo.setTripType("Oneway"); // 单程还是往返
		markupParameterVo.setAirline("FJ"); // 航空公司
		markupParameterVo.setDepartureDateTime("2017-12-08T16:25:00");// 起飞时间
		markupParameterVo.setDepartureAirport("HKG");// 出发地（机场代码）
		markupParameterVo.setDestinations(new String[] { "NAN" });// 目的地（机场代码）
		markupParameterVo.setBookingClasses(new String[] { "Y" });// 所有航段不重复的舱位，例如Q,Z
		markupParameterVo.setFareBasises(new String[] { "BULA" });// 所有航段不重复的票价基础
		markupParameterVo.setFlightNumbers(new String[] { "FJ888" });// 所有航段不重复的航班号，如CZ338,CZ3061
		markupParameterVo.setMarketAirlines(new String[] { "FJ" });// 所有航段不重复的航空公司
		markupParameterVo.setOperatorAirlines(new String[] { "" });// 所有航段的共享航空公司
		markup(markupParameterVo);
	}
}