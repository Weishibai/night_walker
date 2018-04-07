package com.sunshine.common.vo;

import com.sunshine.dal.entity.InfoCenterEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class InfoCenterData implements Serializable {

    private static final long serialVersionUID = -43375406370365315L;

    private String airportCode;

    private String cityCode;

    private String countryCode;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public static InfoCenterData make(String airportCode, String cityCode, String countryCode) {
        InfoCenterData data = new InfoCenterData();
        data.setAirportCode(airportCode);
        data.setCityCode(cityCode);
        data.setCountryCode(countryCode);
        return data;
    }

    public static InfoCenterEntity build(InfoCenterData data) {
        InfoCenterEntity entity = new InfoCenterEntity();
        entity.setAirportCode(data.getAirportCode());
        entity.setCityCode(data.getCityCode());
        entity.setCountryCode(data.getCountryCode());
        return entity;
    }

    public static InfoCenterData make(InfoCenterEntity entity) {
        if (null == entity) {
            return null;
        }

        InfoCenterData data = new InfoCenterData();
        data.setAirportCode(entity.getAirportCode());
        data.setCityCode(entity.getCityCode());
        data.setCountryCode(entity.getCountryCode());
        return data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
