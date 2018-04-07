package com.sunshine.publicserver.service.markup;

import com.google.common.collect.Lists;
import com.sunshine.publicserver.service.markup.pojo.epibolyMarkup.RequestMarkup;
import com.sunshine.publicserver.service.markup.pojo.markup.MarkupModes;
import com.sunshine.publicserver.service.markup.pojo.markup.MarkupParameter;
import com.sunshine.publicserver.service.markup.pojo.markup.MarkupResult;
import com.sunshine.publicserver.vo.MarkupInfo;
import com.sunshine.publicserver.vo.MarkupRequest;
import com.sunshine.publicserver.vo.MarkupResponse;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Markup {

	private final static String FARE_TYPE = "PublishedFare";

	public static MarkupResponse markup(MarkupRequest markupRequest) throws Exception {
		MarkupParameter markupParameterVo = convertrequestMarkup(markupRequest);
		MarkupResult markupResult = MarkupMain.markup(markupParameterVo);
		if (markupResult == null)
			return null;
		return convertResult(markupResult);
	}

//	public static ResultMarkup markup(RequestMarkup requestMarkup)
//			throws Exception {
//		MarkupParameter markupParameterVo = convertrequestMarkup(requestMarkup);
//		MarkupResult markupResult = MarkupMain.markup(markupParameterVo);
//		if (markupResult == null)
//			return null;
//		ResultMarkup infos = convertResultMarkup(markupResult);
//		System.out.println(JSON.toJSONString(infos));
//		return infos;
//	}

	private static MarkupParameter convertrequestMarkup(MarkupRequest markupRequest) {
		MarkupParameter markupParameterVo = new MarkupParameter();
		markupParameterVo.setFareType(FARE_TYPE);
		markupParameterVo.setTripType(markupRequest.getTripType().name());
		markupParameterVo.setAirline(markupRequest.getAirline());
		markupParameterVo.setDepartureDateTime(markupRequest.getDepartureDateTime());
		markupParameterVo.setDepartureAirport(markupRequest.getDepartureAirport());
		markupParameterVo.setDestinations(markupRequest.getDestinations());
		markupParameterVo.setBookingClasses(markupRequest.getBookingClasses());
		markupParameterVo.setFareBasises(markupRequest.getFareBasises());
		markupParameterVo.setFlightNumbers(markupRequest.getFlightNumbers());
		markupParameterVo.setMarketAirlines(markupRequest.getMarketAirlines());
		markupParameterVo.setOperatorAirlines(markupRequest.getOperatorAirlines());
		return markupParameterVo;
	}

	private static MarkupParameter convertrequestMarkup(
			RequestMarkup requestMarkup) {
		MarkupParameter markupParameterVo = new MarkupParameter();
		markupParameterVo.setFareType(FARE_TYPE);
		markupParameterVo.setTripType(requestMarkup.getTripType());
		markupParameterVo.setAirline(requestMarkup.getAirline());
		markupParameterVo.setDepartureDateTime(requestMarkup
				.getDepartureDateTime());
		markupParameterVo.setDepartureAirport(requestMarkup
				.getDepartureAirport());
		markupParameterVo.setDestinations(requestMarkup.getDestinations());
		markupParameterVo.setBookingClasses(requestMarkup.getBookingClasses());
		markupParameterVo.setFareBasises(requestMarkup.getFareBasises());
		markupParameterVo.setFlightNumbers(requestMarkup.getFlightNumbers());
		markupParameterVo.setMarketAirlines(requestMarkup.getMarketAirlines());
		markupParameterVo.setOperatorAirlines(requestMarkup
				.getOperatorAirlines());
		return markupParameterVo;
	}

//	private static ResultMarkup convertResultMarkup(MarkupResult markupResult) {
//		ResultMarkup markup = new ResultMarkup();
//		// 成功或者失败的信息
//		markup.setErrorMsg(markupResult.getErrorMsg());
//		markup.setSuccess(markupResult.getSuccess());
//		// 存放所有内容的集合
//		List<List<Markupinfo>> markupinfos = new ArrayList<List<Markupinfo>>();
//		// int rank = markupResult.getMarkupItems().size();
//		// 判断items是否含有空值
//		if (null != markupResult && markupResult.getMarkupItems().size() >= 1) {
//			// 循环获取items值
//			for (int i = 0; i < markupResult.getMarkupItems().size(); i++) {
//				List<Markupinfo> list2 = new ArrayList<Markupinfo>();
//				// 存放items的对象
//				Markupinfo markupinfo1 = new Markupinfo();
//				markupinfo1.setMarkupBy(markupResult.getMarkupItems().get(i).getMarkupBy());
//				markupinfo1.setMarkupValue(markupResult.getMarkupItems().get(i).getMarkupValue());
//				markupinfo1.setRank(0);
//				list2.add(markupinfo1);
//				if (markupResult.getMarkupItems().get(i).getMarkupModes() != null) {
//					// 进行排序
//					List<MarkupModes> mo = markupResult.getMarkupItems().get(i)
//							.getMarkupModes();
//					Collections.sort(mo, new Comparator<MarkupModes>() {
//						@Override
//						public int compare(MarkupModes o1, MarkupModes o2) {
//							return o1.getRank().compareTo(o2.getRank());
//						}
//					});
//					for (int j = 0; j < markupResult.getMarkupItems().get(i)
//							.getMarkupModes().size(); j++) {
//						Markupinfo markupinfo = new Markupinfo();
//						markupinfo.setMarkupBy(markupResult.getMarkupItems()
//								.get(i).getMarkupModes().get(j).getMarkupBy());
//						markupinfo.setMarkupValue(markupResult.getMarkupItems()
//								.get(i).getMarkupModes().get(j)
//								.getMarkupValue());
//						markupinfo.setRank(j + 1);
//						list2.add(markupinfo);
//					}
//				}
//				markupinfos.add(list2);
//			}
//		}
//		markup.setMarkupinfos(markupinfos);
//		markup.setOriginal(markupResult);
//		return markup;
//	}

	private static MarkupResponse convertResult(MarkupResult markupResult) {
		if (null == markupResult) {
			return null;
		}

		MarkupResponse markupResponse = new MarkupResponse();
		// 成功或者失败的信息
		markupResponse.setErrorMsg(markupResult.getErrorMsg());
		markupResponse.setSuccess(markupResult.getSuccess());

		// 存放所有内容的集合
		List<List<MarkupInfo>> markupinfos = Lists.newArrayList();
		// int rank = markupResult.getMarkupItems().size();
		// 判断items是否含有空值
		if (!CollectionUtils.isEmpty(markupResult.getMarkupItems())) {
			// 循环获取items值
			for (int i = 0; i < markupResult.getMarkupItems().size(); i++) {
				List<MarkupInfo> list2 = Lists.newArrayList();
				// 存放items的对象
				MarkupInfo markupinfo1 = new MarkupInfo();
				markupinfo1.setMarkupBy(markupResult.getMarkupItems().get(i).getMarkupBy());
				markupinfo1.setMarkupValue(markupResult.getMarkupItems().get(i).getMarkupValue());
				markupinfo1.setRank(0);
				list2.add(markupinfo1);
				if (markupResult.getMarkupItems().get(i).getMarkupModes() != null) {
					// 进行排序
					List<MarkupModes> mo = markupResult.getMarkupItems().get(i).getMarkupModes();

					Collections.sort(mo, new Comparator<MarkupModes>() {
						@Override
						public int compare(MarkupModes o1, MarkupModes o2) {
							return o1.getRank().compareTo(o2.getRank());
						}
					});
					for (int j = 0; j < markupResult.getMarkupItems().get(i)
							.getMarkupModes().size(); j++) {
						MarkupInfo markupinfo = new MarkupInfo();
						markupinfo.setMarkupBy(markupResult.getMarkupItems()
								.get(i).getMarkupModes().get(j).getMarkupBy());
						markupinfo.setMarkupValue(markupResult.getMarkupItems()
								.get(i).getMarkupModes().get(j)
								.getMarkupValue());
						markupinfo.setRank(j + 1);
						list2.add(markupinfo);
					}
				}
				markupinfos.add(list2);
			}
		}
		markupResponse.setMarkupinfos(markupinfos);
		return markupResponse;
	}

//	public static void main(String[] args) throws Exception {
//		RequestMarkup markupParameterVo = new RequestMarkup();
//		markupParameterVo.setTripType("Oneway"); // 单程还是往返
//		markupParameterVo.setAirline("FJ"); // 航空公司
//		markupParameterVo.setDepartureDateTime("2017-12-08T16:25:00");// 起飞时间
//		markupParameterVo.setDepartureAirport("HKG");// 出发地（机场代码）
//		markupParameterVo.setDestinations(new String[] { "NAN","ZZZ" });// 目的地（机场代码）
//		markupParameterVo.setBookingClasses(new String[] { "Y","Z" });// 所有航段不重复的舱位，例如Q,Z
//		markupParameterVo.setFareBasises(new String[] { "BULA","X"});// 所有航段不重复的票价基础
//		markupParameterVo.setFlightNumbers(new String[] { "FJ888" ,"V"});// 所有航段不重复的航班号，如CZ338,CZ3061
//		markupParameterVo.setMarketAirlines(new String[] { "FJ" ,"SS"});// 所有航段不重复的航空公司
//		markupParameterVo.setOperatorAirlines(new String[] { "","VV" });// 所有航段的共享航空公司
//		markup(markupParameterVo);
//	}
}