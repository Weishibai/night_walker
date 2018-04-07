package com.sunshine.dal.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "tb_price_policy")
@org.hibernate.annotations.Table(appliesTo = "tb_price_policy", indexes = { @Index(columnNames = {
		"agent_id", "status" }, name = "index_agent_id_status") })
public class PricePolicyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "agent_id", nullable = false)
	private long agentId;

	@Column(name = "trip_type", nullable = false)
	private int tripType;

	@Column(name = "gds", nullable = false)
	private int gds;

	@Column(name = "platform", nullable = false)
	private int platform;

	@Column(name = "carrier", nullable = false)
	private String carrier;

	@Column(name = "from_city", nullable = false)
	private String fromCity;

	@Column(name = "to_city", nullable = false)
	private String toCity;

	@Column(name = "rate", columnDefinition = "decimal(8, 3) NOT NULL")
	private BigDecimal rate;

	@Column(name = "expense", columnDefinition = "decimal(8, 3) NOT NULL")
	private BigDecimal expense;

	@Column(name = "status", nullable = false)
	private int status = 1;

	@Column(name = "spaces", nullable = false)
	private String spaces;

	@Column(name = "advance_sale_day", nullable = false)
	private int advanceSaleDays;

	@Column(name = "subsidies", columnDefinition = "decimal(8, 3) NOT NULL")
	private BigDecimal subsidies;

	public int getAdvanceSaleDays() {
		return advanceSaleDays;
	}

	public void setAdvanceSaleDays(int advanceSaleDays) {
		this.advanceSaleDays = advanceSaleDays;
	}

	public BigDecimal getSubsidies() {
		return subsidies;
	}

	public void setSubsidies(BigDecimal subsidies) {
		this.subsidies = subsidies;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTripType() {
		return tripType;
	}

	public void setTripType(int tripType) {
		this.tripType = tripType;
	}

	public int getGds() {
		return gds;
	}

	public void setGds(int gds) {
		this.gds = gds;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getExpense() {
		return expense;
	}

	public void setExpense(BigDecimal expense) {
		this.expense = expense;
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSpaces() {
		return spaces;
	}

	public void setSpaces(String spaces) {
		this.spaces = spaces;
	}
}
