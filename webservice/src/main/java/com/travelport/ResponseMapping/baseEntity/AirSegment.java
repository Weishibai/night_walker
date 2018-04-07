package com.travelport.ResponseMapping.baseEntity;

import com.travelport.ResponseMapping.baseEntity.BaseAirSegment;

import java.util.List;

public class AirSegment {
    private List<BaseAirSegment> airSegment;

    public List<BaseAirSegment> getAirSegment() {
        return airSegment;
    }

    public void setAirSegment(List<BaseAirSegment> airSegment) {
        this.airSegment = airSegment;
    }
}
