package com.farerboy.framework.boot.util.excel;

import java.lang.annotation.*;

/**
 *
 * @author farerboy
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
