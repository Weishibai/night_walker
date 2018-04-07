package com.sunshine.dal.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public abstract class BaseDAO<T> {

	private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

	private final static int BATCH_MAX_ROW = 10000;

	protected SessionFactory sessionFactory;

	public final Class<T> type;

	@SuppressWarnings("unchecked")
	public BaseDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void insert(T object) {
		this.getSessionFactory().getCurrentSession().save(object);
		this.getSessionFactory().getCurrentSession().flush();
	}

	public void insertSync(T object) {
		this.getSessionFactory().getCurrentSession().save(object);
		this.getSessionFactory().getCurrentSession().flush();
	}

	public int batchInsertSync(T[] array) {
		Session session = this.getSessionFactory().openSession();
		try {
			for (int i = 0; i < array.length; i++) {
				session.persist(array[i]);
				if ((i + 1) % BATCH_MAX_ROW == 0) {
					session.flush();
				}
			}
			session.flush();
		} finally {
			session.close();
		}
		return array.length;
	}

	public int batchInsert(final T[] array) {
		Session session = this.getSessionFactory().openSession();
		try {
			for (int i = 0; i < array.length; i++) {
				session.save(array[i]);
				if ((i + 1) % BATCH_MAX_ROW == 0) {
					session.flush();
				}
			}
			session.flush();
		} finally {
			session.close();
		}
		return array.length;
	}

	public void delete(T object) {
		this.getSessionFactory().getCurrentSession().delete(object);
		this.getSessionFactory().getCurrentSession().flush();
	}

	public int batchDelete(final T[] array) {
		Session session = this.getSessionFactory().openSession();
		try {
			for (int i = 0; i < array.length; i++) {
				session.delete(array[i]);
				if ((i + 1) % BATCH_MAX_ROW == 0) {
					session.flush();
				}
			}
			session.flush();
		} finally {
			session.close();
		}
		return array.length;
	}

	public void update(T object) {
		this.getSessionFactory().getCurrentSession().update(object);
		this.getSessionFactory().getCurrentSession().flush();
	}

	public void saveOrUpdate(T object) {
		this.getSessionFactory().getCurrentSession().saveOrUpdate(object);
		this.getSessionFactory().getCurrentSession().flush();
	}

	public void save(T object) {
		this.getSessionFactory().getCurrentSession().save(object);
		this.getSessionFactory().getCurrentSession().flush();
	}

	public int batchUpdate(final T[] array) {
		return batchUpdate(array, BATCH_MAX_ROW);
	}

	public int batchUpdate(final T[] array, int flushStep) {
		Assert.isTrue(flushStep > 0);
		Session session = this.getSessionFactory().openSession();
		try {
			for (int i = 0; i < array.length; i++) {
				session.update(array[i]);
				if ((i + 1) % BATCH_MAX_ROW == 0) {
					session.flush();
				}
			}
			session.flush();
		} finally {
			session.close();
		}
		return array.length;
	}

	@SuppressWarnings("unchecked")
	public List<T> batchGetRecordsKeepOrder(final List<Long> ids) {
		Session session = this.getSessionFactory().getCurrentSession();
		List<T> results = new ArrayList<T>();
		for (Long id : ids) {
			T entity = (T) session.get(type, id);
			results.add(entity);
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> result = null;
		Criteria query = this.sessionFactory.getCurrentSession()
				.createCriteria(type);
		result = query.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	public T findById(Serializable id) {
		return (T) this.sessionFactory.getCurrentSession().get(type, id);
	}

	@SuppressWarnings("unchecked")
	public T findByIdAndInit(Serializable id) {
		T obj = (T) this.sessionFactory.getCurrentSession().get(type, id);
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		}
		return obj;
	}

	public List<T> findByCriteria(final Criterion... queryCriteria) {
		return findByCriteria(null, null, null, null, queryCriteria);
	}

	public List<T> findByCriteria(final Order[] order,
			final Criterion... queryCriteria) {
		return findByCriteria(null, order, null, null, queryCriteria);
	}

	public T findUniqueByCriteria(Criterion... queryCriteria) {
		List<T> results = findByCriteria(queryCriteria);
		if (results.size() == 0 || results.size() > 1) {
			return null;
		} else {
			return results.get(0);
		}
	}

	@SuppressWarnings("rawtypes")
	public List getProjectColumnsByCriteria(final String[] columns,
			final Criterion... queryCriteria) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				type);
		for (Criterion c : queryCriteria) {
			criteria.add(c);
		}
		if (columns.length > 0) {
			ProjectionList proList = Projections.projectionList();
			for (String column : columns) {
				proList.add(Projections.groupProperty(column));
			}
			criteria.setProjection(proList);
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final String[] joinClass,
			final Order[] order, final Integer firstResult,
			final Integer maxResults, final Criterion... queryCriteria) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				type);

		if (joinClass != null) {
			for (String classStr : joinClass) {
				criteria = criteria.createAlias(classStr, classStr);
			}
		}

		for (Criterion c : queryCriteria) {
			criteria.add(c);
		}

		if (order != null) {
			for (Order o : order) {
				criteria.addOrder(o);
			}
		}

		if (firstResult != null) {
			criteria.setFirstResult(firstResult);
		}

		if (maxResults != null) {
			criteria.setMaxResults(maxResults);
			criteria.setFetchSize(maxResults); // just a hint
		}

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql) {
		List<T> objs = null;
		objs = sessionFactory.getCurrentSession().createQuery(hql).list();
		return objs;
	}

	public int countByCriteria(final String[] joinClass,
			final Criterion... queryCriteria) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				type);
		if (joinClass != null) {
			for (String classStr : joinClass) {
				criteria = criteria.createAlias(classStr, classStr);
			}
		}

		for (Criterion c : queryCriteria) {
			criteria.add(c);
		}

		Object result = criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		return result == null ? 0 : ((Number) result).intValue();
	}

	public long getSumByCriteria(String propertyName,
			Criterion... queryCriteria) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				type);

		for (Criterion c : queryCriteria) {
			criteria.add(c);
		}
		Number number = (Number) criteria.setProjection(
				Projections.sum(propertyName)).uniqueResult();

		return number == null ? 0 : number.longValue();
	}

	/*
	 * using JDBC API to access MySQL
	 */
	@SuppressWarnings("rawtypes")
	public List executeQuery(String sql) {
		Query sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return sqlQuery.list();
	}

	public int executeUpdate(String sql) {
		Query sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
		return sqlQuery.executeUpdate();
	}

	/**
	 * 获取当前的session
	 * 
	 * @return
	 */
	public org.hibernate.Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
}
