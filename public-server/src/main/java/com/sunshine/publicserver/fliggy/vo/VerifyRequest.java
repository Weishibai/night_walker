package com.sunshine.publicserver.fliggy.vo;

public class VerifyRequest {

	private String cid;
	private String tripType;
	private RoutingElement routing;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public RoutingElement getRouting() {
		return routing;
	}

	public void setRouting(RoutingElement routing) {
		this.routing = routing;
	}

	@Override
	public String toString() {
		return "VerifyRequest [cid=" + cid + ", tripType=" + tripType
				+ ", routing=" + routing + "]";
	}

}
