package com.sunshine.publicserver.utils;

public class ArrayUtil {

    public static String[] tranfer(Object[] origin) {
        if (null == origin || origin.length == 0) {
            return null;
        }

        String[] dst = new String[origin.length];
        for (int i = 0; i < origin.length; i ++) {
            dst[i] = (String) origin[i];
        }
        return dst;
    }
}
