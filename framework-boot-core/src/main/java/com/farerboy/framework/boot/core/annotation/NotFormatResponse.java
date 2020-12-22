package com.farerboy.framework.boot.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 不包装返回对象
 * @Author xpy
 * @Date 2019/7/4 0004 17:48
 * @Version 1.0
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotFormatResponse {
}
