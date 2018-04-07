package com.travelport.ResponseMapping.baseEntity;

import java.util.List;

/**
 * Created by jiww on 2017/10/17.
 */
public class FlightCombine {
    //一个航班组合为单元
    private String currency;
    private String totalPrice;
    private String basePrice;
    private String taxes;
    private String lasttickettime;//最晚出票时间
    private String platingcarrier;//出票航司 跟airpricingInfo 有关  不一样
    private List<BaseAirSegment> airSegment;
    private List<FareInfo> farelist;
    private List<com.travelport.ResponseMapping.baseEntity.Retreat> Retreat;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getLasttickettime() {
        return lasttickettime;
    }

    public void setLasttickettime(String lasttickettime) {
        this.lasttickettime = lasttickettime;
    }

    public String getPlatingcarrier() {
        return platingcarrier;
    }

    public void setPlatingcarrier(String platingcarrier) {
        this.platingcarrier = platingcarrier;
    }

    public List<BaseAirSegment> getAirSegment() {
        return airSegment;
    }

    public void setAirSegment(List<BaseAirSegment> airSegment) {
        this.airSegment = airSegment;
    }

    public List<FareInfo> getFarelist() {
        return farelist;
    }

    public void setFarelist(List<FareInfo> farelist) {
        this.farelist = farelist;
    }

    public List<com.travelport.ResponseMapping.baseEntity.Retreat> getRetreat() {
        return Retreat;
    }

    public void setRetreat(List<com.travelport.ResponseMapping.baseEntity.Retreat> retreat) {
        Retreat = retreat;
    }
}
