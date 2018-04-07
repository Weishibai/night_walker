package com.sunshine.dal.dao;

import java.rmi.ServerException;
import java.util.List;

import com.sunshine.dal.entity.PricePolicyEntity;
import com.sunshine.dal.entity.QuotaPolicyEntity;

public interface IQuotaPolicyDao {

	public QuotaPolicyEntity getQuotaPolicy(long id);

	public List<QuotaPolicyEntity> queryQuotaPolicies(Integer gds,
			String targetBranch, Integer pcc_status);

	public void delQuotaPolicy(Long... id) throws ServerException;

	public long saveOrUpdateQuotaPolicy(QuotaPolicyEntity quotaPolicyEntity);
}
