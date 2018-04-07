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
import com.sunshine.dal.dao.IWhitelistPolicyDao;
import com.sunshine.dal.entity.WhitelistPolicyEntity;

@Repository
public class WhitelistPolicyDaoImpl extends BaseDAO<WhitelistPolicyEntity>
		implements IWhitelistPolicyDao {

	public WhitelistPolicyEntity getWhitelistPolicy(long id) {
		return findById(id);
	}

	public List<WhitelistPolicyEntity> queryWhitelistPolicies(Integer gds,
															  String targetBranch, String fromState, String toState) {
		List<Criterion> criteriaList = new LinkedList<>();
		if (StringUtils.isNotBlank(fromState)) {
//			criteriaList.add(Restrictions.eq("fromState", fromState));
			criteriaList.add(Restrictions.or(Restrictions.like("fromState", fromState, MatchMode.ANYWHERE), Restrictions.eq("fromState", "*")));

		}
		if (StringUtils.isNotBlank(toState)) {
//			criteriaList.add(Restrictions.eq("toState", toState));
			criteriaList.add(Restrictions.or(Restrictions.like("toState", toState, MatchMode.ANYWHERE), Restrictions.eq("toState", "*")));
		}
		if (StringUtils.isNotBlank(targetBranch)) {
			criteriaList.add(Restrictions.eq("targetBranch", targetBranch));
		}
		if (gds != null) {
//			criteriaList.add(Restrictions.eq("gds", gds));
			criteriaList.add(Restrictions.or(Restrictions.eq("gds", gds), Restrictions.eq("gds", 3)));
		}
		Criterion[] queryCriteria = new Criterion[criteriaList.size()];
		return findByCriteria(criteriaList.toArray(queryCriteria));
	}

	public void delWhitelistPolicy(Long... id) throws ServerException {
		List<Criterion> criteriaList = new LinkedList<Criterion>();
		criteriaList.add(Restrictions.in("id", Arrays.asList(id)));
		Criterion[] queryCriteria = new Criterion[criteriaList.size()];
		List<WhitelistPolicyEntity> entities = findByCriteria(criteriaList
				.toArray(queryCriteria));
		if (entities == null || entities.size() == 0) {
			throw new ServerException(
					ErrorMsg.ERROR_NOT_EXIST_OR_ALREADY_DELETED);
		}
		for (WhitelistPolicyEntity entity : entities) {
			delete(entity);
		}
	}

	public long saveOrUpdateWhitelistPolicy(
			WhitelistPolicyEntity whitelistPolicyEntity) {
		saveOrUpdate(whitelistPolicyEntity);
		return whitelistPolicyEntity.getId();
	}

	@Override
	public List<WhitelistPolicyEntity> queryWhitelistPolicies() {
		return findAll();
	}

}
