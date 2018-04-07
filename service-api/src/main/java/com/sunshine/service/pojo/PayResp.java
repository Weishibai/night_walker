package com.sunshine.service.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

public class PayResp implements Serializable {

    private static final long serialVersionUID = -40915469306575315L;

    private List<String> pnrCodes;

    private List<BaseAirSegment> airSegment;

    private List<PricingInfo> airPricingInfo;

    private String supplierCode;

    private int totalPrice;

    private int basePrice;

    private int taxes;

    private String currency;

    private List<ApiPassenger> apiPassengers;

    private String platingCarrier;

    public String getPlatingCarrier() {
        return platingCarrier;
    }

    public void setPlatingCarrier(String platingCarrier) {
        this.platingCarrier = platingCarrier;
    }

    public List<String> getPnrCodes() {
        return pnrCodes;
    }

    public void setPnrCodes(List<String> pnrCodes) {
        this.pnrCodes = pnrCodes;
    }

    public List<BaseAirSegment> getAirSegment() {
        return airSegment;
    }

    public void setAirSegment(List<BaseAirSegment> airSegment) {
        this.airSegment = airSegment;
    }

    public List<PricingInfo> getAirPricingInfo() {
        return airPricingInfo;
    }

    public void setAirPricingInfo(List<PricingInfo> airPricingInfo) {
        this.airPricingInfo = airPricingInfo;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getTaxes() {
        return taxes;
    }

    public void setTaxes(int taxes) {
        this.taxes = taxes;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<ApiPassenger> getApiPassengers() {
        return apiPassengers;
    }

    public void setApiPassengers(List<ApiPassenger> apiPassengers) {
        this.apiPassengers = apiPassengers;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
