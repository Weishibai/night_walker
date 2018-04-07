package com.sunshine.publicserver.fliggy.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class OrderRequest {

	private String cid;
	private String tripType;
	private String sessionId;
	private RoutingElement routing;
	private List<PassengerElement> passengers;
	private ContactElement contact;

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

	public RoutingElement getRouting() {
		return routing;
	}

	public void setRouting(RoutingElement routing) {
		this.routing = routing;
	}

	public List<PassengerElement> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerElement> passengers) {
		this.passengers = passengers;
	}

	public ContactElement getContact() {
		return contact;
	}

	public void setContact(ContactElement contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
