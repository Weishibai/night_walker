package com.sunshine.dal.dao;

import com.sunshine.dal.entity.ExchangeEntity;

import java.util.List;

public interface IExchangeDao {

    List<ExchangeEntity> queryExchange(String srcCurrency);

    long upsertExchange(ExchangeEntity entity);
}
