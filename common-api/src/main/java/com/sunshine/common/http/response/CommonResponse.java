package com.sunshine.common.http.response;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;

public class CommonResponse {

	private int status = 1;
	private Object data = new HashMap<>();
	private String errMsg = "";

	public CommonResponse(String key, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, data);
		this.data = map;
	}

	public CommonResponse(Object obj) {
		this.data = obj;
	}

	public CommonResponse() {
	}

	public CommonResponse(ServerException exception) {
		this.status = 0;
		this.errMsg = exception.getMessage();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
