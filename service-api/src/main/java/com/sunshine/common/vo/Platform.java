package com.sunshine.common.vo;

public enum Platform {
	Fliggy(0), Qunar(1), Ctrip(2), ALL(3);
	Platform(int code) {
		this.code = code;
	}

	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static Platform get(int code) {
		for (Platform val : Platform.values()) {
			if (code == val.code) {
				return val;
			}
		}
		return null;
	}

}
