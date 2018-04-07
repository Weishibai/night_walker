package com.sunshine.publicserver.fliggy.vo;

public enum FlightType {
    ONE_WAY(1),
    ROUND_TRIP(2),
    MULTI_TRIP(3);

    private int code;

    FlightType(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }

    public static FlightType codeOf(int flightType) {
        for (FlightType type : values()) {
            if (type.code == flightType) {
                return type;
            }
        }
        return null;
    }

}
