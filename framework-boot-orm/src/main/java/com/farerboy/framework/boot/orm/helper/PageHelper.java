package com.farerboy.framework.boot.orm.helper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

/**
 * 分页助手
 *
 * @author farerboy
 */
public class PageHelper {

    private static final String ASC = "asc";
    private static final String DESC = "desc" ;

    public static IPage page(int pageNum, int pageSize, String sort, String order) {
        if(pageNum < 1){
            pageNum = 1;
        }
        if(pageSize < 1){
            pageSize = 20;
        }
        // 分页对象
        Page page = new Page(pageNum, pageSize);
        // 排序字段
        // 防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        if(StringUtils.isNotBlank(sort)){
            String orderField = SqlHelper.sqlInject(order);
            // 前端字段排序
            if (StringUtils.isNotBlank(orderField)) {
                if (ASC.equalsIgnoreCase(order)) {
                    page.setAsc(orderField);
                } else if (DESC.equalsIgnoreCase(order)) {
                    page.setDesc(orderField);
                } else {
                    page.setAsc(orderField);
                }
            }
        }
        return page;
    }
}
