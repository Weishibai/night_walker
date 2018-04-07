package com.sunshine.publicserver.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorContext.class);

    public static long addRecord(String name, long stTime) {
        LOGGER.debug(name + " costs {}ms", System.currentTimeMillis() - stTime);
        return System.currentTimeMillis();
    }

}
