package com.sunshine.dal.dao;

import java.rmi.ServerException;
import java.util.List;

import com.sunshine.dal.entity.WhitelistPolicyEntity;

public interface IWhitelistPolicyDao {

	public WhitelistPolicyEntity getWhitelistPolicy(long id);

	public List<WhitelistPolicyEntity> queryWhitelistPolicies(Integer gds,
			String targetBranch, String fromState, String toState);

	public List<WhitelistPolicyEntity> queryWhitelistPolicies();

	public void delWhitelistPolicy(Long... id) throws ServerException;

	public long saveOrUpdateWhitelistPolicy(
			WhitelistPolicyEntity whitelistPolicyEntity);
}
