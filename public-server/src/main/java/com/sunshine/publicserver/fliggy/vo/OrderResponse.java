package com.sunshine.publicserver.fliggy.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderResponse {

	private int status;
	private String msg;
	private String sessionId;
	private String orderNo;
	private String pnrCode;
	private int maxSeats;
	private String bookingGds;
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

	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

	public String getBookingGds() {
		return bookingGds;
	}

	public void setBookingGds(String bookingGds) {
		this.bookingGds = bookingGds;
	}

	public RoutingElement getRouting() {
		return routing;
	}

	public void setRouting(RoutingElement routing) {
		this.routing = routing;
	}

	public OrderResponse(){}

	public OrderResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static OrderResponse make(int status, String msg) {
		return new OrderResponse(status, msg);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
