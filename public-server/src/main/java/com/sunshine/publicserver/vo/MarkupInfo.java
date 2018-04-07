package com.sunshine.publicserver.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MarkupInfo {

    private String markupBy;

    private double markupValue;

    private int rank;

    public String getMarkupBy() {
        return markupBy;
    }

    public void setMarkupBy(String markupBy) {
        this.markupBy = markupBy;
    }

    public double getMarkupValue() {
        return markupValue;
    }

    public void setMarkupValue(double markupValue) {
        this.markupValue = markupValue;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
