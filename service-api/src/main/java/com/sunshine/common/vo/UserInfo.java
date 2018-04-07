package com.sunshine.common.vo;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sunshine.dal.entity.UserEntity;

public class UserInfo {

	private long id;

	private String uname;
	@JsonIgnore
	private String pwd;

	private long agentId;

	@JsonIgnore
	private String salt;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public static UserInfo convertFromEntity(UserEntity entity) {
		UserInfo userInfo = new UserInfo();
		// userInfo.setPwd(entity.getPwd());
		userInfo.setAgentId(entity.getAgentId());
		userInfo.setId(entity.getId());
		userInfo.setSalt(entity.getSalt());
		userInfo.setUname(entity.getUname());
		return userInfo;
	}

	public static UserEntity convertToEntity(UserInfo userInfo) {
		UserEntity entity = new UserEntity();
		return entity;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", uname=" + uname + ", pwd=" + pwd
				+ ", agentId=" + agentId + ", salt=" + salt + "]";
	}

}
