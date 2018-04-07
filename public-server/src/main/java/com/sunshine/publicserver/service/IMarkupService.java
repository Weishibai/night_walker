package com.sunshine.publicserver.service;

import com.sunshine.publicserver.vo.MarkupResponse;
import com.sunshine.service.pojo.FareSearchResponse;
import com.sunshine.service.pojo.OrderResp;
import com.sunshine.service.pojo.PayResp;
import com.sunshine.service.pojo.VerifyResp;

import java.util.Map;

public interface IMarkupService {

    Map<Integer, MarkupResponse> markup(FareSearchResponse fareSearchResponse);

    FareSearchResponse addPrice(FareSearchResponse origin, Map<Integer, MarkupResponse> markups);

    MarkupResponse markup(VerifyResp verifyResp);

    VerifyResp addPrice(VerifyResp origin, MarkupResponse markups);

    MarkupResponse markup(OrderResp orderResp, int tripType);

    OrderResp addPrice(OrderResp origin, MarkupResponse markups);

    MarkupResponse markup(PayResp payResp, int tripType);

    PayResp addPrice(PayResp origin, MarkupResponse markups);

}
