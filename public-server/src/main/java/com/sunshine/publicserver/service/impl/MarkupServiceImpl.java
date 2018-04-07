package com.sunshine.publicserver.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.publicserver.service.IMarkupService;
import com.sunshine.publicserver.service.markup.Markup;
import com.sunshine.publicserver.service.markup.pojo.markup.MarkupParameter;
import com.sunshine.publicserver.service.markup.pojo.markup.MarkupResult;
import com.sunshine.publicserver.utils.ArrayUtil;
import com.sunshine.publicserver.utils.HttpClientUtils;
import com.sunshine.publicserver.vo.MarkupInfo;
import com.sunshine.publicserver.vo.MarkupRequest;
import com.sunshine.publicserver.vo.MarkupRequest.TripType;
import com.sunshine.publicserver.vo.MarkupResponse;
import com.sunshine.service.pojo.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MarkupServiceImpl implements IMarkupService {

	private static Logger LOGGER = LoggerFactory.getLogger(MarkupServiceImpl.class);

	@Value("${markupUrl}")
	private String markupUrl;

	private static int CONN_TIMEOUT = 30 * 1000;

	private static int SO_TIMEOUT = 30 * 1000;

	private static Header[] header = new Header[]{new BasicHeader("Content-Type", "binary/octet-stream")};

	private ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

	private MarkupResponse markupCore(MarkupRequest markupRequest) {
		try {
			return Markup.markup(markupRequest);
		} catch (Exception e) {
			LOGGER.error("invoke markup interface error: ", e);
			return null;
		}
	}

	@Override
	public Map<Integer, MarkupResponse> markup(FareSearchResponse fareSearchResponse) {
		final Map<Integer, MarkupResponse> markupResponses = Maps.newHashMap();
		List<FlightCombination> flightCombinations = fareSearchResponse.getFlightCombinations();
		AtomicInteger counter = new AtomicInteger(0);
		final CountDownLatch countDownLatch = new CountDownLatch(org.apache.commons.collections.CollectionUtils.size(flightCombinations));
		for (FlightCombination flightCombination : flightCombinations) {
			final int count = counter.incrementAndGet();
			final MarkupRequest markupRequest = buildMarkupRequest(flightCombination, fareSearchResponse.getFlyType());
			listeningExecutorService.submit(new Runnable() {
				@Override
				public void run() {
					try {
						if (null == markupRequest) {
							return;
						}

//						String resp = HttpClientUtils.post(markupUrl, JsonUtil.writeString(markupRequest), header, CONN_TIMEOUT, SO_TIMEOUT);
//						MarkupResponse markupResponse = JsonUtil.readValue(resp, MarkupResponse.class);

						MarkupResponse markupResponse = markupCore(markupRequest);
						if (markupResponse != null) {
                            markupResponses.put(count, markupResponse);
						}
					} finally {
						countDownLatch.countDown();
					}
				}
			});
		}

		try {
			if (countDownLatch.await(3, TimeUnit.SECONDS)) {
				return markupResponses;
			}
			return Collections.emptyMap();
		} catch (InterruptedException e) {
			return Collections.emptyMap();
		}
	}

	private MarkupRequest buildMarkupRequest(FlightCombination flightCombination, int flyType) {

		try {
			MarkupRequest markupRequest = new MarkupRequest();
			markupRequest.setTripType(flyType == 2 ? TripType.Roundtrip : TripType.Oneway);
			markupRequest.setAirline(flightCombination.getPlatingcarrier());
			List<BaseAirSegment> airSegments = flightCombination.getAirSegment();
			BaseAirSegment firstSegment = Iterables.getFirst(flightCombination.getAirSegment(), null);
			markupRequest.setDepartureDateTime(firstSegment.getDepartureTime());
			markupRequest.setDepartureAirport(firstSegment.getOrigin());

			List<String> destinations = Lists.newArrayList();
			for (int i = 0; i < airSegments.size(); i ++) {
				destinations.add(airSegments.get(i).getDestination());
			}
			markupRequest.setDestinations(ArrayUtil.tranfer(destinations.toArray()));

			LinkedHashSet<String> cabins = Sets.newLinkedHashSet();
			LinkedHashSet<String> flightNums = Sets.newLinkedHashSet();
			LinkedHashSet<String> marketAirs = Sets.newLinkedHashSet();
			LinkedHashSet<String> operateAirs = Sets.newLinkedHashSet();

			for (BaseAirSegment baseAirSegment : flightCombination.getAirSegment()) {
                cabins.add(baseAirSegment.getBookingCode());
                flightNums.add(baseAirSegment.getCarrier() + baseAirSegment.getFlightNumber());
                marketAirs.add(baseAirSegment.getCarrier());
                operateAirs.add(StringUtils.isBlank(baseAirSegment.getOperatingCarrier()) ? baseAirSegment.getCarrier() : baseAirSegment.getOperatingCarrier());
            }

			LinkedHashSet<String> farebasises = Sets.newLinkedHashSet(Lists.transform(flightCombination.getFarelist(), new Function<FareInfo, String>() {
                @Override
                public String apply(FareInfo input) {
                    return input.getFareBasisCode();
                }
            }));

			markupRequest.setBookingClasses(ArrayUtil.tranfer(cabins.toArray()));
			markupRequest.setFareBasises(ArrayUtil.tranfer(farebasises.toArray()));
			markupRequest.setFlightNumbers(ArrayUtil.tranfer(flightNums.toArray()));
			markupRequest.setMarketAirlines(ArrayUtil.tranfer(marketAirs.toArray()));
			markupRequest.setOperatorAirlines(ArrayUtil.tranfer(operateAirs.toArray()));
			LOGGER.debug("search markup request: {}", markupRequest);
			return markupRequest;
		} catch (Exception e) {
			LOGGER.error("build markup error: ", e);
			return null;
		}
	}

	private MarkupRequest buildMarkupRequest(String platingCarrier, List<BaseAirSegment> airSegments, List<PricingInfo> pricingInfos, int tripType) {
		try {
			MarkupRequest markupRequest = new MarkupRequest();
			markupRequest.setTripType(2 == tripType ? TripType.Roundtrip : TripType.Oneway);
			markupRequest.setAirline(StringUtils.isNotBlank(platingCarrier) ? platingCarrier : airSegments.get(0).getCarrier());

			BaseAirSegment firstSegment = Iterables.getFirst(airSegments, null);
			List<String> destinations = Lists.newArrayList();
			for (int i = 0; i < airSegments.size(); i ++) {
				destinations.add(airSegments.get(i).getDestination());
			}
			markupRequest.setDestinations(ArrayUtil.tranfer(destinations.toArray()));

			markupRequest.setDepartureDateTime(firstSegment.getDepartureTime());
			markupRequest.setDepartureAirport(firstSegment.getOrigin());

			LinkedHashSet<String> cabins = Sets.newLinkedHashSet();
			LinkedHashSet<String> flightNums = Sets.newLinkedHashSet();
			LinkedHashSet<String> marketAirs = Sets.newLinkedHashSet();
			LinkedHashSet<String> operateAirs = Sets.newLinkedHashSet();

			for (BaseAirSegment baseAirSegment : airSegments) {
				cabins.add(baseAirSegment.getBookingCode());
				flightNums.add(baseAirSegment.getCarrier() + baseAirSegment.getFlightNumber());
				marketAirs.add(baseAirSegment.getCarrier());
				operateAirs.add(StringUtils.isBlank(baseAirSegment.getOperatingCarrier()) ? baseAirSegment.getCarrier() : baseAirSegment.getOperatingCarrier());
			}

			LinkedHashSet<String> farebasises = Sets.newLinkedHashSet(Lists.transform(pricingInfos, new Function<PricingInfo, String>() {
				@Override
				public String apply(PricingInfo input) {
					return input.getFareBasisCode();
				}
			}));

			markupRequest.setBookingClasses(ArrayUtil.tranfer(cabins.toArray()));
			markupRequest.setFareBasises(ArrayUtil.tranfer(farebasises.toArray()));
			markupRequest.setFlightNumbers(ArrayUtil.tranfer(flightNums.toArray()));
			markupRequest.setMarketAirlines(ArrayUtil.tranfer(marketAirs.toArray()));
			markupRequest.setOperatorAirlines(ArrayUtil.tranfer(operateAirs.toArray()));
			LOGGER.debug("order markup request: {}", markupRequest);
			return markupRequest;
		} catch (Exception e) {
			LOGGER.error("build markup error: ", e);
			return null;
		}
	}

	private MarkupRequest buildMarkupRequest(VerifyResp verifyResp) {
		try {
			MarkupRequest markupRequest = new MarkupRequest();
			markupRequest.setTripType(2 == verifyResp.getTripType() ? TripType.Roundtrip : TripType.Oneway);
			markupRequest.setAirline(verifyResp.getPlatingCarrier());
			List<BaseAirSegment> airSegments = verifyResp.getAirSegment();
			BaseAirSegment firstSegment = Iterables.getFirst(airSegments, null);
			markupRequest.setDepartureDateTime(firstSegment.getDepartureTime());
			markupRequest.setDepartureAirport(firstSegment.getOrigin());
			List<String> destinations = Lists.newArrayList();

			for (int i = 0; i < airSegments.size(); i ++) {
				destinations.add(airSegments.get(i).getDestination());
			}
			markupRequest.setDestinations(ArrayUtil.tranfer(destinations.toArray()));
			LinkedHashSet<String> cabins = Sets.newLinkedHashSet();
			LinkedHashSet<String> flightNums = Sets.newLinkedHashSet();
			LinkedHashSet<String> marketAirs = Sets.newLinkedHashSet();
			LinkedHashSet<String> operateAirs = Sets.newLinkedHashSet();

			for (BaseAirSegment baseAirSegment : verifyResp.getAirSegment()) {
				cabins.add(baseAirSegment.getBookingCode());
				flightNums.add(baseAirSegment.getCarrier() + baseAirSegment.getFlightNumber());
				marketAirs.add(baseAirSegment.getCarrier());
				operateAirs.add(StringUtils.isBlank(baseAirSegment.getOperatingCarrier()) ? baseAirSegment.getCarrier() : baseAirSegment.getOperatingCarrier());
			}

			LinkedHashSet<String> farebasises = Sets.newLinkedHashSet(Lists.transform(verifyResp.getAirPricingInfo(), new Function<PricingInfo, String>() {
				@Override
				public String apply(PricingInfo input) {
					return input.getFareBasisCode();
				}
			}));

			markupRequest.setBookingClasses(ArrayUtil.tranfer(cabins.toArray()));
			markupRequest.setFareBasises(ArrayUtil.tranfer(farebasises.toArray()));
			markupRequest.setFlightNumbers(ArrayUtil.tranfer(flightNums.toArray()));
			markupRequest.setMarketAirlines(ArrayUtil.tranfer(marketAirs.toArray()));
			markupRequest.setOperatorAirlines(ArrayUtil.tranfer(operateAirs.toArray()));
			LOGGER.debug("markup request: {}", markupRequest);
			return markupRequest;
		} catch (Exception e) {
			LOGGER.error("build markup error: ", e);
			return null;
		}
	}

	@Override
	public FareSearchResponse addPrice(FareSearchResponse origin, Map<Integer, MarkupResponse> markups) {
		if (null == origin || MapUtils.isEmpty(markups)) {
			return origin;
		}

		FareSearchResponse resp = new FareSearchResponse();
		resp.setFlyType(origin.getFlyType());
		resp.setTbCode(origin.getTbCode());
		List<FlightCombination> flightCombinations = Lists.newArrayList();
		resp.setFlightCombinations(origin.getFlightCombinations());

		try {
			int count = 1;
			for (FlightCombination flightCombination : origin.getFlightCombinations()) {
                if (!markups.containsKey(count)) {
                    FlightCombination fc = new FlightCombination();
                    BeanUtils.copyProperties(fc, flightCombination);
                    flightCombinations.add(fc);
                    continue;
                }

                MarkupResponse markupResponse = markups.get(count ++);
                if (null == markupResponse || StringUtils.isNotBlank(markupResponse.getErrorMsg()) || StringUtils.equalsIgnoreCase("false", markupResponse.getSuccess())) {
                    continue;
                }

                for (List<MarkupInfo> markupInfos : markupResponse.getMarkupinfos()) {
                    /* 不同的返点留钱模式， 取较低的 */
					FlightCombination fc = new FlightCombination();
					BeanUtils.copyProperties(fc, flightCombination);
					flightCombinations.add(fc);
					FareInfo targetFareInfo = null;
					for (FareInfo fareInfo : fc.getFarelist()) {
						if (!StringUtils.equalsIgnoreCase("ADT", fareInfo.getType())
								&& !StringUtils.equalsIgnoreCase("ALL", fareInfo.getType())) {
							continue;
						}

						targetFareInfo = fareInfo;
						break;
					}

					/* 只有成人才能有返点留钱，儿童直接使用原始价格 */
					addPrice(targetFareInfo, markupInfos);
                }
            }

			if (!CollectionUtils.isEmpty(flightCombinations)) {
				resp.setFlightCombinations(flightCombinations);
			}
            return resp;
		} catch (Exception e) {
			LOGGER.error("markup error: ", e);
		}
		return null;
	}

	private double _addPrice(double origin, List<MarkupInfo> markupInfos) {
		for (MarkupInfo markupInfo : markupInfos) {
			if (StringUtils.equalsIgnoreCase(markupInfo.getMarkupBy(), "Rate")) {
				origin = origin + markupInfo.getMarkupValue() / 100.0 * origin;
			} else if (StringUtils.equalsIgnoreCase(markupInfo.getMarkupBy(), "Amount")) {
				origin += markupInfo.getMarkupValue();
			}
		}
		return origin;
	}

	private void addPrice(FareInfo targetFareInfo, List<MarkupInfo> markupInfos) {
		double branchBasePrice = targetFareInfo.getBranchBasePrice();
		branchBasePrice = _addPrice(branchBasePrice, markupInfos);
		targetFareInfo.setBranchBasePrice((int) branchBasePrice);
		targetFareInfo.setBranchTotalPrice(targetFareInfo.getBranchBasePrice() + targetFareInfo.getBranchTaxes());
	}

	/* pre handle */
	private void addPrice(PricingInfo pricingInfo, List<MarkupInfo> markupInfos) {
		double branchBasePrice = NumberUtils.toDouble(pricingInfo.getBranchBasePrice());
		LOGGER.info("before mark up price: {}", pricingInfo);
		branchBasePrice = _addPrice(branchBasePrice, markupInfos);
		pricingInfo.setBranchBasePrice(String.valueOf(branchBasePrice));
		pricingInfo.setBranchTotalPrice(String.valueOf(branchBasePrice + NumberUtils.toDouble(pricingInfo.getBranchTaxes())));
		LOGGER.info("after mark up price: {}", pricingInfo);
	}

	/* used for verify */
	@Override
	public MarkupResponse markup(VerifyResp verifyResp) {

		MarkupRequest markupRequest = buildMarkupRequest(verifyResp);

		return markupCore(markupRequest);

//		List<Header> headers = Lists.newArrayList();
//		headers.add(new BasicHeader("Content-Type", "binary/octet-stream"));
//		String resp = HttpClientUtils.post(markupUrl, JsonUtil.writeString(markupRequest), header, CONN_TIMEOUT, SO_TIMEOUT);
//		return JsonUtil.readValue(resp, MarkupResponse.class);
	}

	private void addPriceCore(List<PricingInfo> pricingInfos, MarkupResponse markups) {
		for (PricingInfo pricingInfo : pricingInfos) {
			if (StringUtils.equalsIgnoreCase(pricingInfo.getCode(), "ADT")
					|| StringUtils.equalsIgnoreCase(pricingInfo.getCode(), "ALL")) {

				if (markups == null || CollectionUtils.isEmpty(markups.getMarkupinfos())
						|| StringUtils.isNotBlank(markups.getErrorMsg()) || StringUtils.equalsIgnoreCase("false", markups.getSuccess())) {
					continue;
				}

				for (List<MarkupInfo> markupInfos : markups.getMarkupinfos()) {
                    /* 不同的返点留钱模式， 取较低的 */
					/* 只有成人才能有返点留钱，儿童直接使用原始价格 */
					addPrice(pricingInfo, markupInfos);
				}
			}
		}
	}

	@Override
	public VerifyResp addPrice(VerifyResp origin, MarkupResponse markups) {
		addPriceCore(origin.getAirPricingInfo(), markups);
		return origin;
	}

	/* used for order */

	@Override
	public MarkupResponse markup(OrderResp orderResp, int tripType) {
		MarkupRequest markupRequest = buildMarkupRequest(orderResp.getPlatingCarrier(), orderResp.getAirSegment(), orderResp.getAirPricingInfo(), tripType);
		return invoke(markupRequest);
	}

	@Override
	public OrderResp addPrice(OrderResp origin, MarkupResponse markups) {
		addPriceCore(origin.getAirPricingInfo(), markups);
		return origin;
	}
	/* used for pay validate */

	@Override
	public MarkupResponse markup(PayResp payResp, int tripType) {
		MarkupRequest markupRequest = buildMarkupRequest(payResp.getPlatingCarrier(), payResp.getAirSegment(), payResp.getAirPricingInfo(), tripType);
		return invoke(markupRequest);
	}

	private MarkupResponse invoke(MarkupRequest markupRequest) {
		return markupCore(markupRequest);
//		List<Header> headers = Lists.newArrayList();
//		headers.add(new BasicHeader("Content-Type", "binary/octet-stream"));
//		String resp = HttpClientUtils.post(markupUrl, JsonUtil.writeString(markupRequest), header, CONN_TIMEOUT, SO_TIMEOUT);
//		return JsonUtil.readValue(resp, MarkupResponse.class);
	}

	@Override
	public PayResp addPrice(PayResp origin, MarkupResponse markups) {
		addPriceCore(origin.getAirPricingInfo(), markups);
		return origin;
	}

	public static void main(String[] args) {
		MarkupRequest markupParameterVo = new MarkupRequest();
		markupParameterVo.setTripType(TripType.Oneway); // 单程还是往返
		markupParameterVo.setAirline("FJ"); // 航空公司
		markupParameterVo.setDepartureDateTime("2017-12-08T16:25:00");// 起飞时间
		markupParameterVo.setDepartureAirport("HKG");// 出发地（机场代码）
		markupParameterVo.setDestinations(new String[]{"NAN"});// 目的地（机场代码）
		markupParameterVo.setBookingClasses(new String[]{"Y"});// 所有航段不重复的舱位，例如Q,Z
		markupParameterVo.setFareBasises(new String[]{"BULA"});// 所有航段不重复的票价基础
		markupParameterVo.setFlightNumbers(new String[]{"FJ888"});// 所有航段不重复的航班号，如CZ338,CZ3061
		markupParameterVo.setMarketAirlines(new String[]{"FJ"});// 所有航段不重复的航空公司
		markupParameterVo.setOperatorAirlines(new String[]{""});// 所有航段的共享航空公司
		List<Header> headers = Lists.newArrayList();
		headers.add(new BasicHeader("Content-Type", "binary/octet-stream"));
		String resp = HttpClientUtils.post("http://api.bangpiao.net:4005/ots/services/wtl-interface!markup.do"
				, JsonUtil.writeString(markupParameterVo), header, CONN_TIMEOUT, SO_TIMEOUT);

		MarkupResponse markupResponse = JsonUtil.readValue(resp, MarkupResponse.class);
		System.out.println(markupResponse);
	}
}
