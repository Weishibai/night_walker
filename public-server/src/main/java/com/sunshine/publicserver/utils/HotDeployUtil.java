package com.sunshine.publicserver.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class HotDeployUtil {

    private static Map<String, String> hotDeployMap;

    static {
        hotDeployMap = Maps.newHashMap();
        hotDeployMap.put("priceCacheTime", "1200"); /* seconds */
        hotDeployMap.put("priceCacheFailCount", "3"); /* seconds */
    }

    public static String get(String key) {
        if (StringUtils.isBlank(key)) {
            return StringUtils.EMPTY;
        }

        String val = hotDeployMap.get(key);
        return null == val ? StringUtils.EMPTY : val;
    }

    public static void refresh(String key, String updatedValue) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        hotDeployMap.put(key, updatedValue);
    }


}
