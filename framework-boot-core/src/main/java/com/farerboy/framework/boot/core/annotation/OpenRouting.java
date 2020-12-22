package com.farerboy.framework.boot.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 公开路由注解
 * @Author linjb
 * @Date 2019/7/4 0004 17:48
 * @Version 1.0
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenRouting {

}
