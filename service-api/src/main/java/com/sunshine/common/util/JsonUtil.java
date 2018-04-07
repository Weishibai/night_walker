package com.sunshine.common.util;


import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * since 2017/6/28.
 */
public class JsonUtil {

    public final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static String writeString(Object obj) {
        try {
            if (obj != null) {
                return OBJECT_MAPPER.writeValueAsString(obj);
            }
            return StringUtils.EMPTY;
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static String writeString(Object obj, String defaultVal) {
        try {
            if (obj != null) {
                return OBJECT_MAPPER.writeValueAsString(obj);
            }
            return defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static <T> T readValue(String s, Class<T> clz) {
        try {
            return OBJECT_MAPPER.readValue(s, clz);
        } catch (IOException e) {
            return null;
        }
    }
}
