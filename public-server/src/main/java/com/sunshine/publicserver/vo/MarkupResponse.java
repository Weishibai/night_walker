package com.sunshine.publicserver.vo;

import com.sunshine.common.util.JsonUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class MarkupResponse {

    private String errorMsg;

    private List<List<MarkupInfo>> markupinfos;

    private String success;

    public List<List<MarkupInfo>> getMarkupinfos() {
        return markupinfos;
    }

    public void setMarkupinfos(List<List<MarkupInfo>> markupinfos) {
        this.markupinfos = markupinfos;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static void main(String[] args) {
        String resp = "{\"errorMsg\":\"\",\"markupinfos\":[[{\"markupBy\":\"Rate\",\"markupValue\":-2.5,\"rank\":0}]],\"original\":{\"errorMsg\":\"\",\"exchangeRates\":[{\"from\":\"HKD\",\"rate\":0.843,\"to\":\"CNY\"}],\"lastUpdated\":\"0001-01-01T00:00:00\",\"markupItems\":[{\"airline\":\"MF\",\"id\":\"0\",\"markupBy\":\"Rate\",\"markupCategory\":\"0\",\"markupDescription\":\"CN OTA (VIP N) - MF (GDS/Published Fare) ex-HKG\",\"markupId\":\"65599\",\"markupModes\":[],\"markupValue\":-2.5}],\"success\":\"true\"},\"success\":\"true\"}";
        MarkupResponse markupResponse = JsonUtil.readValue(resp, MarkupResponse.class);
    }
}
