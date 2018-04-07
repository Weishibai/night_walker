package com.sunshine.common.vo;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.sunshine.dal.entity.WhitelistPolicyEntity;

public class WhitelistPolicy {

	private long id;
	private String fromState;
	private String toState;
	private GDS gds;
	private Set<String> exPair;
	private String targetBranch;
	private long agentId;

	public WhitelistPolicy() {
	}

	public WhitelistPolicy(long id, String fromState, String toState, GDS gds,
			Set<String> exPair, String targetBranch) {
		super();
		this.id = id;
		this.fromState = fromState;
		this.toState = toState;
		this.gds = gds;
		this.exPair = exPair;
		this.targetBranch = targetBranch;
	}

	public static WhitelistPolicy convertFromEntity(WhitelistPolicyEntity entity) {
		WhitelistPolicy whitelistPolicy = new WhitelistPolicy();
		Set<String> exPair = null;
		if (StringUtils.isNotBlank(entity.getExPair())) {
			exPair = new HashSet<String>(Splitter.on(",").splitToList(
					entity.getExPair()));
		} else {
			exPair = new HashSet<String>();
		}
		whitelistPolicy.setExPair(exPair);
		whitelistPolicy.setFromState(entity.getFromState());
		whitelistPolicy.setGds(GDS.get(entity.getGds()));
		whitelistPolicy.setId(entity.getId());
		whitelistPolicy.setTargetBranch(entity.getTargetBranch());
		whitelistPolicy.setToState(entity.getToState());
		return whitelistPolicy;
	}

	public static WhitelistPolicyEntity convertToEntity(WhitelistPolicy whitelistPolicy) {
		WhitelistPolicyEntity entity = new WhitelistPolicyEntity();
		entity.setAgentId(whitelistPolicy.getAgentId());
		if (whitelistPolicy.getExPair() != null) {
			entity.setExPair(Joiner.on(",").join(whitelistPolicy.getExPair()));
		}
		entity.setFromState(whitelistPolicy.getFromState());
		entity.setGds(whitelistPolicy.getGds().getCode());
		entity.setId(whitelistPolicy.getId());
		entity.setTargetBranch(whitelistPolicy.getTargetBranch());
		entity.setToState(whitelistPolicy.getToState());
		return entity;
	}

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

	public GDS getGds() {
		return gds;
	}

	public void setGds(GDS gds) {
		this.gds = gds;
	}

	public Set<String> getExPair() {
		return exPair;
	}

	public void setExPair(Set<String> exPair) {
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

	@Override
	public String toString() {
		return "WhitelistPolicy [id=" + id + ", fromState=" + fromState
				+ ", toState=" + toState + ", gds=" + gds + ", exPair="
				+ exPair + ", targetBranch=" + targetBranch + ", agentId="
				+ agentId + "]";
	}

}
