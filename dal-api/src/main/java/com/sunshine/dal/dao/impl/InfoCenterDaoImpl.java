package com.sunshine.dal.dao.impl;

import com.sunshine.dal.dao.IInfoCenterDao;
import com.sunshine.dal.entity.InfoCenterEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InfoCenterDaoImpl extends BaseDAO<InfoCenterEntity> implements IInfoCenterDao {


    @Override
    public List<InfoCenterEntity> queryByAirportCode(String airportCode) {
        if (StringUtils.isBlank(airportCode)) {
            return Collections.emptyList();
        }

        List<Criterion> criteriaList = new LinkedList<>();
        criteriaList.add(Restrictions.eq("airportCode", airportCode));

        Criterion[] queryCriteria = new Criterion[criteriaList.size()];
        return findByCriteria(criteriaList.toArray(queryCriteria));
    }

    @Override
    public List<InfoCenterEntity> queryByCityCode(String cityCode) {
        if (StringUtils.isBlank(cityCode)) {
            return Collections.emptyList();
        }

        List<Criterion> criteriaList = new LinkedList<>();
        criteriaList.add(Restrictions.eq("cityCode", cityCode));

        Criterion[] queryCriteria = new Criterion[criteriaList.size()];
        return findByCriteria(criteriaList.toArray(queryCriteria));
    }

    @Override
    public InfoCenterEntity uniqueQuery(String airportCode, String cityCode) {
        if (StringUtils.isBlank(airportCode) && StringUtils.isBlank(cityCode)) {
            return null;
        }

        List<Criterion> criteriaList = new LinkedList<>();
        criteriaList.add(Restrictions.eq("airportCode", airportCode));
        criteriaList.add(Restrictions.eq("cityCode", cityCode));

        Criterion[] queryCriteria = new Criterion[criteriaList.size()];
        return findUniqueByCriteria(criteriaList.toArray(queryCriteria));
    }

    @Override
    public long upsertInfocenter(InfoCenterEntity entity) {
        if (null == entity) {
            return -1;
        }
        saveOrUpdate(entity);
        return 1;
    }
}
