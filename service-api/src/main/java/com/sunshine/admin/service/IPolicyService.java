package com.sunshine.admin.service;

import java.rmi.ServerException;
import java.util.List;

import com.sunshine.common.vo.GDS;
import com.sunshine.common.vo.Platform;
import com.sunshine.common.vo.PricePolicy;
import com.sunshine.common.vo.QuotaPolicy;
import com.sunshine.common.vo.TripType;
import com.sunshine.common.vo.WhitelistPolicy;

public interface IPolicyService {

	public List<PricePolicy> queryPricePolicies(TripType tripType, GDS gds,
			Platform platform, String carrier, Long id, String fromCity,
			String toCity);

	public long saveOrUpdatePricePolicy(PricePolicy pricePolicy);

	public void delPricePolicyById(Long... id) throws ServerException;

	public List<QuotaPolicy> queryQuotaPolicies(Integer status, GDS gds,
			Long id, String targetBranch);

	public long saveOrUpdateQuotaPolicy(QuotaPolicy quotaPolicy);

	public void delQuotaPolicyById(Long... id) throws ServerException;

	public List<WhitelistPolicy> queryWhitelistPolicies();

	public List<WhitelistPolicy> queryWhitelistPolicies(GDS gds,
			String targetBranch, Long id, String fromState, String toState);

	public long saveOrUpdateWhitelistPolicy(WhitelistPolicy whitelistPolicy);

	public void delWhitePolicyById(Long... id) throws ServerException;
}
