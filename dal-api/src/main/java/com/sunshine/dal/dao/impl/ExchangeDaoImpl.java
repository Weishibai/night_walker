package com.sunshine.dal.dao.impl;

import com.sunshine.dal.dao.IExchangeDao;
import com.sunshine.dal.entity.ExchangeEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ExchangeDaoImpl extends BaseDAO<ExchangeEntity> implements IExchangeDao {

    @Override
    public List<ExchangeEntity> queryExchange(String srcCurrency) {
        if (StringUtils.isEmpty(srcCurrency)) {
            return Collections.emptyList();
        }

        List<Criterion> criteriaList = new LinkedList<>();

        if (StringUtils.isNotBlank(srcCurrency)) {
            criteriaList.add(Restrictions.eq("srcCurrency", srcCurrency));
        }

        Criterion[] queryCriteria = new Criterion[criteriaList.size()];
        return findByCriteria(criteriaList.toArray(queryCriteria));
    }

    @Override
    public long upsertExchange(ExchangeEntity entity) {
        if (null == entity) {
            return -1;
        }

        List<ExchangeEntity> exchangeEntities = queryExchange(entity.getSrcCurrency());
        if (CollectionUtils.isEmpty(exchangeEntities)) {
            saveOrUpdate(entity);
        } else {
            for (ExchangeEntity exchangeEntity : exchangeEntities) {
                exchangeEntity.setRate(entity.getRate());
                saveOrUpdate(exchangeEntity);
            }
        }
        return 1;
    }
}
