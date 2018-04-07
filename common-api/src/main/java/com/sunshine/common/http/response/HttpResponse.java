package com.sunshine.common.http.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class HttpResponse<T> implements Serializable {

    private static final long serialVersionUID = 362498820763181265L;

    private String msg;

    private int status;

    private T data;

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpResponse() {

    }

    public boolean isSuccess() {
        return 0 == status;
    }

    public HttpResponse(String msg, int status, T data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public static <V> HttpResponse<V> success(V data) {
        HttpResponse<V> response = new HttpResponse<>();
        response.setData(data);
        response.setStatus(0);
        response.setMsg("");
        return response;
    }

    public static <V> HttpResponse<V> failed(String msg) {
        HttpResponse<V> response = new HttpResponse<>();
        response.setData(null);
        response.setStatus(0);
        response.setMsg(msg);
        return response;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
