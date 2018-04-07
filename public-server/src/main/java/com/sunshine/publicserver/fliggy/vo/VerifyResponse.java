package com.sunshine.publicserver.fliggy.vo;

public class VerifyResponse {

	private int status;
	private String msg;
	private String sessionId;
	private int maxSeats;
	private RoutingElement routing;
	private RuleElement rule;

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

	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

	public RoutingElement getRouting() {
		return routing;
	}

	public void setRouting(RoutingElement routing) {
		this.routing = routing;
	}

	public RuleElement getRule() {
		return rule;
	}

	public void setRule(RuleElement rule) {
		this.rule = rule;
	}

	public VerifyResponse(){}

	public VerifyResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static VerifyResponse make(int status, String msg) {
		return new VerifyResponse(status, msg);
	}

	@Override
	public String toString() {
		return "VerifyResponse [status=" + status + ", msg=" + msg
				+ ", sessionId=" + sessionId + ", maxSeats=" + maxSeats
				+ ", routing=" + routing + ", rule=" + rule + "]";
	}

}
