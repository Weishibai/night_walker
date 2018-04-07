package com.sunshine.publicserver.fliggy.vo;

public class SegmentElement {

	private String carrier;
	private String flightNumber;
	private String depAirport;
	private String depTime;
	private String arrAirport;
	private String arrTime;
	private String stopCities;
	private boolean codeShare;
	private String operatingCarrier;
	private String operatingFlightNo;
	private String departureTerminal;
	private String arrivingTerminal;
	private String cabin;
	private String aircraftCode;
	private String seatCount;
	private String cabinClass;
	private String timeZone;

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepAirport() {
		return depAirport;
	}

	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getStopCities() {
		return stopCities;
	}

	public void setStopCities(String stopCities) {
		this.stopCities = stopCities;
	}

	public boolean isCodeShare() {
		return codeShare;
	}

	public void setCodeShare(boolean codeShare) {
		this.codeShare = codeShare;
	}

	public String getOperatingCarrier() {
		return operatingCarrier;
	}

	public void setOperatingCarrier(String operatingCarrier) {
		this.operatingCarrier = operatingCarrier;
	}

	public String getOperatingFlightNo() {
		return operatingFlightNo;
	}

	public void setOperatingFlightNo(String operatingFlightNo) {
		this.operatingFlightNo = operatingFlightNo;
	}

	public String getDepartureTerminal() {
		return departureTerminal;
	}

	public void setDepartureTerminal(String departureTerminal) {
		this.departureTerminal = departureTerminal;
	}

	public String getArrivingTerminal() {
		return arrivingTerminal;
	}

	public void setArrivingTerminal(String arrivingTerminal) {
		this.arrivingTerminal = arrivingTerminal;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getAircraftCode() {
		return aircraftCode;
	}

	public void setAircraftCode(String aircraftCode) {
		this.aircraftCode = aircraftCode;
	}

	public String getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(String seatCount) {
		this.seatCount = seatCount;
	}

	public String getCabinClass() {
		return cabinClass;
	}

	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}

	@Override
	public String toString() {
		return "SegmentElement [carrier=" + carrier + ", flightNumber="
				+ flightNumber + ", depAirport=" + depAirport + ", depTime="
				+ depTime + ", arrAirport=" + arrAirport + ", arrTime="
				+ arrTime + ", stopCities=" + stopCities + ", codeShare="
				+ codeShare + ", operatingCarrier=" + operatingCarrier
				+ ", operatingFlightNo=" + operatingFlightNo
				+ ", departureTerminal=" + departureTerminal
				+ ", arrivingTerminal=" + arrivingTerminal + ", cabin=" + cabin
				+ ", aircraftCode=" + aircraftCode + ", seatCount=" + seatCount
				+ ", cabinClass=" + cabinClass + "]";
	}

}
