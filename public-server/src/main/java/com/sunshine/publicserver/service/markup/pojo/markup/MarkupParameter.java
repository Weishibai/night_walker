package com.sunshine.publicserver.service.markup.pojo.markup;

public class MarkupParameter {
	private Integer id;
	private String fareType;
	private String tripType;
	private String airline;
	private String departureDateTime;
	private String departureAirport;
	private String[] destinations;
	private String[] bookingClasses;
	private String[] fareBasises;
	private String[] flightNumbers;
	private String[] marketAirlines;
	private String[] operatorAirlines;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String[] getDestinations() {
		return destinations;
	}

	public void setDestinations(String[] destinations) {
		this.destinations = destinations;
	}

	public String[] getBookingClasses() {
		return bookingClasses;
	}

	public void setBookingClasses(String[] bookingClasses) {
		this.bookingClasses = bookingClasses;
	}

	public String[] getFareBasises() {
		return fareBasises;
	}

	public void setFareBasises(String[] fareBasises) {
		this.fareBasises = fareBasises;
	}

	public String[] getFlightNumbers() {
		return flightNumbers;
	}

	public void setFlightNumbers(String[] flightNumbers) {
		this.flightNumbers = flightNumbers;
	}

	public String[] getMarketAirlines() {
		return marketAirlines;
	}

	public void setMarketAirlines(String[] marketAirlines) {
		this.marketAirlines = marketAirlines;
	}

	public String[] getOperatorAirlines() {
		return operatorAirlines;
	}

	public void setOperatorAirlines(String[] operatorAirlines) {
		this.operatorAirlines = operatorAirlines;
	}
}