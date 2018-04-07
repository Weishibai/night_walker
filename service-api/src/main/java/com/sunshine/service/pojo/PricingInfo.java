package com.sunshine.service.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class PricingInfo implements Serializable {

    private static final long serialVersionUID = -433754637036575315L;

    private String branchTotalPrice;
    private String branchBasePrice;
    private String branchTaxes;//上面减去
    private String latestTicketingTime;
    private String platingCarrier;
    private String fareBasisCode;
    private String code;//乘机人类型
    private int count;

    public String getBranchTotalPrice() {
        return branchTotalPrice;
    }

    public void setBranchTotalPrice(String branchTotalPrice) {
        this.branchTotalPrice = branchTotalPrice;
    }

    public String getBranchBasePrice() {
        return branchBasePrice;
    }

    public void setBranchBasePrice(String branchBasePrice) {
        this.branchBasePrice = branchBasePrice;
    }

    public String getBranchTaxes() {
        return branchTaxes;
    }

    public void setBranchTaxes(String branchTaxes) {
        this.branchTaxes = branchTaxes;
    }

    public String getLatestTicketingTime() {
        return latestTicketingTime;
    }

    public void setLatestTicketingTime(String latestTicketingTime) {
        this.latestTicketingTime = latestTicketingTime;
    }

    public String getPlatingCarrier() {
        return platingCarrier;
    }

    public void setPlatingCarrier(String platingCarrier) {
        this.platingCarrier = platingCarrier;
    }

    public String getFareBasisCode() {
        return fareBasisCode;
    }

    public void setFareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
