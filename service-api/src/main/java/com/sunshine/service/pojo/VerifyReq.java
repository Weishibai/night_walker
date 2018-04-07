package com.sunshine.service.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

public class VerifyReq implements Serializable {

    private static final long serialVersionUID = -433754637036575315L;

    private int flightType;

    private AirSegment airSegment;

    private List<String> fareBasis;

    private String targetBranch;

    private String pcc;

    private int adultNum;

    private int childNum;

    private String providerCode;

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getPcc() {
        return pcc;
    }

    public void setPcc(String pcc) {
        this.pcc = pcc;
    }

    public String getTargetBranch() {
        return targetBranch;
    }

    public void setTargetBranch(String targetBranch) {
        this.targetBranch = targetBranch;
    }

    public int getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(int adultNum) {
        this.adultNum = adultNum;
    }

    public int getChildNum() {
        return childNum;
    }

    public void setChildNum(int childNum) {
        this.childNum = childNum;
    }

    public int getFlightType() {
        return flightType;
    }

    public void setFlightType(int flightType) {
        this.flightType = flightType;
    }

    public AirSegment getAirSegment() {
        return airSegment;
    }

    public void setAirSegment(AirSegment airSegment) {
        this.airSegment = airSegment;
    }

    public List<String> getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(List<String> fareBasis) {
        this.fareBasis = fareBasis;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
