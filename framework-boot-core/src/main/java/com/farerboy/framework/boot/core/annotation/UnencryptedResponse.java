package com.farerboy.framework.boot.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 强制返回值不加密
 *
 * @Author linjb
 * @Date 2020/5/27 0027 20:46
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnencryptedResponse {
}
