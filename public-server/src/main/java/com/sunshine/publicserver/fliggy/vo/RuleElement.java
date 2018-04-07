package com.sunshine.publicserver.fliggy.vo;

public class RuleElement {

	private int hasRefund;
	private String refundCurrency;
	private String refund;
	private int partRefund;
	private String partRefundCurrency;
	private String partRefundPrice;
	private String partRefundSeg;
	private int hasEndorse;
	private String endorseCurrency;
	private String endorse;
	private int partEndorse;
	private String partEndorseCurrency;
	private String partEndorsePrice;
	private int endorsement;
	private int hasBaggage;
	private String baggage;
	private int hasNoShow;
	private int noShowLimitTime;
	private String noShowCurrency;
	private String penalty;
	private int specialNoShow;
	private String other;

	public int getHasRefund() {
		return hasRefund;
	}

	public void setHasRefund(int hasRefund) {
		this.hasRefund = hasRefund;
	}

	public String getRefundCurrency() {
		return refundCurrency;
	}

	public void setRefundCurrency(String refundCurrency) {
		this.refundCurrency = refundCurrency;
	}

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public int getPartRefund() {
		return partRefund;
	}

	public void setPartRefund(int partRefund) {
		this.partRefund = partRefund;
	}

	public String getPartRefundCurrency() {
		return partRefundCurrency;
	}

	public void setPartRefundCurrency(String partRefundCurrency) {
		this.partRefundCurrency = partRefundCurrency;
	}

	public String getPartRefundPrice() {
		return partRefundPrice;
	}

	public void setPartRefundPrice(String partRefundPrice) {
		this.partRefundPrice = partRefundPrice;
	}

	public String getPartRefundSeg() {
		return partRefundSeg;
	}

	public void setPartRefundSeg(String partRefundSeg) {
		this.partRefundSeg = partRefundSeg;
	}

	public int getHasEndorse() {
		return hasEndorse;
	}

	public void setHasEndorse(int hasEndorse) {
		this.hasEndorse = hasEndorse;
	}

	public String getEndorseCurrency() {
		return endorseCurrency;
	}

	public void setEndorseCurrency(String endorseCurrency) {
		this.endorseCurrency = endorseCurrency;
	}

	public String getEndorse() {
		return endorse;
	}

	public void setEndorse(String endorse) {
		this.endorse = endorse;
	}

	public int getPartEndorse() {
		return partEndorse;
	}

	public void setPartEndorse(int partEndorse) {
		this.partEndorse = partEndorse;
	}

	public String getPartEndorseCurrency() {
		return partEndorseCurrency;
	}

	public void setPartEndorseCurrency(String partEndorseCurrency) {
		this.partEndorseCurrency = partEndorseCurrency;
	}

	public String getPartEndorsePrice() {
		return partEndorsePrice;
	}

	public void setPartEndorsePrice(String partEndorsePrice) {
		this.partEndorsePrice = partEndorsePrice;
	}

	public int getEndorsement() {
		return endorsement;
	}

	public void setEndorsement(int endorsement) {
		this.endorsement = endorsement;
	}

	public int getHasBaggage() {
		return hasBaggage;
	}

	public void setHasBaggage(int hasBaggage) {
		this.hasBaggage = hasBaggage;
	}

	public String getBaggage() {
		return baggage;
	}

	public void setBaggage(String baggage) {
		this.baggage = baggage;
	}

	public int getHasNoShow() {
		return hasNoShow;
	}

	public void setHasNoShow(int hasNoShow) {
		this.hasNoShow = hasNoShow;
	}

	public int getNoShowLimitTime() {
		return noShowLimitTime;
	}

	public void setNoShowLimitTime(int noShowLimitTime) {
		this.noShowLimitTime = noShowLimitTime;
	}

	public String getNoShowCurrency() {
		return noShowCurrency;
	}

	public void setNoShowCurrency(String noShowCurrency) {
		this.noShowCurrency = noShowCurrency;
	}

	public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}

	public int getSpecialNoShow() {
		return specialNoShow;
	}

	public void setSpecialNoShow(int specialNoShow) {
		this.specialNoShow = specialNoShow;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "RuleElement [hasRefund=" + hasRefund + ", refundCurrency="
				+ refundCurrency + ", refund=" + refund + ", partRefund="
				+ partRefund + ", partRefundCurrency=" + partRefundCurrency
				+ ", partRefundPrice=" + partRefundPrice + ", partRefundSeg="
				+ partRefundSeg + ", hasEndorse=" + hasEndorse
				+ ", endorseCurrency=" + endorseCurrency + ", endorse="
				+ endorse + ", partEndorse=" + partEndorse
				+ ", partEndorseCurrency=" + partEndorseCurrency
				+ ", partEndorsePrice=" + partEndorsePrice + ", endorsement="
				+ endorsement + ", hasBaggage=" + hasBaggage + ", baggage="
				+ baggage + ", hasNoShow=" + hasNoShow + ", noShowLimitTime="
				+ noShowLimitTime + ", noShowCurrency=" + noShowCurrency
				+ ", penalty=" + penalty + ", specialNoShow=" + specialNoShow
				+ ", other=" + other + "]";
	}

}
