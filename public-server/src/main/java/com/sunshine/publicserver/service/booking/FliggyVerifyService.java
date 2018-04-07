package com.sunshine.publicserver.service.booking;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.common.vo.TripType;
import com.sunshine.publicserver.constants.Con;
import com.sunshine.publicserver.enums.Platform;
import com.sunshine.publicserver.fliggy.vo.*;
import com.sunshine.publicserver.service.IMarkupService;
import com.sunshine.publicserver.service.basic.BaseInfoService;
import com.sunshine.publicserver.service.cache.DistributeCacheService;
import com.sunshine.publicserver.service.cache.PriceCacheManager;
import com.sunshine.publicserver.service.impl.AdjustPriceService;
import com.sunshine.publicserver.utils.Base64Util;
import com.sunshine.publicserver.utils.HttpClientUtils;
import com.sunshine.publicserver.utils.TransferUtil;
import com.sunshine.publicserver.vo.MarkupResponse;
import com.sunshine.service.pojo.VerifyReq;
import com.sunshine.service.pojo.VerifyResp;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FliggyVerifyService implements IBookingService<VerifyRequest, VerifyResponse> {

    private static Logger LOGGER = LoggerFactory.getLogger(FliggyVerifyService.class);

    @Value("${verifyUrl}")
    private String verifyUrl;

    @Resource
    private IMarkupService markupService;

    @Resource
    private BaseInfoService baseInfoService;

    @Resource
    private AdjustPriceService adjustPriceService;

    @Resource
    private PriceCacheManager priceCacheManager;

    @Override
    public Platform platform() {
        return Platform.FLIGGY;
    }

    private VerifyResponse validate(VerifyRequest request) {
        if (null == request) {
            return VerifyResponse.make(1, "请求参数为空");
        }

        if (StringUtils.isBlank(request.getCid())) {
            return VerifyResponse.make(1, "cid is null");
        }

        if (StringUtils.isBlank(request.getTripType())
                || null == TripType.get(NumberUtils.toInt(request.getTripType()))) {
            return VerifyResponse.make(1, "trip type is illegal");
        }

        if (null == request.getRouting()) {
            return VerifyResponse.make(1, "routing is null");
        }

        if (StringUtils.isBlank(request.getRouting().getData())) {
            return VerifyResponse.make(1, "routing data is null");
        }
        return null;
    }

    @Override
    public VerifyResponse booking(VerifyRequest request) {
        VerifyResponse resp = validate(request);
        if (null != resp) {
            LOGGER.error("request is illegal: {}", resp);
            return resp;
        }

        RoutingElement routing = request.getRouting();
        FliggyDataInfo fliggyDataInfo = JsonUtil.readValue(Base64Util.wrapDecode(routing.getData(), Con.DATA_TOKEN), FliggyDataInfo.class);
        if (null == fliggyDataInfo) {
            return VerifyResponse.make(1, "data is empty");
        }

        String pcc = fliggyDataInfo.getTbCode();
        List<String> farebasis = fliggyDataInfo.getFareBasis();
        String providerCode = fliggyDataInfo.getProviderCode();

        /* 调用底层验价接口 */
        VerifyReq req = new VerifyReq();
        req.setFareBasis(farebasis);
        req.setAdultNum(1);
        req.setChildNum(0);
        req.setFlightType(NumberUtils.toInt(request.getTripType()));
        req.setAirSegment(TransferUtil.verifyReqTransfer(routing));
        req.setTargetBranch(StringUtils.equalsIgnoreCase(providerCode, "1G") ? Con.G_TB : Con.P_TB);
        req.setPcc(pcc);
        req.setProviderCode(providerCode);

        VerifyResp verifyResp = fetchVerifyResp(req);
        if (null == verifyResp || CollectionUtils.isEmpty(verifyResp.getAirPricingInfo())) {
            priceCacheManager.refreshIfMaintains(fliggyDataInfo.getFareCacheKey());
            return VerifyResponse.make(1, "未取到该运价的校验结果");
        }

        /* pre handle price is not number */
        verifyResp.setAirPricingInfo(TransferUtil.removeCurrencySuffix(verifyResp.getAirPricingInfo()));

        TransferUtil.fillCabinInfos(request.getRouting(), verifyResp.getAirSegment());

        /* 匹配markup调价 */
        MarkupResponse markup = markupService.markup(verifyResp);
        verifyResp = markupService.addPrice(verifyResp, markup);

        VerifyResponse verifyResponse = TransferUtil.verifyTransform(verifyResp, fliggyDataInfo.getFareCacheKey(), request.getRouting());

        /* 汇率转换 */
        ArrayList<RoutingElement> routingElements = Lists.newArrayList(verifyResponse.getRouting());
        baseInfoService.exchange(routingElements, verifyResp.getCurrency());

        /* 调价 */
        adjustPriceService.syncAdjustPrice(routingElements);
        LOGGER.info("verify response: {}", verifyResponse);
        return verifyResponse;
    }

    private VerifyResp fetchVerifyResp(VerifyReq req) {
        String httpResp = HttpClientUtils.post(verifyUrl, JsonUtil.writeString(req), HttpClientUtils.DEFAULT_HEADER, 10000, 20000);

        @SuppressWarnings("unchecked")
        HttpResponse<String> response = JsonUtil.readValue(httpResp, HttpResponse.class);
        if (null == response || !response.isSuccess() || StringUtils.isBlank(response.getData())) {
            LOGGER.warn("未取到airPrice结果: {}", response);
            return null;
        }

        VerifyResp verifyResp = null;
        try {
            verifyResp = JsonUtil.OBJECT_MAPPER.readValue(response.getData(), VerifyResp.class);
        } catch (IOException e) {}

        return verifyResp;
    }
}
