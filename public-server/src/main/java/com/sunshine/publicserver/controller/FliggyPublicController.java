package com.sunshine.publicserver.controller;

import com.mchange.v2.util.CollectionUtils;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.publicserver.constants.Con;
import com.sunshine.publicserver.context.BusinessContext;
import com.sunshine.publicserver.enums.Platform;
import com.sunshine.publicserver.fliggy.vo.*;
import com.sunshine.publicserver.service.PlatformServiceRouter;
import com.sunshine.publicserver.utils.Base64Util;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/fliggy")
public class FliggyPublicController {
	private static final Logger logger = LoggerFactory.getLogger(FliggyPublicController.class);

	@Resource
	private PlatformServiceRouter platformServiceRouter;

	private String decode(HttpServletRequest request) {
		try {
			return IOUtils.toString(request.getInputStream(), "utf-8");
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public SearchResponse search(HttpServletRequest request) {
		try {
			String origin = decode(request);
			long st = System.currentTimeMillis();
			logger.debug("searchRequest:{}", origin);
			SearchRequest searchRequest = JsonUtil.readValue(origin, SearchRequest.class);
			BusinessContext.setRequestId("fliggy_search_" + DateTime.now().getMillis());
			SearchResponse searchResponse = platformServiceRouter.search(searchRequest, Platform.FLIGGY);
			logger.debug("search size {} response: {} costs {}ms", CollectionUtils.size(searchResponse.getRoutings()), searchResponse, System.currentTimeMillis() - st);
			return searchResponse;
		} finally {
			BusinessContext.releaseResource();
		}
	}

	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	@ResponseBody
	public VerifyResponse verify(HttpServletRequest request) {
		try {
			String origin = decode(request);
			long st = System.currentTimeMillis();
			logger.info("verifyRequest:{}", origin);
			VerifyRequest verifyRequest = JsonUtil.readValue(origin, VerifyRequest.class);
			logger.info("decode verify request: {}", verifyRequest);
			BusinessContext.setRequestId("fliggy_verify_" + DateTime.now().getMillis());
			VerifyResponse verifyResponse = platformServiceRouter.verify(verifyRequest, Platform.FLIGGY);
			logger.info("verifyResponse: {} costs {}ms", verifyResponse, System.currentTimeMillis() - st);
			return verifyResponse;
		} finally {
			BusinessContext.releaseResource();
		}
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST, consumes = {MediaType.TEXT_PLAIN_VALUE})
	@ResponseBody
	public String order(HttpServletRequest request) {
		try {
			String origin = decode(request);
			long st = System.currentTimeMillis();
			logger.info("orderRequest:{}", origin);
			if (StringUtils.isBlank(origin)) {
                return Base64Util.wrapEncode(JsonUtil.writeString(OrderResponse.make(1, "request is null")), Con.DATA_TOKEN);
            }

			OrderRequest orderRequest = JsonUtil.readValue(Base64Util.wrapDecode(origin, Con.DATA_TOKEN), OrderRequest.class);
			logger.info("decode order request: {}", orderRequest);
			BusinessContext.setRequestId("fliggy_order_" + DateTime.now().getMillis());
			OrderResponse orderResponse = platformServiceRouter.order(orderRequest, Platform.FLIGGY);
			logger.info("orderResponse: {} costs {}ms", orderResponse, System.currentTimeMillis() - st);
			return Base64Util.wrapEncode(JsonUtil.writeString(orderResponse), Con.DATA_TOKEN);
		} finally {
			BusinessContext.releaseResource();
		}
	}

	@RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String pay(HttpServletRequest request) {
		try {
			String origin = decode(request);
			long st = System.currentTimeMillis();
			logger.info("payRequest:{}", origin);
			if (StringUtils.isBlank(origin)) {
                return Base64Util.wrapEncode(JsonUtil.writeString(PayResponse.make(1, "request is null")), Con.DATA_TOKEN);
            }
			PayRequest payRequest = JsonUtil.readValue(Base64Util.wrapDecode(origin, Con.DATA_TOKEN), PayRequest.class);
			logger.info("decode pay request: {}", payRequest);
			BusinessContext.setRequestId("fliggy_pay_" + DateTime.now().getMillis());
			PayResponse payResponse = platformServiceRouter.pay(payRequest, Platform.FLIGGY);
			logger.info("payResponse: {} costs {}ms", payResponse, System.currentTimeMillis() - st);
			return Base64Util.wrapEncode(JsonUtil.writeString(payResponse), Con.DATA_TOKEN);
		} finally {
			BusinessContext.releaseResource();
		}
	}

}
