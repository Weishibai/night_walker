package com.sunshine.publicserver.service;

import com.google.common.collect.Maps;
import com.sunshine.publicserver.enums.Platform;
import com.sunshine.publicserver.service.booking.IBookingService;
import com.sunshine.publicserver.service.order.IOrderService;
import com.sunshine.publicserver.service.pay.IPayService;
import com.sunshine.publicserver.service.search.ISearchService;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PlatformServiceRouter {

    @Resource
    private List<ISearchService> searchServices;

    @Resource
    private List<IBookingService> verifyServices;

    @Resource
    private List<IOrderService> orderServices;

    @Resource
    private List<IPayService> payServices;

    private static Map<Platform, ISearchService> searchServiceMap;

    private static Map<Platform, IBookingService> verifyServiceMap;

    private static Map<Platform, IOrderService> orderServiceMap;

    private static Map<Platform, IPayService> payServiceMap;

    @PostConstruct
    public void init() {
        searchServiceMap = Maps.newHashMap();
        verifyServiceMap = Maps.newHashMap();
        orderServiceMap = Maps.newHashMap();
        payServiceMap = Maps.newHashMap();
        for (ISearchService searchService : searchServices) {
            searchServiceMap.put(searchService.platform(), searchService);
        }

        for (IBookingService verifyService : verifyServices) {
            verifyServiceMap.put(verifyService.platform(), verifyService);
        }

        for (IOrderService orderService : orderServices) {
            orderServiceMap.put(orderService.platform(), orderService);
        }

        for (IPayService payService : payServices) {
            payServiceMap.put(payService.platform(), payService);
        }



    }

    public <T, V> V search(T searchRequest, Platform platform) {
        return (V) ((ISearchService) MapUtils.getObject(searchServiceMap, platform)).search(searchRequest);
    }

    public <T, V> V verify(T verifyRequest, Platform platform) {
        return (V) ((IBookingService) MapUtils.getObject(verifyServiceMap, platform)).booking(verifyRequest);
    }

    public <T, V> V order(T orderRequest, Platform platform) {
        return (V) ((IOrderService) MapUtils.getObject(orderServiceMap, platform)).order(orderRequest);
    }

    public <T, V> V pay(T payRequest, Platform platform) {
        return (V) ((IPayService) MapUtils.getObject(payServiceMap, platform)).pay(payRequest);
    }

}
