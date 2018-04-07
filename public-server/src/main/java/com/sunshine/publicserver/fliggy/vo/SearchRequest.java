package com.sunshine.publicserver.fliggy.vo;

public class SearchRequest {

	private String cid;
	private int tripType;
	private String fromCity;
	private String toCity;
	private String fromDate;
	private String retDate;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public int getTripType() {
		return tripType;
	}

	public void setTripType(int tripType) {
		this.tripType = tripType;
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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getRetDate() {
		return retDate;
	}

	public void setRetDate(String retDate) {
		this.retDate = retDate;
	}

	@Override
	public String toString() {
		return "SearchRequest [cid=" + cid + ", tripType=" + tripType
				+ ", fromCity=" + fromCity + ", toCity=" + toCity
				+ ", fromDate=" + fromDate + ", retDate=" + retDate + "]";
	}

}
