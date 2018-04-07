package com.sunshine.dal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "tb_user")
@org.hibernate.annotations.Table(appliesTo = "tb_user", indexes = { @Index(columnNames = { "uname" }, name = "index_agent_id") })
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "uname", nullable = false)
	private String uname;

	@Column(name = "pwd", nullable = false)
	private String pwd;

	@Column(name = "agentId", nullable = false)
	private long agentId;

	@Column(name = "salt", nullable = false)
	private String salt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", uname=" + uname + ", pwd=" + pwd
				+ ", agentId=" + agentId + ", salt=" + salt + "]";
	}

}
