package com.sunshine.publicserver.enums;

public enum Month {

    Jan(1),
    Feb(2),
    Mar(3),
    Apr(4),
    May(5),
    Jun(6),
    Jul(7),
    Aug(8),
    Sep(9),
    Oct(10),
    Nov(11),
    Dec(12);

    private int code;

    Month(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }

    public static Month codeOf(int code) {
        for (Month month : values()) {
            if (month.code() == code) {
                return month;
            }
        }
        return null;
    }
}
