//package com.farerboy.framework.boot.core.callback;
//
//import com.farerboy.framework.boot.util.ClassUtil;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//
//import java.io.IOException;
//import java.util.Properties;
//
//public class PropertyPlaceholderConfigurerAndCallback extends PropertyPlaceholderConfigurer {
//
//    private Class<? extends PropertiesCallback> callbackClass;
//
//    public void setCallbackClass(Class<? extends PropertiesCallback> callbackClass) {
//        this.callbackClass = callbackClass;
//    }
//
//    @Override
//    public void setLocation(Resource location) {
//        this.setLocations(location);
//    }
//
//    @Override
//    public void setLocations(Resource... locations) {
//        if (this.callbackClass != null) {
//            Properties properties = this.loadResources(locations);
//            PropertiesCallback callback = ClassUtil.newInstance(this.callbackClass);
//            callback.callback(properties);
//        }
//        super.setLocations(locations);
//    }
//
//    private Properties loadResources(Resource[] resources) {
//        Properties properties = new Properties();
//        for (Resource location : resources) {
//            try {
//                PropertiesLoaderUtils.fillProperties(properties, location);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return properties;
//    }
//
//}