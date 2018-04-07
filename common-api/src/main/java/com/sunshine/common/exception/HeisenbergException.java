package com.sunshine.common.exception;

public class HeisenbergException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorMsg;

	public HeisenbergException(String errorMsg) {
		this.setErrorMsg(errorMsg);
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
