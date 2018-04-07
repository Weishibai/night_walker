package com.sunshine.dal.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sunshine.dal.dao.IUserDao;
import com.sunshine.dal.entity.UserEntity;

@Repository
public class UserDaoImpl extends BaseDAO<UserEntity> implements IUserDao {

	@Override
	public UserEntity queryUser(String uname) {
		List<Criterion> criteriaList = new LinkedList<Criterion>();
		criteriaList.add(Restrictions.eq("uname", uname));
		Criterion[] queryCriteria = new Criterion[criteriaList.size()];
		return findUniqueByCriteria(criteriaList.toArray(queryCriteria));
	}

}
