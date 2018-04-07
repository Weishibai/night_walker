package com.sunshine.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "tb_whitelist_policy")
@org.hibernate.annotations.Table(appliesTo = "tb_whitelist_policy", indexes = { @Index(columnNames = { "agent_id" }, name = "index_agent_id") })
public class WhitelistPolicyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "from_state", nullable = false)
	private String fromState;

	@Column(name = "to_state", nullable = false)
	private String toState;

	@Column(name = "gds", nullable = false)
	private int gds;

	@Column(name = "ex_pair", nullable = true)
	private String exPair;

	@Column(name = "target_branch", nullable = false)
	private String targetBranch;

	@Column(name = "agent_id", nullable = false)
	private long agentId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFromState() {
		return fromState;
	}

	public void setFromState(String fromState) {
		this.fromState = fromState;
	}

	public String getToState() {
		return toState;
	}

	public void setToState(String toState) {
		this.toState = toState;
	}

	public int getGds() {
		return gds;
	}

	public void setGds(int gds) {
		this.gds = gds;
	}

	public String getExPair() {
		return exPair;
	}

	public void setExPair(String exPair) {
		this.exPair = exPair;
	}

	public String getTargetBranch() {
		return targetBranch;
	}

	public void setTargetBranch(String targetBranch) {
		this.targetBranch = targetBranch;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

}
