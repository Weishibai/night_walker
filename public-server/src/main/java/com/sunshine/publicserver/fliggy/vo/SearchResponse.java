package com.sunshine.publicserver.fliggy.vo;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.LinkedList;
import java.util.List;

public class SearchResponse {

	private int status;
	private String msg;
	private List<RoutingElement> routings = Lists.newLinkedList();

	public static final SearchResponse REQUEST_FAIL_RESP = new SearchResponse(1, "");

	public static final SearchResponse SYSTEM_FAIL_RESP = new SearchResponse(2, "");

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<RoutingElement> getRoutings() {
		return routings;
	}

	public void setRoutings(List<RoutingElement> routings) {
		this.routings = routings;
	}

	public SearchResponse() {}

	public SearchResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public SearchResponse(int status, String msg, List<RoutingElement> routings) {
		this.status = status;
		this.msg = msg;
		this.routings = routings;
	}

	public static SearchResponse make(int status, String msg) {
		return new SearchResponse(status, msg);
	}

	public static SearchResponse make(int status, String msg, List<RoutingElement> routings) {
		return new SearchResponse(status, msg, routings);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}



}
