package com.sunshine.publicserver.fliggy.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * data 信息
 */
public class FliggyDataInfo implements Serializable {

    private static final long serialVersionUID = -433754637036575315L;

    private String sessionId;

    private String tbCode;

    private List<String> fareBasis;

    private String providerCode;

    private List<String> pnrCodes;

    private String fareCacheKey;

    private Map<String, String> extMap;

    public String getFareCacheKey() {
        return fareCacheKey;
    }

    public void setFareCacheKey(String fareCacheKey) {
        this.fareCacheKey = fareCacheKey;
    }

    public List<String> getPnrCodes() {
        return pnrCodes;
    }

    public void setPnrCodes(List<String> pnrCodes) {
        this.pnrCodes = pnrCodes;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTbCode() {
        return tbCode;
    }

    public void setTbCode(String tbCode) {
        this.tbCode = tbCode;
    }

    public List<String> getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(List<String> fareBasis) {
        this.fareBasis = fareBasis;
    }

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
