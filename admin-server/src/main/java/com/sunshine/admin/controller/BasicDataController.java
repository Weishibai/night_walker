package com.sunshine.admin.controller;

import com.sunshine.admin.service.IBasicDataService;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.common.vo.ExchangeInfo;
import com.sunshine.common.vo.InfoCenterData;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 基础数据服务,例如机场-国家对应表
 */
@Controller
public class BasicDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicDataController.class);

    @Resource
    private IBasicDataService basicDataService;



    @RequestMapping(value = "/exchange/query", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public HttpResponse<String> queryExchange(@RequestBody ExchangeInfo request) {
        if (null == request) {
            return HttpResponse.failed("request is null");
        }

        List<ExchangeInfo> exchangeInfos = basicDataService.queryExchange(request);
        if (CollectionUtils.isEmpty(exchangeInfos)) {
            return HttpResponse.failed("response is empty");
        }
        LOGGER.debug("query exchange success request: {} response: {}", request, exchangeInfos);
        return HttpResponse.success(JsonUtil.writeString(exchangeInfos));
    }

    @RequestMapping(value = "/exchange/queryAll", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public HttpResponse<String> queryExchangeAll() {
        ExchangeInfo exchangeInfo = ExchangeInfo.make("", "");
        List<ExchangeInfo> exchangeInfos = basicDataService.queryExchange(exchangeInfo);
        if (CollectionUtils.isEmpty(exchangeInfos)) {
            return HttpResponse.failed("response is empty");
        }
        LOGGER.debug("query exchange success response: {}", exchangeInfos);
        return HttpResponse.success(JsonUtil.writeString(exchangeInfos));
    }

    @RequestMapping(value = "/exchange/upsert/{rate}/{srcCur}/{dstCur}", method = {RequestMethod.GET})
    @ResponseBody
    public HttpResponse<String> upsertExchange(@PathVariable double rate, @PathVariable String srcCur,  @PathVariable String dstCur) {
        if (StringUtils.isBlank(srcCur) || StringUtils.isBlank(dstCur)) {
            return HttpResponse.failed("request is blank");
        }

        if (rate <= 1.0e-06) {
            return HttpResponse.failed("rate is negative");
        }

        ExchangeInfo info = ExchangeInfo.make(srcCur, dstCur);
        info.setRate(rate);
        if (basicDataService.upsertExchange(info)) {
            return HttpResponse.success("upsert exchange success");
        }
        return HttpResponse.failed("upsert exchange failed");
    }


    @RequestMapping(value = "/infocenter/query", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public HttpResponse<String> queryInfoCenter(@RequestBody InfoCenterData request) {
        if (null == request) {
            return HttpResponse.failed("request is null");
        }

        List<InfoCenterData> data = basicDataService.queryInfoCenter(request);
        if (CollectionUtils.isEmpty(data)) {
            return HttpResponse.failed("response is empty");
        }
        LOGGER.debug("query infocenter success request: {} response: {}", request, data);
        return HttpResponse.success(JsonUtil.writeString(data));
    }

    @RequestMapping(value = "/infocenter/upsert/{airportCode}/{cityCode}/{countryCode}", method = {RequestMethod.GET})
    public HttpResponse<String> upsertInfocenter(@PathVariable String airportCode, @PathVariable String cityCode, @PathVariable String countryCode) {
        if (StringUtils.isBlank(airportCode) || StringUtils.isBlank(cityCode) || StringUtils.isBlank(countryCode)) {
            return HttpResponse.failed("request is blank");
        }

        InfoCenterData infoCenterData = InfoCenterData.make(airportCode, cityCode, countryCode);

        if (basicDataService.upsertInfocenter(infoCenterData)) {
            return HttpResponse.success("upsert infocenter success");
        }
        return HttpResponse.failed("upsert infocenter failed");
    }

}
