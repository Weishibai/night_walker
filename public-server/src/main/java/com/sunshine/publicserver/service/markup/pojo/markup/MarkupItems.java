package com.sunshine.publicserver.service.markup.pojo.markup;

import java.util.List;

public class MarkupItems {
	private String id;
	private String markupBy;
	private Double markupValue;
	private String markupDescription;
	private String airline;
	private String markupCategory;
	private String markupId;
	private List<MarkupModes> markupModes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getMarkupDescription() {
		return markupDescription;
	}
	public void setMarkupDescription(String markupDescription) {
		this.markupDescription = markupDescription;
	}
	public List<MarkupModes> getMarkupModes() {
		return markupModes;
	}
	public void setMarkupModes(List<MarkupModes> markupModes) {
		this.markupModes = markupModes;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getMarkupCategory() {
		return markupCategory;
	}
	public void setMarkupCategory(String markupCategory) {
		this.markupCategory = markupCategory;
	}
	public String getMarkupId() {
		return markupId;
	}
	public void setMarkupId(String markupId) {
		this.markupId = markupId;
	}
}
