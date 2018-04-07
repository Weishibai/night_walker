package com.sunshine.dal.dao;

import com.sunshine.dal.entity.PricePolicyEntity;

import java.rmi.ServerException;
import java.util.List;

public interface IPricePolicyDao {

    public PricePolicyEntity getPricePolicy(long id);

    public List<PricePolicyEntity> queryPricePolicies(Integer tripType,
                                                      Integer gds, Integer platform, String carrier, String fromCity,
                                                      String toCity);

    public void delPricePolicy(Long[] id) throws ServerException;

    public long saveOrUpdatePricePolicy(PricePolicyEntity pricePolicyEntity);
}
