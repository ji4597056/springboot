package com.study.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 非@component获取bean实例
 *
 * @author Jeffrey
 * @since 2017/3/7 13:44
 */
@Component
public class ApplicationContextReader implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        appContext = context;
    }

    public static <T> T getBean(String beanName) {
        return (T) appContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return appContext.getBean(clazz);
    }
}
