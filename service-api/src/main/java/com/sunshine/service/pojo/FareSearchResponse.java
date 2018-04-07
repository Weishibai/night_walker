package com.sunshine.service.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

public class FareSearchResponse implements Serializable {

    private static final long serialVersionUID = -43354637036575315L;

    /**
     * fake target branch
     */
    private String tbCode;

    private List<FlightCombination> flightCombinations;

    private int flyType;

    public int getFlyType() {
        return flyType;
    }

    public void setFlyType(int flyType) {
        this.flyType = flyType;
    }

    public String getTbCode() {
        return tbCode;
    }

    public void setTbCode(String tbCode) {
        this.tbCode = tbCode;
    }

    public List<FlightCombination> getFlightCombinations() {
        return flightCombinations;
    }

    public void setFlightCombinations(List<FlightCombination> flightCombinations) {
        this.flightCombinations = flightCombinations;
    }

    public FareSearchResponse(){}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
