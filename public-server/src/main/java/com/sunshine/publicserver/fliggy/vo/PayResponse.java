package com.sunshine.publicserver.fliggy.vo;

public class PayResponse {

	private int status;
	private String msg;
	private String sessionId;
	private String orderNo;
	private String pnrCode;
	private String ticketingGds;
	private RoutingElement routing;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public String getTicketingGds() {
		return ticketingGds;
	}

	public void setTicketingGds(String ticketingGds) {
		this.ticketingGds = ticketingGds;
	}

	public RoutingElement getRouting() {
		return routing;
	}

	public void setRouting(RoutingElement routing) {
		this.routing = routing;
	}

	public PayResponse(){}

	public PayResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static PayResponse make(int status, String msg) {
		return new PayResponse(status, msg);
	}

	@Override
	public String toString() {
		return "PayResponse [status=" + status + ", msg=" + msg
				+ ", sessionId=" + sessionId + ", orderNo=" + orderNo
				+ ", pnrCode=" + pnrCode + ", ticketingGds=" + ticketingGds
				+ ", routing=" + routing + "]";
	}

}
