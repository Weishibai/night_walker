package com.travelport.ResponseMapping.baseEntity;


import java.util.List;

/**
 * Created by jiww on 2017/10/13.
 */
public class SegmentList {
    protected List<AirSegmentCombine> SegmentCombine;

    public List<AirSegmentCombine> getSegmentCombine() {
        return SegmentCombine;
    }

    public void setSegmentCombine(List<AirSegmentCombine> segmentCombine) {
        SegmentCombine = segmentCombine;
    }
}
