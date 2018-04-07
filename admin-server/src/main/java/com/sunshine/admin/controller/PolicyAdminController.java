package com.sunshine.admin.controller;

import java.rmi.ServerException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunshine.admin.service.IPolicyService;
import com.sunshine.common.http.response.CommonResponse;
import com.sunshine.common.vo.GDS;
import com.sunshine.common.vo.Platform;
import com.sunshine.common.vo.PricePolicy;
import com.sunshine.common.vo.QuotaPolicy;
import com.sunshine.common.vo.TripType;
import com.sunshine.common.vo.WhitelistPolicy;

@Controller
public class PolicyAdminController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PolicyAdminController.class);

	@Resource
	private IPolicyService policyService;

	@RequestMapping(value = "/price/query", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse queryPrice(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) TripType tripType,
			@RequestParam(required = false) GDS gds,
			@RequestParam(required = false) Platform platform,
			@RequestParam(required = false) String carrier,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String fromCity,
			@RequestParam(required = false) String toCity) {
		logger.debug(
				"tripType:{} gds:{} platform:{} carrier:{} id:{} fromCity:{} toCity:{}",
				tripType, gds, platform, carrier, id, fromCity, toCity);

		List<PricePolicy> list = this.policyService.queryPricePolicies(
				tripType, gds, platform, carrier, id, fromCity, toCity);
		return new CommonResponse("prices", list);
	}

	private static final Splitter SPLITTER = Splitter.on("/").trimResults().omitEmptyStrings();

	@RequestMapping(value = "/price/queryv2", method = RequestMethod.POST)
	@ResponseBody
	public HttpResponse<String> queryAdjustPricePolicy(@RequestBody PricePolicy request) {
		if (null == request) {
			return HttpResponse.failed("null request");
		}

		logger.debug("adjust price request: {}", request);
		/* split fromcity and tocity to support country config */
		List<String> fromConfigs = SPLITTER.splitToList(request.getFromCity());
		List<String> toConfigs = SPLITTER.splitToList(request.getToCity());
		Set<PricePolicy> finalResult = Sets.newHashSet();
		for (String fromCity : fromConfigs) {
			for (String toCity : toConfigs) {
				List<PricePolicy> pricePolicies = this.policyService.queryPricePolicies(request.getTripType(), request.getGds(), request.getPlatform()
						, request.getCarrier(), request.getId(), fromCity, toCity);

				if (!CollectionUtils.isEmpty(pricePolicies)) {
					finalResult.addAll(pricePolicies);
				}
			}
		}

		if (CollectionUtils.isEmpty(finalResult)) {
			return HttpResponse.failed("response is null");
		}
		return HttpResponse.success(JsonUtil.writeString(Sets.newHashSet(finalResult)));
	}

	@RequestMapping(value = "/price/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public CommonResponse saveOrUpdatePrice(@RequestBody PricePolicy pricePolicy) {
		logger.debug("pricePolicy:{}", pricePolicy);
		long id = this.policyService.saveOrUpdatePricePolicy(pricePolicy);
		return new CommonResponse("id", id);
	}

	@RequestMapping(value = "/price/del", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse delPrice(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Long[] id)
			throws ServerException {
		logger.debug("del id:{}", Arrays.asList(id));
		this.policyService.delPricePolicyById(id);
		return new CommonResponse();
	}

	@RequestMapping(value = "/whitelist/query", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse queryWhitelist(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) GDS gds,
			@RequestParam(required = false) String targetBranch,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String fromState,
			@RequestParam(required = false) String toState) {
		logger.debug("gds:{} targetBranch:{} id:{} fromState:{} toState:{}",
				gds, targetBranch, id, fromState, toState);

		List<WhitelistPolicy> list = this.policyService.queryWhitelistPolicies(
				gds, targetBranch, id, fromState, toState);
		return new CommonResponse("whitelists", list);
	}

	@RequestMapping(value = "/whitelist/queryv2", method = RequestMethod.POST)
	@ResponseBody
	public HttpResponse<String> queryWhiteList(@RequestBody WhitelistPolicy request) {
		if (null == request) {
			return HttpResponse.failed("request is null");
		}

		logger.debug("whitelistPolicy request: {}", request);
		List<WhitelistPolicy> list = this.policyService.queryWhitelistPolicies(request.getGds(), request.getTargetBranch(), request.getId()
				, request.getFromState(), request.getToState());
		if (CollectionUtils.isEmpty(list)) {
			return HttpResponse.failed("response is empty");
		}
		return HttpResponse.success(JsonUtil.writeString(list));
	}


	@RequestMapping(value = "/whitelist/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public CommonResponse saveOrUpdateWhitelist(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody WhitelistPolicy whitelistPolicy) {
		logger.debug("whitelistPolicy:{}", whitelistPolicy);
		long id = this.policyService
				.saveOrUpdateWhitelistPolicy(whitelistPolicy);
		return new CommonResponse("id", id);
	}

	@RequestMapping(value = "/whitelist/del", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse delWhitelist(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Long[] id)
			throws ServerException {
		this.policyService.delWhitePolicyById(id);
		return new CommonResponse();
	}

	@RequestMapping(value = "/quota/queryv2", method = RequestMethod.POST)
	@ResponseBody
	public HttpResponse<String> queryQuota(@RequestBody QuotaPolicy quotaPolicy) {
		if (null == quotaPolicy) {
			return HttpResponse.failed("request is null");
		}

		logger.debug("quota policy: {}", quotaPolicy);
		List<QuotaPolicy> quotaPolicies = policyService.queryQuotaPolicies(quotaPolicy.getStatus(), quotaPolicy.getGds(), quotaPolicy.getId(), quotaPolicy.getTargetBranch());
		logger.debug("quota response: {}", quotaPolicies);
		if (!org.apache.commons.collections.CollectionUtils.isEmpty(quotaPolicies)) {
			return HttpResponse.success(JsonUtil.writeString(quotaPolicies));
		}
		return HttpResponse.failed("response is empty");
	}

	@RequestMapping(value = "/quota/query", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse queryQuota(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) Integer status,
			@RequestParam(required = false) GDS gds,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String targetBranch) {
		logger.debug("status:{} gds:{} id:{} targetBranch:{}", status, gds, id,
				targetBranch);
		List<QuotaPolicy> list = this.policyService.queryQuotaPolicies(status,
				gds, id, targetBranch);
		return new CommonResponse("quotas", list);
	}

	@RequestMapping(value = "/quota/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public CommonResponse saveOrUpdateQuota(HttpServletRequest request,
			HttpServletResponse response, @RequestBody QuotaPolicy quotaPolicy) {
		logger.debug("quotaPolicy:{}", quotaPolicy);
		long id = this.policyService.saveOrUpdateQuotaPolicy(quotaPolicy);
		return new CommonResponse("id", id);
	}

	@RequestMapping(value = "/quota/upsert", method = RequestMethod.POST)
	@ResponseBody
	public HttpResponse<String> upsertQuotas(@RequestBody List<QuotaPolicy> quotaPolicies) {
		logger.debug("quotaPolicy:{}", quotaPolicies);
		if (CollectionUtils.isEmpty(quotaPolicies)) {
			return HttpResponse.failed("request is null");
		}

		List<Long> ids = Lists.newArrayList();
		for (QuotaPolicy quotaPolicy : quotaPolicies) {
			ids.add(policyService.saveOrUpdateQuotaPolicy(quotaPolicy));
		}
		return HttpResponse.success(JsonUtil.writeString(ids));
	}

	@RequestMapping(value = "/quota/del", method = RequestMethod.GET)
	@ResponseBody
	public CommonResponse delQuota(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Long[] id)
			throws ServerException {
		this.policyService.delQuotaPolicyById(id);
		return new CommonResponse();
	}
}
