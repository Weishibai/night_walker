package com.sunshine.publicserver.fliggy.vo;

import java.io.Serializable;
import java.util.List;

public class RoutingElement implements Serializable {

	private static final long serialVersionUID = -43354637036575315L;

	private String data;
	private int adultPrice;
	private int adultTax;
	private int childPrice;
	private int childTax;
	private int nationalityType;
	private String nationality;
	private String suitAge;
	private int priceType;
	private int applyType;
	private int adultTaxType;
	private int childTaxType;
	private int minPassengerCount;
	private int maxPassengerCount;
	private int gvChildRule;
	private RuleElement rule;
	private List<SegmentElement> fromSegments;
	private List<SegmentElement> retSegments;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(int adultPrice) {
		this.adultPrice = adultPrice;
	}

	public int getAdultTax() {
		return adultTax;
	}

	public void setAdultTax(int adultTax) {
		this.adultTax = adultTax;
	}

	public int getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(int childPrice) {
		this.childPrice = childPrice;
	}

	public int getChildTax() {
		return childTax;
	}

	public void setChildTax(int childTax) {
		this.childTax = childTax;
	}

	public int getNationalityType() {
		return nationalityType;
	}

	public void setNationalityType(int nationalityType) {
		this.nationalityType = nationalityType;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getSuitAge() {
		return suitAge;
	}

	public void setSuitAge(String suitAge) {
		this.suitAge = suitAge;
	}

	public int getPriceType() {
		return priceType;
	}

	public void setPriceType(int priceType) {
		this.priceType = priceType;
	}

	public int getApplyType() {
		return applyType;
	}

	public void setApplyType(int applyType) {
		this.applyType = applyType;
	}

	public int getAdultTaxType() {
		return adultTaxType;
	}

	public void setAdultTaxType(int adultTaxType) {
		this.adultTaxType = adultTaxType;
	}

	public int getChildTaxType() {
		return childTaxType;
	}

	public void setChildTaxType(int childTaxType) {
		this.childTaxType = childTaxType;
	}

	public int getMinPassengerCount() {
		return minPassengerCount;
	}

	public void setMinPassengerCount(int minPassengerCount) {
		this.minPassengerCount = minPassengerCount;
	}

	public int getMaxPassengerCount() {
		return maxPassengerCount;
	}

	public void setMaxPassengerCount(int maxPassengerCount) {
		this.maxPassengerCount = maxPassengerCount;
	}

	public int getGvChildRule() {
		return gvChildRule;
	}

	public void setGvChildRule(int gvChildRule) {
		this.gvChildRule = gvChildRule;
	}

	public RuleElement getRule() {
		return rule;
	}

	public void setRule(RuleElement rule) {
		this.rule = rule;
	}

	public List<SegmentElement> getFromSegments() {
		return fromSegments;
	}

	public void setFromSegments(List<SegmentElement> fromSegments) {
		this.fromSegments = fromSegments;
	}

	public List<SegmentElement> getRetSegments() {
		return retSegments;
	}

	public void setRetSegments(List<SegmentElement> retSegments) {
		this.retSegments = retSegments;
	}

	@Override
	public String toString() {
		return "RoutingElement [data=" + data + ", adultPrice=" + adultPrice
				+ ", adultTax=" + adultTax + ", childPrice=" + childPrice
				+ ", childTax=" + childTax + ", nationalityType="
				+ nationalityType + ", nationality=" + nationality
				+ ", suitAge=" + suitAge + ", priceType=" + priceType
				+ ", applyType=" + applyType + ", adultTaxType=" + adultTaxType
				+ ", childTaxType=" + childTaxType + ", minPassengerCount="
				+ minPassengerCount + ", maxPassengerCount="
				+ maxPassengerCount + ", gvChildRule=" + gvChildRule
				+ ", rule=" + rule + ", fromSegments=" + fromSegments
				+ ", retSegments=" + retSegments + "]";
	}

}
