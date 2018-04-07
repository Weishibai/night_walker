package com.sunshine.publicserver.service.order;

import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.sunshine.common.http.response.HttpResponse;
import com.sunshine.common.util.JsonUtil;
import com.sunshine.publicserver.constants.Con;
import com.sunshine.publicserver.enums.Month;
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
import com.sunshine.service.pojo.ApiPassenger;
import com.sunshine.service.pojo.OrderReq;
import com.sunshine.service.pojo.OrderResp;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FliggyOrderService implements IOrderService<OrderRequest, OrderResponse> {

    private static Logger LOGGER = LoggerFactory.getLogger(FliggyOrderService.class);

    private static final Splitter NAME_SPLITTER = Splitter.on("/").trimResults().omitEmptyStrings();

    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");

    @Value("${orderUrl}")
    private String orderUrl;

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

    private String buildDate(DateTime origin) {
        int year = origin.getYear() % 100;
        int day = origin.getDayOfMonth();
        String month = Month.codeOf(origin.getMonthOfYear()).name();
        return ((day < 10) ? "0" + day : day) + month + year;
    }

    @Override
    public OrderResponse order(OrderRequest request) {
        try {
            if (null == request || StringUtils.isBlank(request.getCid()) || StringUtils.isBlank(request.getSessionId())
                    || CollectionUtils.isEmpty(request.getPassengers()) || null == request.getRouting()) {
                return OrderResponse.make(999, "request is illegal");
            }

            RoutingElement routing = request.getRouting();
            FliggyDataInfo fliggyDataInfo = JsonUtil.readValue(Base64Util.wrapDecode(routing.getData(), Con.DATA_TOKEN), FliggyDataInfo.class);
            LOGGER.info("obtain order data: {}", fliggyDataInfo);
            String pcc = fliggyDataInfo.getTbCode();
            List<String> farebasis = fliggyDataInfo.getFareBasis();
            String providerCode = fliggyDataInfo.getProviderCode();

            List<PassengerElement> passengers = request.getPassengers();
            List<PassengerElement> adults = Lists.newArrayList(Iterables.filter(passengers, new Predicate<PassengerElement>() {
                @Override
                public boolean apply(PassengerElement input) {
                    return input.getAgeType() == 0 || input.getAgeType() == -1;
                }
            }));

            /* 请求底层生编接口 */
            OrderReq orderReq = new OrderReq();
            orderReq.setFareBasis(farebasis);
            orderReq.setAdultNum(adults.size());
            orderReq.setChildNum(passengers.size() - adults.size());

            orderReq.setFlightType(NumberUtils.toInt(request.getTripType()));
            orderReq.setAirSegment(TransferUtil.verifyReqTransfer(routing));
            orderReq.setPcc(pcc);
            orderReq.setProviderCode(providerCode);
            orderReq.setTargetBranch(StringUtils.equalsIgnoreCase(providerCode, "1G") ? Con.G_TB : Con.P_TB);
            List<ApiPassenger> apiPassengers = Lists.newArrayList();
            int passengerId = 1;
            for (PassengerElement passenger : passengers) {
                ApiPassenger apiPassenger = new ApiPassenger();
                DateTime birthDay = formatter.parseDateTime(passenger.getBirthday());
                apiPassenger.setBirthday(birthDay.toString("yyyy-MM-dd"));
                apiPassenger.setGender(passenger.getGender());
                apiPassenger.setType(1 == passenger.getAgeType() ? "CHD" : "ADT");
                List<String> names = NAME_SPLITTER.splitToList(passenger.getName());
                apiPassenger.setLastName(names.get(0));
                apiPassenger.setFirstName(names.get(1));
                apiPassenger.setNickname(gengerateNick(apiPassenger.getType(), apiPassenger.getGender()));
                apiPassenger.setId(String.valueOf(passengerId ++));
                apiPassenger.setEmailId(Con.DETAIL_EMAIL);
                apiPassenger.setEmailType("Home");
    //            P/护照签发国家/护照号/乘客国籍/出生日期/性别/护照到期时间/姓/名
                /* "P/CN/P1234567/CN/01Jan95/M/13Apr15/Jones/sddsd" */
    //            出生日期 格式：ddmmmyy 举例：13Apr15
                String birth = buildDate(birthDay);

                DateTime cardExpired = formatter.parseDateTime(passenger.getCardExpired());
                String expired = buildDate(cardExpired);

                String freeText = buildFreeTxt(passenger, birth, expired);
                apiPassenger.setPassport(freeText);
                apiPassengers.add(apiPassenger);
            }

            orderReq.setPassengers(apiPassengers);
            LOGGER.info("api order req: {}", orderReq);
            String httpResp = HttpClientUtils.post(orderUrl, JsonUtil.writeString(orderReq), HttpClientUtils.DEFAULT_HEADER, 20000, 30000);

            @SuppressWarnings("unchecked")
            HttpResponse<String> response = JsonUtil.readValue(httpResp, HttpResponse.class);
            if (null == response || !response.isSuccess() || StringUtils.isBlank(response.getData())) {
                LOGGER.warn("调用底层生编失败: {}", response);
                priceCacheManager.refreshIfMaintains(fliggyDataInfo.getFareCacheKey());
                return OrderResponse.make(999, "生编失败");
            }

            OrderResp orderResp = null;
            try {
                orderResp = JsonUtil.OBJECT_MAPPER.readValue(response.getData(), OrderResp.class);
            } catch (IOException e) {}

            LOGGER.info("order create pnrcodes: {}", orderResp.getPnrCodes());


            /* pre handle price is not number */
            orderResp.setAirPricingInfo(TransferUtil.removeCurrencySuffix(orderResp.getAirPricingInfo()));

            TransferUtil.fillCabinInfos(request.getRouting(), orderResp.getAirSegment());

            /* markup */
            MarkupResponse markup = markupService.markup(orderResp, NumberUtils.toInt(request.getTripType()));
            LOGGER.info("order markup resp: {}", markup);
            orderResp = markupService.addPrice(orderResp, markup);
            LOGGER.info("order info after markup: {}", orderResp);
            OrderResponse orderResponse = TransferUtil.orderTransform(orderResp, request.getRouting(), request.getSessionId());

            /* 汇率 */
            ArrayList<RoutingElement> routingElements = Lists.newArrayList(orderResponse.getRouting());
            baseInfoService.exchange(routingElements, orderResp.getCurrency());

            /* 调账 */
            adjustPriceService.syncAdjustPrice(routingElements);

            LOGGER.info("order response {}", orderResponse);
            return orderResponse;
        } catch (Exception e) {
            LOGGER.error("create order error: ", e);
            return OrderResponse.make(999, "unknown error");
        }
    }

    private String gengerateNick(String adtType, String gender) {
        /*  男 MR  女  MS   男童MSTR  女童MISS  */
        if (StringUtils.equalsIgnoreCase("adt", adtType)) {
            if (StringUtils.equalsIgnoreCase(gender, "M")) {
                return "MR";
            } else {
                return "MS";
            }
        } else {
            if (StringUtils.equalsIgnoreCase(gender, "M")) {
                return "MSTR";
            } else {
                return "MISS";
            }
        }
    }

    private String buildFreeTxt(PassengerElement passenger, String birth, String expired) {
        return "P/" + passenger.getCardIssuePlace() + "/" + passenger.getCardNum() + "/" + passenger.getNationality()
                + "/" + birth + "/" + passenger.getGender() + "/" + expired + "/" + passenger.getName();
    }

}
