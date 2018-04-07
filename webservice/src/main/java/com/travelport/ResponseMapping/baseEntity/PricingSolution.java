package com.travelport.ResponseMapping.baseEntity;

import com.travelport.ResponseMapping.baseEntity.PricingInfo;
import com.travelport.schema.air_v42_0.AirSegmentRef;

import java.util.List;

public class PricingSolution {
    private List<AirSegmentRef> airSegmentRef;
    protected List<PricingInfo> airPricingInfo;
    private String approximateTotalPrice;
    private String approximateBasePrice;
    private String approximateTaxes;

    public List<AirSegmentRef> getAirSegmentRef() {
        return airSegmentRef;
    }

    public void setAirSegmentRef(List<AirSegmentRef> airSegmentRef) {
        this.airSegmentRef = airSegmentRef;
    }

    public List<PricingInfo> getAirPricingInfo() {
        return airPricingInfo;
    }

    public void setAirPricingInfo(List<PricingInfo> airPricingInfo) {
        this.airPricingInfo = airPricingInfo;
    }

    public String getApproximateTotalPrice() {
        return approximateTotalPrice;
    }

    public void setApproximateTotalPrice(String approximateTotalPrice) {
        this.approximateTotalPrice = approximateTotalPrice;
    }

    public String getApproximateBasePrice() {
        return approximateBasePrice;
    }

    public void setApproximateBasePrice(String approximateBasePrice) {
        this.approximateBasePrice = approximateBasePrice;
    }

    public String getApproximateTaxes() {
        return approximateTaxes;
    }

    public void setApproximateTaxes(String approximateTaxes) {
        this.approximateTaxes = approximateTaxes;
    }
}
