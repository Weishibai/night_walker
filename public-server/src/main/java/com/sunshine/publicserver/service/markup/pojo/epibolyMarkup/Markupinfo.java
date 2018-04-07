package com.sunshine.publicserver.service.markup.pojo.epibolyMarkup;


/**
 * items数值
 *
 * @author Administrator
 */
public class Markupinfo {
    private Integer rank; // 计算次序
    private String markupBy;// 加点留钱方式
    private Double markupValue; // Markup数值

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getMarkupBy() {
        return markupBy;
    }

    public void setMarkupBy(String markupBy) {
        this.markupBy = markupBy;
    }

    public Double getMarkupValue() {
        return markupValue;
    }

    public void setMarkupValue(Double markupValue) {
        this.markupValue = markupValue;
    }
}
