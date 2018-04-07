package com.sunshine.publicserver.service.pay;

import com.google.common.collect.Lists;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.publicserver.constants.Con;
import com.sunshine.publicserver.enums.Platform;
import com.sunshine.publicserver.fliggy.vo.*;
import com.sunshine.publicserver.service.IMarkupService;
import com.sunshine.publicserver.service.basic.BaseInfoService;
import com.sunshine.publicserver.service.impl.AdjustPriceService;
import com.sunshine.publicserver.utils.Base64Util;
import com.sunshine.publicserver.utils.HttpClientUtils;
import com.sunshine.publicserver.utils.TransferUtil;
import com.sunshine.publicserver.vo.MarkupResponse;
import com.sunshine.service.pojo.PayReq;
import com.sunshine.service.pojo.PayResp;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class FliggyPayService implements IPayService<PayRequest, PayResponse> {

    private static Logger LOGGER = LoggerFactory.getLogger(FliggyPayService.class);

    @Value("${payUrl}")
    private String payUrl;

    @Resource
    private IMarkupService markupService;

    @Resource
    private BaseInfoService baseInfoService;

    @Resource
    private AdjustPriceService adjustPriceService;

    @Override
    public Platform platform() {
        return Platform.FLIGGY;
    }

    @Override
    public PayResponse pay(PayRequest request) {
        try {
            if (null == request) {
                return PayResponse.make(1, "request is null");
            }

            int tripType = NumberUtils.toInt(request.getTripType());
            if (StringUtils.isBlank(request.getCid()) || StringUtils.isBlank(request.getPnrCode())
                    || null == request.getRouting()) {
                return PayResponse.make(1, "request is illegal");
            }

            /* 请求底层接口进行rt */
            RoutingElement routing = request.getRouting();
            FliggyDataInfo fliggyDataInfo = JsonUtil.readValue(Base64Util.wrapDecode(routing.getData(), Con.DATA_TOKEN), FliggyDataInfo.class);
            LOGGER.info("pay data: {}", fliggyDataInfo);

            @SuppressWarnings("unchecked")
            List<String> pnrCodes = JsonUtil.readValue(Base64Util.wrapDecode(JsonUtil.writeString(request.getOrderNo()), Con.DATA_TOKEN), List.class);
            LOGGER.info("pay target pnr codes: {}", pnrCodes);
            String pcc = fliggyDataInfo.getTbCode();
            String realPnrCode = pnrCodes.get(0);
            PayReq payReq = PayReq.make(pcc, fliggyDataInfo.getProviderCode(), realPnrCode);
            payReq.setTargetBranch(StringUtils.equalsIgnoreCase(fliggyDataInfo.getProviderCode(), "1G") ? Con.G_TB : Con.P_TB);
            String httpResp = HttpClientUtils.post(payUrl, JsonUtil.writeString(payReq), HttpClientUtils.DEFAULT_HEADER, 20000, 30000);

            @SuppressWarnings("unchecked")
            HttpResponse<String> response = JsonUtil.readValue(httpResp, HttpResponse.class);
            if (null == response || !response.isSuccess() || StringUtils.isBlank(response.getData())) {
                LOGGER.warn("调用底层获取pnr信息失败: {}", response);
                return PayResponse.make(1, "rtpnr失败");
            }

            PayResp payResp = null;
            try {
                payResp = JsonUtil.OBJECT_MAPPER.readValue(response.getData(), PayResp.class);
            } catch (IOException e) {}

            /* pre handle price is not number */
            payResp.setAirPricingInfo(TransferUtil.removeCurrencySuffix(payResp.getAirPricingInfo()));

            TransferUtil.fillCabinInfos(request.getRouting(), payResp.getAirSegment());

            /* markup */
            MarkupResponse markup = markupService.markup(payResp, tripType);
            payResp = markupService.addPrice(payResp, markup);

            /* 结果转换 */
            PayResponse payResponse = TransferUtil.payTransform(payResp, request);

            /* 汇率 */
            List<RoutingElement> routingElements = Lists.newArrayList(payResponse.getRouting());
            baseInfoService.exchange(routingElements, payResp.getCurrency());

            /* 调价 */
            adjustPriceService.syncAdjustPrice(routingElements);
            return payResponse;
        } catch (Exception e) {
            LOGGER.error("pay error: ", e);
            return PayResponse.make(1, "unknown error");
        }
    }
}
