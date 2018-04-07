package com.sunshine.common.vo;

import com.sunshine.dal.entity.ExchangeEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class ExchangeInfo implements Serializable {

    private static final long serialVersionUID = -4337547036575315L;

    private String srcCurrency;

    private String dstCurrency;

    private double rate;

    public String getSrcCurrency() {
        return srcCurrency;
    }

    public void setSrcCurrency(String srcCurrency) {
        this.srcCurrency = srcCurrency;
    }

    public String getDstCurrency() {
        return dstCurrency;
    }

    public void setDstCurrency(String dstCurrency) {
        this.dstCurrency = dstCurrency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public static ExchangeInfo make(String srcCurrency, String dstCurrency) {
        ExchangeInfo info = new ExchangeInfo();
        info.setSrcCurrency(srcCurrency);
        info.setDstCurrency(dstCurrency);
        return info;
    }

    public static ExchangeInfo make(ExchangeEntity exchangeEntity) {
        if (null == exchangeEntity) {
            return null;
        }

        ExchangeInfo exchangeInfo = new ExchangeInfo();
        exchangeInfo.setSrcCurrency(exchangeEntity.getSrcCurrency());
        exchangeInfo.setDstCurrency(exchangeEntity.getDstCurrency());
        exchangeInfo.setRate(exchangeEntity.getRate());
        return exchangeInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
