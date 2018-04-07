package com.sunshine.admin.exception;

public class ServerException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private String errorMsg;

  public ServerException(String errorMsg) {
    this.setErrorMsg(errorMsg);
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
