package com.sunshine.service.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

public class VerifyResp implements Serializable {

    private static final long serialVersionUID = -433754637036575315L;

    private String tbCode;

    private String totalPrice;

    private String basePrice;

    private String taxes;

    private String currency;

    private String platingCarrier;

    private List<BaseAirSegment> airSegment;

    private List<PricingInfo> airPricingInfo;

    private String providerCode;

    private int tripType;

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public int getTripType() {
        return tripType;
    }

    public void setTripType(int tripType) {
        this.tripType = tripType;
    }

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
