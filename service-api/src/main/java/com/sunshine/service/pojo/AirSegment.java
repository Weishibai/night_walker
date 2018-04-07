package com.sunshine.service.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

public class AirSegment implements Serializable {

    private static final long serialVersionUID = 4331137036575315L;

    private List<BaseAirSegment> airSegment;

    public List<BaseAirSegment> getAirSegment() {
        return airSegment;
    }

    public void setAirSegment(List<BaseAirSegment> airSegment) {
        this.airSegment = airSegment;
    }

    public AirSegment() {}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
