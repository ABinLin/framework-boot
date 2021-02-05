package com.farerboy.framework.boot.test.callback;

import com.farerboy.framework.boot.core.callback.PropertiesCallback;

import java.util.Properties;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/5 2:53 下午
 */
public class SpringCallback implements PropertiesCallback {

    @Override
    public void callback(Properties properties) {
        System.out.println(properties);
    }
}
