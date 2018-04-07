package com.sunshine.publicserver.service.markup.pojo.epibolyMarkup;

public class RequestMarkup {
    private String airline;// 出票航司
    private String tripType;// 航程类型
    private String departureDateTime;// 出发时间
    private String departureAirport;// 出发机场
    private String[] destinations;// 到达机场
    private String[] bookingClasses;// 舱位(不重复)
    private String[] fareBasises;// (不重复)
    private String[] flightNumbers;// 航班号
    private String[] marketAirlines;// 执飞航司
    private String[] operatorAirlines;// 共享航司

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
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
