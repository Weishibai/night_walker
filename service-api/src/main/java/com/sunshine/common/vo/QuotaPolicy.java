package com.sunshine.common.vo;

import com.sunshine.dal.entity.QuotaPolicyEntity;

public class QuotaPolicy {

	private long id;
	private String targetBranch;
	private GDS gds;
	private long limit;
	private long surplus;
	private int status;
	private long agentId;

	public QuotaPolicy() {
	}

	public QuotaPolicy(long id, String targetBranch, GDS gds, long limit,
			long surplus, int status) {
		super();
		this.id = id;
		this.targetBranch = targetBranch;
		this.gds = gds;
		this.limit = limit;
		this.surplus = surplus;
		this.status = status;
	}

	public static QuotaPolicy convertFromEntity(QuotaPolicyEntity entity) {
		QuotaPolicy quotaPolicy = new QuotaPolicy();
		quotaPolicy.setGds(GDS.get(entity.getGds()));
		quotaPolicy.setId(entity.getId());
		quotaPolicy.setLimit(entity.getLimit());
		quotaPolicy.setStatus(entity.getPccStatus());
		quotaPolicy.setSurplus(entity.getSurplus());
		quotaPolicy.setTargetBranch(entity.getTargetBranch());
		return quotaPolicy;
	}

	public static QuotaPolicyEntity convertToEntity(QuotaPolicy quotaPolicy) {
		QuotaPolicyEntity entity = new QuotaPolicyEntity();
		entity.setAgentId(quotaPolicy.getAgentId());
		entity.setGds(quotaPolicy.getGds().getCode());
		entity.setId(quotaPolicy.getId());
		entity.setLimit(quotaPolicy.getLimit());
		entity.setPccStatus(quotaPolicy.getStatus());
		entity.setSurplus(quotaPolicy.getSurplus());
		entity.setTargetBranch(quotaPolicy.getTargetBranch());
		return entity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTargetBranch() {
		return targetBranch;
	}

	public void setTargetBranch(String targetBranch) {
		this.targetBranch = targetBranch;
	}

	public GDS getGds() {
		return gds;
	}

	public void setGds(GDS gds) {
		this.gds = gds;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public long getSurplus() {
		return surplus;
	}

	public void setSurplus(long surplus) {
		this.surplus = surplus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	@Override
	public String toString() {
		return "QuotaPolicy [id=" + id + ", targetBranch=" + targetBranch
				+ ", gds=" + gds + ", limit=" + limit + ", surplus=" + surplus
				+ ", status=" + status + ", agentId=" + agentId + "]";
	}

}
