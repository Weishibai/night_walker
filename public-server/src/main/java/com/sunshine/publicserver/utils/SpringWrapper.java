package com.sunshine.publicserver.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: liuzz
 * Date: 12-8-7
 * Time: 下午6:09
 * 由于目前大部分的bean没有在spring中管理,导致无法注入，所以先使用一个wrapper适配一下
 */
@Service
public class SpringWrapper implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        if (context == null) {
            throw new IllegalStateException("SpringWrapper未注入");
        }
        return (T) context.getBean(clazz);
    }
}
