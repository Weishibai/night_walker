package com.travelport.ResponseMapping;

import com.sunshine.service.pojo.FlightCombination;
import com.travelport.ResponseMapping.baseEntity.FlightCombine;

import java.util.List;

/**
 * Created by jiww on 2017/10/17.
 */
public class LowFareResponse {

    private String TBCode;

    private List<FlightCombine> FlightCombine;

    private List<FlightCombination> flightCombinations;

    public LowFareResponse(String TBCode, List<com.travelport.ResponseMapping.baseEntity.FlightCombine> flightCombine) {
        this.TBCode = TBCode;
        FlightCombine = flightCombine;
    }

    public LowFareResponse() {

    }

    public List<FlightCombination> getFlightCombinations() {
        return flightCombinations;
    }

    public void setFlightCombinations(List<FlightCombination> flightCombinations) {
        this.flightCombinations = flightCombinations;
    }

    public String getTBCode() {
        return TBCode;
    }

    public void setTBCode(String TBCode) {
        this.TBCode = TBCode;
    }

    public List<com.travelport.ResponseMapping.baseEntity.FlightCombine> getFlightCombine() {
        return FlightCombine;
    }

    public void setFlightCombine(List<com.travelport.ResponseMapping.baseEntity.FlightCombine> flightCombine) {
        FlightCombine = flightCombine;
    }
}
