package com.sunshine.service.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jiww on 2017/10/17.
 */
public class FlightCombination implements Serializable {

    private static final long serialVersionUID = -43354637036575315L;


    //一个航班组合为单元
    private String currency;
    private String totalPrice;
    private String basePrice;
    private String taxes;
    private String lasttickettime;//最晚出票时间
    private String platingcarrier;//出票航司 跟airpricingInfo 有关  不一样
    private String providerCode;
    private List<BaseAirSegment> airSegment;
    private List<FareInfo> farelist;
    private List<Retreat> Retreat;

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

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

    public List<Retreat> getRetreat() {
        return Retreat;
    }

    public void setRetreat(List<Retreat> retreat) {
        Retreat = retreat;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
