package com.sunshine.dal.dao.impl;

import java.rmi.ServerException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunshine.common.exception.ErrorMsg;
import com.sunshine.dal.dao.IQuotaPolicyDao;
import com.sunshine.dal.entity.QuotaPolicyEntity;

@Repository
public class QuotaPolicyDaoImpl extends BaseDAO<QuotaPolicyEntity> implements
		IQuotaPolicyDao {

	public QuotaPolicyEntity getQuotaPolicy(long id) {
		return findById(id);
	}

	public List<QuotaPolicyEntity> queryQuotaPolicies(Integer gds,
			String targetBranch, Integer pcc_status) {
		List<Criterion> criteriaList = new LinkedList<Criterion>();
		if (StringUtils.isNotBlank(targetBranch)) {
			criteriaList.add(Restrictions.eq("targetBranch", targetBranch));
		}
		if (gds != null) {
			criteriaList.add(Restrictions.eq("gds", gds));
		}
		if (pcc_status != null) {
			criteriaList.add(Restrictions.eq("pccStatus", pcc_status));
		}
		Criterion[] queryCriteria = new Criterion[criteriaList.size()];
		return findByCriteria(criteriaList.toArray(queryCriteria));
	}

	public void delQuotaPolicy(Long... id) throws ServerException {
		List<Criterion> criteriaList = new LinkedList<Criterion>();
		criteriaList.add(Restrictions.in("id", Arrays.asList(id)));
		Criterion[] queryCriteria = new Criterion[criteriaList.size()];
		List<QuotaPolicyEntity> entities = findByCriteria(criteriaList
				.toArray(queryCriteria));
		if (entities == null || entities.size() == 0) {
			throw new ServerException(
					ErrorMsg.ERROR_NOT_EXIST_OR_ALREADY_DELETED);
		}
		for (QuotaPolicyEntity entity : entities) {
			delete(entity);
		}

	}

	public long saveOrUpdateQuotaPolicy(QuotaPolicyEntity quotaPolicyEntity) {
		saveOrUpdate(quotaPolicyEntity);
		return quotaPolicyEntity.getId();
	}

}
