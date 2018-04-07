package com.sunshine.publicserver.service.markup.pojo.markup;

import java.util.List;


public class MarkupResult {
	private String success;	//是否成功
	private String errorMsg;//失败信息
	private String lastUpdated;//最后更新时间
	private List<ExchangeRates> exchangeRates;	//汇率信息
	private List<MarkupItems> markupItems;		//Markup 信息

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

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<ExchangeRates> getExchangeRates() {
		return exchangeRates;
	}

	public void setExchangeRates(List<ExchangeRates> exchangeRates) {
		this.exchangeRates = exchangeRates;
	}

	public List<MarkupItems> getMarkupItems() {
		return markupItems;
	}

	public void setMarkupItems(List<MarkupItems> markupItems) {
		this.markupItems = markupItems;
	}
}