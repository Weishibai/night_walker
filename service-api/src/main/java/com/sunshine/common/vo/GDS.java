package com.sunshine.common.vo;

public enum GDS {
	Amadeus(0), Sabre(1), Galileo(2), ALL(3);

	GDS(int code) {
		this.code = code;
	}

	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static GDS get(int code) {
		for (GDS val : GDS.values()) {
			if (code == val.code) {
				return val;
			}
		}
		return null;
	}

}
