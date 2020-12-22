package com.farerboy.framework.boot.util.excel;

import java.lang.annotation.*;

/**
 * @Description TODO
 * @Author linjb
 * @Date 2019/7/11 0011 18:43
 * @Version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAnnotation {
    //Excel列ID(Excel列排序序号)
    int id();
    //Excel列名
    String[] name();
    //Excel列宽
    int width() default 5000;
}
