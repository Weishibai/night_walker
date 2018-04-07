package com.sunshine.publicserver.fliggy.vo;

public class PayRequest {

	private String cid;
	private String tripType;
	private String sessionId;
	private String orderNo;
	private String pnrCode;
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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPnrCode() {
		return pnrCode;
	}

	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}

	public RoutingElement getRouting() {
		return routing;
	}

	public void setRouting(RoutingElement routing) {
		this.routing = routing;
	}

	@Override
	public String toString() {
		return "PayRequest [cid=" + cid + ", tripType=" + tripType
				+ ", sessionId=" + sessionId + ", orderNo=" + orderNo
				+ ", pnrCode=" + pnrCode + ", routing=" + routing + "]";
	}

}
