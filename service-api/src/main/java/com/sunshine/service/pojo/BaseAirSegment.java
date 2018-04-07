package com.sunshine.service.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by jiww on 2017/10/13.
 */
public class BaseAirSegment implements Serializable {

    private static final long serialVersionUID = 43354637036575315L;

    private int group;
    private String carrier;
    private String cabinClass;
    private String bookingCounts;//airprice没有
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String operatingCarrier;
    private String operatingFlightNumber;
    private String originTerminal;
    private String destinationTerminal;
    private String stopCity;//经停低
    private String bookingCode;
    private BigInteger  flightTime;
    private BigInteger distance;
    private String equipment;
    private boolean status;

    private String statusCode;

    public BaseAirSegment() {
    }

    public BaseAirSegment(int group, String carrier, String cabinClass, String bookingCounts, String flightNumber, String origin, String destination, String departureTime, String arrivalTime, String operatingCarrier, String operatingFlightNumber, String stopCity, String bookingCode, BigInteger flightTime, BigInteger distance, String equipment, String originTerminal, String destinationTerminal) {
        this.group = group;
        this.carrier = carrier;
        this.cabinClass = cabinClass;
        this.bookingCounts = bookingCounts;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.operatingCarrier = operatingCarrier;
        this.operatingFlightNumber = operatingFlightNumber;
        this.stopCity = stopCity;
        this.bookingCode = bookingCode;
        this.flightTime = flightTime;
        this.distance = distance;
        this.equipment = equipment;
        this.originTerminal=originTerminal;
        this.destinationTerminal=destinationTerminal;
    }

    public boolean isStatus() {
        return status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getBookingCounts() {
        return bookingCounts;
    }

    public void setBookingCounts(String bookingCounts) {
        this.bookingCounts = bookingCounts;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public String getOperatingFlightNumber() {
        return operatingFlightNumber;
    }

    public void setOperatingFlightNumber(String operatingFlightNumber) {
        this.operatingFlightNumber = operatingFlightNumber;
    }

    public String getOriginTerminal() {
        return originTerminal;
    }

    public void setOriginTerminal(String originTerminal) {
        this.originTerminal = originTerminal;
    }

    public String getDestinationTerminal() {
        return destinationTerminal;
    }

    public void setDestinationTerminal(String destinationTerminal) {
        this.destinationTerminal = destinationTerminal;
    }

    public String getStopCity() {
        return stopCity;
    }

    public void setStopCity(String stopCity) {
        this.stopCity = stopCity;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public BigInteger getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(BigInteger flightTime) {
        this.flightTime = flightTime;
    }

    public BigInteger getDistance() {
        return distance;
    }

    public void setDistance(BigInteger distance) {
        this.distance = distance;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
