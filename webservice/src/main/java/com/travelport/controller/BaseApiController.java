package com.travelport.controller;

import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.service.pojo.*;
import com.travelport.ResponseMapping.AvaiableResponse;
import com.travelport.uapi.request.AirPrice;
import com.travelport.uapi.request.CreateReservat;
import com.travelport.uapi.request.LowFareSearch;
import com.travelport.uapi.request.UniversalRecordRetrieve;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseApiController.class);

    @Value("${usrname}")
    private String usrname;

    @Value("${password}")
    private String password;

    @Value("${1p.usrname}")
    private String worldSpanUsrname;

    @Value("${1p.password}")
    private String worldSpanPassword;

    private String selectUsername(String providerCode) {
        if (StringUtils.equalsIgnoreCase(providerCode, "1G")) {
            return usrname;
        }
        return worldSpanUsrname;
    }

    private String selectPassword(String providerCode) {
        if (StringUtils.equalsIgnoreCase(providerCode, "1G")) {
            return password;
        }
        return worldSpanPassword;
    }

    @RequestMapping(value = "/price/search", method = {RequestMethod.POST})
    @ResponseBody
    public HttpResponse<String> priceSearch(@RequestBody FareSearchReq fareSearchReq) {
        if (null == fareSearchReq) {
            return HttpResponse.failed("request is null");
        }

        FareSearchResponse fareSearchResponse = LowFareSearch.fareSearch(fareSearchReq, selectUsername(fareSearchReq.getProviderCode())
                , selectPassword(fareSearchReq.getProviderCode()));
        if (null == fareSearchResponse || CollectionUtils.isEmpty(fareSearchResponse.getFlightCombinations())) {
            return HttpResponse.failed("未搜索到运价");
        }
        return HttpResponse.success(JsonUtil.writeString(fareSearchResponse));
    }

    @RequestMapping(value = "/cabin/verify", method = {RequestMethod.POST})
    @ResponseBody
    public HttpResponse verify(@RequestBody VerifyReq verifyReq) {
        if (null == verifyReq) {
            return HttpResponse.failed("request is null");
        }

        VerifyResp response = AirPrice.verify(verifyReq, selectUsername(verifyReq.getProviderCode()), selectPassword(verifyReq.getProviderCode()));
        LOGGER.info("air price response: {}", response);

        if (null == response || CollectionUtils.isEmpty(response.getAirPricingInfo())) {
            return HttpResponse.failed("未搜索到运价");
        }
        return HttpResponse.success(JsonUtil.writeString(response));
    }

    @RequestMapping(value = "/createOrder", method = {RequestMethod.POST})
    @ResponseBody
    public HttpResponse createOrder(@RequestBody OrderReq orderRequest) {
        if (null == orderRequest) {
            return HttpResponse.failed("request is null");
        }

        /* ss + passenger detail */
        OrderResp response = CreateReservat.createPnr(orderRequest, selectUsername(orderRequest.getProviderCode()), selectPassword(orderRequest.getProviderCode()));
        if (null == response || CollectionUtils.isEmpty(response.getAirPricingInfo())) {
            return HttpResponse.failed("未搜索到运价");
        }

        if (CollectionUtils.isEmpty(response.getAirSegment())) {
            return HttpResponse.failed("舱位不存在");
        }
        return HttpResponse.success(JsonUtil.writeString(response));
    }

    @RequestMapping(value = "/payValidate", method = {RequestMethod.POST})
    @ResponseBody
    public HttpResponse payValidate(@RequestBody PayReq payReq) {
        if (null == payReq) {
            return HttpResponse.failed("request is null");
        }

        PayResp payResp = UniversalRecordRetrieve.readPnr(payReq.getPnrCode(), payReq.getTargetBranch(), selectUsername(payReq.getProviderCode()), selectPassword(payReq.getProviderCode()));
        if (null == payResp) {
            return HttpResponse.failed("获取pnr信息失败");
        }
        return HttpResponse.success(JsonUtil.writeString(payResp));
    }
}
