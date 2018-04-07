package com.sunshine.admin.service;

import com.sunshine.common.vo.ExchangeInfo;
import com.sunshine.common.vo.InfoCenterData;

import java.util.List;

public interface IBasicDataService {

    List<ExchangeInfo> queryExchange(ExchangeInfo request);

    boolean upsertExchange(ExchangeInfo request);

    List<InfoCenterData> queryInfoCenter(InfoCenterData request);

    boolean upsertInfocenter(InfoCenterData infoCenterData);

}
