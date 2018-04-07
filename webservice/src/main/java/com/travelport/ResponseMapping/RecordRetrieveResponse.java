package com.travelport.ResponseMapping;

import com.travelport.ResponseMapping.baseEntity.*;

import java.util.List;

public class RecordRetrieveResponse {
    private String pnrCode1;
    private String pnrCode2;
    private String pnrCode3;
    private String supplierCode;
    private int totalPrice;
    private int basePrice;
    private int taxes;
    private String currency;
    private List<BaseAirSegment> airSegment;
    private List<PricingInfo> airPricingInfo;
    private List<Traveler>bookingTraveler;

    public String getPnrCode1() {
        return pnrCode1;
    }

    public void setPnrCode1(String pnrCode1) {
        this.pnrCode1 = pnrCode1;
    }

    public String getPnrCode2() {
        return pnrCode2;
    }

    public void setPnrCode2(String pnrCode2) {
        this.pnrCode2 = pnrCode2;
    }

    public String getPnrCode3() {
        return pnrCode3;
    }

    public void setPnrCode3(String pnrCode3) {
        this.pnrCode3 = pnrCode3;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public List<BaseAirSegment> getAirSegment() {
        return airSegment;
    }

    public void setAirSegment(List<BaseAirSegment> airSegment) {
        this.airSegment = airSegment;
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

    public List<PricingInfo> getAirPricingInfo() {
        return airPricingInfo;
    }

    public void setAirPricingInfo(List<PricingInfo> airPricingInfo) {
        this.airPricingInfo = airPricingInfo;
    }

    public List<Traveler> getBookingTraveler() {
        return bookingTraveler;
    }

    public void setBookingTraveler(List<Traveler> bookingTraveler) {
        this.bookingTraveler = bookingTraveler;
    }
}
