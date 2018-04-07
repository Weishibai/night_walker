package com.sunshine.publicserver.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MarkupRequest {
    private TripType tripType; // 单程还是往返
    private String airline; // 航空公司
    private String departureDateTime;// 起飞时间
    private String departureAirport;// 出发地（机场代码）
    private String[] destinations;// 目的地（机场代码）
    private String[] bookingClasses;// 所有航段不重复的舱位，例如Q,Z
    private String[] fareBasises;// 所有航段不重复的票价基础
    private String[] flightNumbers;// 所有航段不重复的航班号，如CZ338,CZ3061
    private String[] marketAirlines;// 所有航段不重复的航空公司
    private String[] operatorAirlines;// 所有航段的共享航空公司

    public enum TripType {
        Oneway("Oneway"), Roundtrip("Roundtrip");
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        TripType(String name) {
            this.name = name;
        }
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
