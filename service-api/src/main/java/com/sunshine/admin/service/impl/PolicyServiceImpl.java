package com.sunshine.admin.service.impl;

import java.rmi.ServerException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunshine.admin.service.IPolicyService;
import com.sunshine.common.vo.GDS;
import com.sunshine.common.vo.Platform;
import com.sunshine.common.vo.PricePolicy;
import com.sunshine.common.vo.QuotaPolicy;
import com.sunshine.common.vo.TripType;
import com.sunshine.common.vo.WhitelistPolicy;
import com.sunshine.dal.dao.IPricePolicyDao;
import com.sunshine.dal.dao.IQuotaPolicyDao;
import com.sunshine.dal.dao.IWhitelistPolicyDao;
import com.sunshine.dal.entity.PricePolicyEntity;
import com.sunshine.dal.entity.QuotaPolicyEntity;
import com.sunshine.dal.entity.WhitelistPolicyEntity;

import javax.annotation.Resource;

@Transactional
@Service
public class PolicyServiceImpl implements IPolicyService {

	private static final Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

	@Resource
	private IPricePolicyDao pricePolicyDao;

	@Resource
	private IQuotaPolicyDao quotaPolicyDao;

	@Resource
	private IWhitelistPolicyDao whitelistPolicyDao;

	public IPricePolicyDao getPricePolicyDao() {
		return pricePolicyDao;
	}

	public void setPricePolicyDao(IPricePolicyDao pricePolicyDao) {
		this.pricePolicyDao = pricePolicyDao;
	}

	public IQuotaPolicyDao getQuotaPolicyDao() {
		return quotaPolicyDao;
	}

	public void setQuotaPolicyDao(IQuotaPolicyDao quotaPolicyDao) {
		this.quotaPolicyDao = quotaPolicyDao;
	}

	@Override
	public List<PricePolicy> queryPricePolicies(TripType tripType, GDS gds,
			Platform platform, String carrier, Long id, String fromCity, String toCity) {

		List<PricePolicyEntity> pricePolicyEntities;
		if (id != null && id != 0) {
			pricePolicyEntities = new LinkedList<>();
			PricePolicyEntity entity = this.pricePolicyDao.getPricePolicy(id);
			if (entity != null) {
				pricePolicyEntities.add(entity);
			}
		} else {
			pricePolicyEntities = this.pricePolicyDao.queryPricePolicies(
					tripType == null ? null : tripType.getCode(),
					gds == null ? null : gds.getCode(), platform == null ? null
							: platform.getCode(), carrier, fromCity, toCity);
		}

		List<PricePolicy> pricePolicies = new LinkedList<>();
		for (PricePolicyEntity pricePolicyEntity : pricePolicyEntities) {
			PricePolicy pricePolicy = PricePolicy.convertFromEntity(pricePolicyEntity);
			pricePolicies.add(pricePolicy);
		}
		logger.debug("pricePolicies:{}", pricePolicies);
		return pricePolicies;
	}

	@Override
	public long saveOrUpdatePricePolicy(PricePolicy pricePolicy) {
		PricePolicyEntity pricePolicyEntity = PricePolicy.convertToEntity(pricePolicy);
		logger.debug("target pricePolicyEntity: {}", pricePolicyEntity);
		return this.pricePolicyDao.saveOrUpdatePricePolicy(pricePolicyEntity);
	}

	@Override
	public void delPricePolicyById(Long[] id) throws ServerException {
		this.pricePolicyDao.delPricePolicy(id);
	}

	@Override
	public List<QuotaPolicy> queryQuotaPolicies(Integer status, GDS gds,
			Long id, String targetBranch) {
		List<QuotaPolicyEntity> entities = this.quotaPolicyDao
				.queryQuotaPolicies(gds == null ? null : gds.getCode(),
						targetBranch, status);
		List<QuotaPolicy> quotaPolicies = new LinkedList<QuotaPolicy>();
		for (QuotaPolicyEntity entity : entities) {
			QuotaPolicy quotaPolicy = QuotaPolicy.convertFromEntity(entity);
			quotaPolicies.add(quotaPolicy);
		}
		return quotaPolicies;
	}

	@Override
	public long saveOrUpdateQuotaPolicy(QuotaPolicy quotaPolicy) {
		QuotaPolicyEntity entity = QuotaPolicy.convertToEntity(quotaPolicy);
		return this.quotaPolicyDao.saveOrUpdateQuotaPolicy(entity);
	}

	@Override
	public void delQuotaPolicyById(Long... id) throws ServerException {
		this.quotaPolicyDao.delQuotaPolicy(id);
	}

	@Override
	public List<WhitelistPolicy> queryWhitelistPolicies(GDS gds,
			String targetBranch, Long id, String fromState, String toState) {
		List<WhitelistPolicyEntity> entities;
		if (id != null && id != 0) {
			entities = new LinkedList<>();
			WhitelistPolicyEntity entity = this.whitelistPolicyDao
					.getWhitelistPolicy(id);
			if (entity != null) {
				entities.add(entity);
			}
		} else {
			entities = this.whitelistPolicyDao.queryWhitelistPolicies(
					gds == null ? null : gds.getCode(), targetBranch,
					fromState, toState);
		}
		List<WhitelistPolicy> whitelistPolicies = new LinkedList<>();
		for (WhitelistPolicyEntity entity : entities) {
			WhitelistPolicy whitePolicy = WhitelistPolicy
					.convertFromEntity(entity);
			whitelistPolicies.add(whitePolicy);
		}
		return whitelistPolicies;
	}

	@Override
	public long saveOrUpdateWhitelistPolicy(WhitelistPolicy whitelistPolicy) {
		WhitelistPolicyEntity entity = WhitelistPolicy
				.convertToEntity(whitelistPolicy);
		return this.whitelistPolicyDao.saveOrUpdateWhitelistPolicy(entity);
	}

	@Override
	public void delWhitePolicyById(Long... id) throws ServerException {
		this.whitelistPolicyDao.delWhitelistPolicy(id);
	}

	@Override
	public List<WhitelistPolicy> queryWhitelistPolicies() {
		return convert(this.whitelistPolicyDao.queryWhitelistPolicies());
	}

	private List<WhitelistPolicy> convert(List<WhitelistPolicyEntity> list) {
		List<WhitelistPolicy> whitelistPolicies = new LinkedList<WhitelistPolicy>();
		for (WhitelistPolicyEntity entity : list) {
			WhitelistPolicy whitePolicy = WhitelistPolicy
					.convertFromEntity(entity);
			whitelistPolicies.add(whitePolicy);
		}
		return whitelistPolicies;
	}

}
