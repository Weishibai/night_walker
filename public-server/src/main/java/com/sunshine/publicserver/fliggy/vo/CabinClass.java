package com.sunshine.publicserver.fliggy.vo;

import org.apache.commons.lang.StringUtils;

public enum CabinClass {
    First("F"),
    Business("C"),
    Economy("Y");

    private String code;
    CabinClass(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    public static String codeOf(String name) {
        for (CabinClass cabinClass : values()) {
            if (StringUtils.equalsIgnoreCase(cabinClass.name(), name)) {
                return cabinClass.code();
            }
        }
        return "Y";
    }

    public static String invCodeOf(String name) {
        for (CabinClass cabinClass : values()) {
            if (StringUtils.equalsIgnoreCase(cabinClass.code(), name)) {
                return cabinClass.name();
            }
        }
        return Economy.name();
    }
}
