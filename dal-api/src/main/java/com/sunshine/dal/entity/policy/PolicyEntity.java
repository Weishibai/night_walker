package com.sunshine.dal.entity.policy;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * policy skeleton class for further extension
 */
public abstract class PolicyEntity {

    private BigDecimal adultRate;

    private BigDecimal adultExpense;

    private BigDecimal childRate;

    private BigDecimal childExpense;

    private String cabins;

    private String supportAirlines;

    private int gdsType;



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
