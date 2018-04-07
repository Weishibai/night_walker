package com.travelport.ResponseMapping.baseEntity;

import java.util.List;

/**
 * Created by jiww on 2017/10/18.
 */
public class AirSegmentCombine {
    protected List<BaseAirSegment> airSegment;

    public List<BaseAirSegment> getAirSegment() {
        return airSegment;
    }

    public void setAirSegment(List<BaseAirSegment> airSegment) {
        this.airSegment = airSegment;
    }
}
