package com.sunshine.service.pojo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class PayReq implements Serializable {

    private static final long serialVersionUID = -409154637036575315L;

    private String targetBranch;

    private String pnrCode;

    private String pcc;

    private String providerCode;

    public String getPcc() {
        return pcc;
    }

    public void setPcc(String pcc) {
        this.pcc = pcc;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getTargetBranch() {
        return targetBranch;
    }

    public void setTargetBranch(String targetBranch) {
        this.targetBranch = targetBranch;
    }

    public String getPnrCode() {
        return pnrCode;
    }

    public void setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
    }

    public static PayReq make(String pcc, String providerCode, String pnrCode) {
        PayReq req = new PayReq();
        req.setProviderCode(providerCode);
        req.setPcc(pcc);
        req.setPnrCode(pnrCode);
        return req;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
