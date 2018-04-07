package com.sunshine.dal.dao.impl;

import java.rmi.ServerException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunshine.common.exception.ErrorMsg;
import com.sunshine.dal.dao.IPricePolicyDao;
import com.sunshine.dal.entity.PricePolicyEntity;

@Repository
public class PricePolicyDaoImpl extends BaseDAO<PricePolicyEntity> implements
		IPricePolicyDao {

	public PricePolicyEntity getPricePolicy(long id) {
		return findById(id);
	}

	public List<PricePolicyEntity> queryPricePolicies(Integer tripType,
			Integer gds, Integer platform, String carrier, String fromCity,
			String toCity) {
		List<Criterion> criteriaList = new LinkedList<>();
		if (StringUtils.isNotBlank(fromCity)) {
			criteriaList.add(Restrictions.or(Restrictions.like("fromCity", fromCity, MatchMode.ANYWHERE), Restrictions.eq("fromCity", "*")));
//			criteriaList.add(Restrictions.eq("fromCity", fromCity));
		}

		if (StringUtils.isNotBlank(toCity)) {
			criteriaList.add(Restrictions.or(Restrictions.like("toCity", toCity, MatchMode.ANYWHERE), Restrictions.eq("toCity", "*")));
//			criteriaList.add(Restrictions.eq("toCity", toCity));
		}

		if (StringUtils.isNotBlank(carrier)) {
			criteriaList.add(Restrictions.or(Restrictions.like("carrier", carrier, MatchMode.ANYWHERE), Restrictions.eq("carrier", "*")));
//			criteriaList.add(Restrictions.eq("carrier", carrier));
		}

		if (tripType != null) {
			criteriaList.add(Restrictions.or(Restrictions.eq("tripType", tripType), Restrictions.eq("tripType", 3)));
//			criteriaList.add(Restrictions.eq("tripType", tripType));
		}

		if (gds != null) {
//			criteriaList.add(Restrictions.eq("gds", gds));
			criteriaList.add(Restrictions.or(Restrictions.eq("gds", gds), Restrictions.eq("gds", 3)));
		}

		if (platform != null) {
//			criteriaList.add(Restrictions.eq("platform", platform));
			criteriaList.add(Restrictions.or(Restrictions.eq("platform", platform), Restrictions.eq("platform", 3)));
		}

		criteriaList.add(Restrictions.eq("status", 1));
		Criterion[] queryCriteria = new Criterion[criteriaList.size()];
		return findByCriteria(criteriaList.toArray(queryCriteria));
	}

	public void delPricePolicy(Long[] id) throws ServerException {
		List<Criterion> criteriaList = new LinkedList<Criterion>();
		criteriaList.add(Restrictions.in("id", Arrays.asList(id)));
		Criterion[] queryCriteria = new Criterion[criteriaList.size()];
		List<PricePolicyEntity> entities = findByCriteria(criteriaList
				.toArray(queryCriteria));
		if (entities == null || entities.size() == 0) {
			throw new ServerException(
					ErrorMsg.ERROR_NOT_EXIST_OR_ALREADY_DELETED);
		}
		for (PricePolicyEntity entity : entities) {
			entity.setStatus(0);
			saveOrUpdate(entity);
		}
	}

	public long saveOrUpdatePricePolicy(PricePolicyEntity pricePolicyEntity) {
		saveOrUpdate(pricePolicyEntity);
		return pricePolicyEntity.getId();
	}

}
