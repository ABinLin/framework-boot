package com.farerboy.framework.boot.core.helper.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * ApplicationContextHelper
 *
 * @author farerboy
 * @date 2020/12/22 11:11 上午
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(ApplicationContextHelper.applicationContext == null) {
            ApplicationContextHelper.applicationContext = applicationContext;
        }
        logger.info("ApplicationContextHelper.setApplicationContext({})", ApplicationContextHelper.applicationContext);
    }

    public <T> Map<String, T> getBeansOfType(Class<T> clazz){
        return ApplicationContextHelper.applicationContext.getBeansOfType(clazz);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    public <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
