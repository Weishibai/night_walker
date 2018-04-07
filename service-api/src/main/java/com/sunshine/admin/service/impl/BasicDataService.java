package com.sunshine.admin.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sunshine.admin.service.IBasicDataService;
import com.sunshine.common.vo.ExchangeInfo;
import com.sunshine.common.vo.InfoCenterData;
import com.sunshine.dal.dao.IExchangeDao;
import com.sunshine.dal.dao.IInfoCenterDao;
import com.sunshine.dal.entity.ExchangeEntity;
import com.sunshine.dal.entity.InfoCenterEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class BasicDataService implements IBasicDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicDataService.class);

    @Resource
    private IExchangeDao exchangeDao;

    @Resource
    private IInfoCenterDao infoCenterDao;

    @Override
    public List<ExchangeInfo> queryExchange(ExchangeInfo request) {
        if (null == request) {
            LOGGER.warn("exchange request is null");
            return Collections.emptyList();
        }

        List<ExchangeEntity> exchanges = exchangeDao.queryExchange(request.getSrcCurrency());
        if (CollectionUtils.isEmpty(exchanges)) {
            LOGGER.warn("exchange resp is empty for request: {}", request);
            return Collections.emptyList();
        }

        return Lists.transform(exchanges, new Function<ExchangeEntity, ExchangeInfo>() {
            @Override
            public ExchangeInfo apply(ExchangeEntity input) {
                return ExchangeInfo.make(input);
            }
        });
    }

    @Override
    public boolean upsertExchange(ExchangeInfo request) {
        if (null == request) {
            LOGGER.warn("exchange request is null");
            return false;
        }

        ExchangeEntity entity = new ExchangeEntity();
        entity.setSrcCurrency(request.getSrcCurrency());
        entity.setDstCurrency(request.getDstCurrency());
        entity.setRate(request.getRate());
        return exchangeDao.upsertExchange(entity) > 0;
    }

    @Override
    public List<InfoCenterData> queryInfoCenter(InfoCenterData request) {
        if (null == request) {
            LOGGER.warn("info center request is null");
            return Collections.emptyList();
        }

        List<InfoCenterEntity> entities = Lists.newArrayList();
        if (StringUtils.isNotBlank(request.getAirportCode())) {
            LOGGER.info("request airport is {}", request.getAirportCode());
            entities.addAll(infoCenterDao.queryByAirportCode(request.getAirportCode()));
        }

        if (StringUtils.isNotBlank(request.getCityCode())) {
            LOGGER.info("request city code is {}", request.getCityCode());
            entities.addAll(infoCenterDao.queryByCityCode(request.getCityCode()));
        }

        if (CollectionUtils.isEmpty(entities)) {
            LOGGER.warn("info center resp is empty for request: {}", request);
            return Collections.emptyList();
        }

        return Lists.transform(entities, new Function<InfoCenterEntity, InfoCenterData>() {
            @Override
            public InfoCenterData apply(InfoCenterEntity input) {
                return InfoCenterData.make(input);
            }
        });
    }

    @Override
    public boolean upsertInfocenter(InfoCenterData infoCenterData) {
        if (null == infoCenterData) {
            LOGGER.warn("info center request is null");
            return false;
        }
        return infoCenterDao.upsertInfocenter(InfoCenterData.build(infoCenterData)) > 0;
    }
}
