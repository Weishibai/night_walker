package com.sunshine.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "tb_quota_policy")
@org.hibernate.annotations.Table(appliesTo = "tb_quota_policy", indexes = { @Index(columnNames = { "agent_id" }, name = "index_agent_id") })
public class QuotaPolicyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "agent_id", nullable = false)
	private long agentId;

	@Column(name = "target_branch", nullable = false)
	private String targetBranch;

	@Column(name = "gds", nullable = false)
	private int gds;

	@Column(name = "uplimit", nullable = false)
	private long limit;

	@Column(name = "surplus", nullable = false)
	private long surplus;

	@Column(name = "pcc_status", nullable = false)
	private int pccStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public String getTargetBranch() {
		return targetBranch;
	}

	public void setTargetBranch(String targetBranch) {
		this.targetBranch = targetBranch;
	}

	public int getGds() {
		return gds;
	}

	public void setGds(int gds) {
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

	public int getPccStatus() {
		return pccStatus;
	}

	public void setPccStatus(int pccStatus) {
		this.pccStatus = pccStatus;
	}

	@Override
	public String toString() {
		return "QuotaPolicyEntity [id=" + id + ", agentId=" + agentId
				+ ", targetBranch=" + targetBranch + ", gds=" + gds
				+ ", limit=" + limit + ", surplus=" + surplus + ", pccStatus="
				+ pccStatus + "]";
	}

}
