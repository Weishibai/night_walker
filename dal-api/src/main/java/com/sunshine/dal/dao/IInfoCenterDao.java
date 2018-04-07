package com.sunshine.dal.dao;

import com.sunshine.dal.entity.InfoCenterEntity;

import java.util.List;

public interface IInfoCenterDao {

    List<InfoCenterEntity> queryByAirportCode(String airportCode);

    List<InfoCenterEntity> queryByCityCode(String cityCode);

    InfoCenterEntity uniqueQuery(String airportCode, String cityCode);

    long upsertInfocenter(InfoCenterEntity entity);
}
