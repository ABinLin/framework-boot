package com.farerboy.framework.boot.orm.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * TODO description
 * 2021/3/13 10:05 下午
 *
 * @author linjianbin
 */
public class BasePageParam {

    @ApiModelProperty(value = "当前页",notes = "默认1",required =false)
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页的数量",notes = "默认20",required =false)
    private Integer pageSize = 20;

    @ApiModelProperty(value = "排序",notes = "desc/asc",required =false)
    private String order;

    @ApiModelProperty(value = "排序字段",required =false)
    private String sort;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
