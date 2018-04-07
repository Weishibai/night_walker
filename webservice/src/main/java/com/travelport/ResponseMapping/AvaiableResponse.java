package com.travelport.ResponseMapping;

import com.travelport.ResponseMapping.baseEntity.BaseAirSegment;
import com.travelport.ResponseMapping.baseEntity.PricingInfo;
import com.travelport.ResponseMapping.baseEntity.SegmentList;
import com.travelport.schema.air_v42_0.AirPricingInfo;
import com.travelport.schema.air_v42_0.AirSegmentList;

import java.util.List;

/**
 * Created by jiww on 2017/10/13.
 */
public class AvaiableResponse {
    private String tbCode;
    private List<BaseAirSegment> airSegment;
    private String totalPrice;
    private String basePrice;
    private String taxes;
    private String currency;
    private String platingCarrier;
    private List<PricingInfo> airPricingInfo;

    public String getTbCode() {
        return tbCode;
    }

    public void setTbCode(String tbCode) {
        this.tbCode = tbCode;
    }

    public List<BaseAirSegment> getAirSegment() {
        return airSegment;
    }

    public void setAirSegment(List<BaseAirSegment> airSegment) {
        this.airSegment = airSegment;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPlatingCarrier() {
        return platingCarrier;
    }

    public void setPlatingCarrier(String platingCarrier) {
        this.platingCarrier = platingCarrier;
    }

    public List<PricingInfo> getAirPricingInfo() {
        return airPricingInfo;
    }

    public void setAirPricingInfo(List<PricingInfo> airPricingInfo) {
        this.airPricingInfo = airPricingInfo;
    }
}
