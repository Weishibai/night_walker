package com.sunshine.common.vo;

public enum TripType {

	Single(1), Round(2), ALL(3);
	TripType(int code) {
		this.code = code;
	}

	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static TripType get(int code) {
		for (TripType val : TripType.values()) {
			if (code == val.code) {
				return val;
			}
		}
		return null;
	}

}
