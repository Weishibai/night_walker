package com.sunshine.dal.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Index;

import javax.persistence.*;

@Entity
@Table(name = "tb_infocenter")
@org.hibernate.annotations.Table(appliesTo = "tb_infocenter", indexes = { @Index(columnNames = { "airport_code" }, name = "index_airport_code")
	, @Index(columnNames = { "city_code" }, name = "index_city_code"), @Index(columnNames = { "country_code" }, name = "index_country_code")})
public class InfoCenterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "airport_code", nullable = false)
	private String airportCode;

	@Column(name = "city_code", nullable = false)
	private String cityCode;

	@Column(name = "country_code", nullable = false)
	private String countryCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
