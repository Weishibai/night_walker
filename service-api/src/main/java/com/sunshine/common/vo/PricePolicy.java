package com.sunshine.common.vo;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.sunshine.dal.entity.PricePolicyEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PricePolicy {

	private static final Joiner JOINER = Joiner.on(",").skipNulls();

	private static final Splitter SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();

	private long id;
	private TripType tripType;
	private GDS gds;
	private Platform platform;
	private String carrier;
	private String fromCity;
	private String toCity;
	private BigDecimal rate;
	private BigDecimal expense;
	private long agentId;
	private int advanceSaleDays;
	private BigDecimal subsidies;
	private Set<String> spaces = new HashSet<>();

	public PricePolicy() {
	}

	public PricePolicy(long id, TripType tripType, GDS gds, Platform platform,
			String carrier, String fromCity, String toCity, BigDecimal rate,
			BigDecimal expense, int advanceSaleDays, BigDecimal subsidies) {
		super();
		this.id = id;
		this.tripType = tripType;
		this.gds = gds;
		this.platform = platform;
		this.carrier = carrier;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.rate = rate;
		this.expense = expense;
		this.advanceSaleDays = advanceSaleDays;
		this.subsidies = subsidies;
	}

	public static PricePolicy convertFromEntity(PricePolicyEntity entity) {
		PricePolicy pricePolicy = new PricePolicy();
		pricePolicy.setId(entity.getId());
		pricePolicy.setCarrier(entity.getCarrier());
		pricePolicy.setExpense(null == entity.getExpense() ? BigDecimal.ZERO : entity.getExpense());
		pricePolicy.setFromCity(entity.getFromCity());
		pricePolicy.setGds(GDS.get(entity.getGds()));
		pricePolicy.setPlatform(Platform.get(entity.getPlatform()));
		pricePolicy.setRate(null == entity.getRate() ? BigDecimal.ZERO : entity.getRate());
		pricePolicy.setToCity(entity.getToCity());
		pricePolicy.setTripType(TripType.get(entity.getTripType()));
		pricePolicy.setSpaces(Sets.newHashSet(SPLITTER.split(entity.getSpaces())));
		pricePolicy.setAdvanceSaleDays(entity.getAdvanceSaleDays());
		pricePolicy.setSubsidies(entity.getSubsidies());
		return pricePolicy;
	}

	public static PricePolicyEntity convertToEntity(PricePolicy pricePolicy) {
		PricePolicyEntity entity = new PricePolicyEntity();
		entity.setId(pricePolicy.getId());
		entity.setAgentId(pricePolicy.getAgentId());
		entity.setCarrier(pricePolicy.getCarrier());
		entity.setExpense(null == pricePolicy.getExpense() ? BigDecimal.ZERO : pricePolicy.getExpense());
		entity.setRate(null == pricePolicy.getRate() ? BigDecimal.ZERO : pricePolicy.getRate());
		entity.setFromCity(pricePolicy.getFromCity());
		entity.setGds(pricePolicy.getGds().getCode());
		entity.setId(pricePolicy.getId());
		entity.setPlatform(pricePolicy.getPlatform().getCode());
		entity.setToCity(pricePolicy.getToCity());
		entity.setTripType(pricePolicy.getTripType().getCode());
		entity.setSpaces(JOINER.join(pricePolicy.getSpaces()));
		entity.setAdvanceSaleDays(pricePolicy.getAdvanceSaleDays());
		entity.setSubsidies(pricePolicy.getSubsidies());
		return entity;
	}

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

	public TripType getTripType() {
		return tripType;
	}

	public void setTripType(TripType tripType) {
		this.tripType = tripType;
	}

	public GDS getGds() {
		return gds;
	}

	public void setGds(GDS gds) {
		this.gds = gds;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
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

	public Set<String> getSpaces() {
		return spaces;
	}

	public void setSpaces(Set<String> spaces) {
		this.spaces = spaces;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}

		if (!(obj instanceof PricePolicy)) {
			return false;
		}

		PricePolicy pricePolicy = (PricePolicy) obj;
		return pricePolicy.getId() == getId()
				&& StringUtils.equalsIgnoreCase(pricePolicy.getFromCity(), getFromCity())
				&& StringUtils.equalsIgnoreCase(pricePolicy.getToCity(), getToCity())
				&& StringUtils.equalsIgnoreCase(pricePolicy.getCarrier(), getCarrier())
				&& pricePolicy.getGds() == getGds();
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		return (int) (hashCode + getId() * 31);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
