package com.farerboy.framework.boot.orm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Map;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/1/16 5:33 下午
 */
public interface BaseService<T> extends IService<T> {

    Map<String,Object> getBaseColumn(Class<T> cls);

    QueryWrapper<T> getBaseQueryWrapper(Class<T> cls);

    QueryWrapper<T> getQueryWrapper(T o);

    T newInstance(Class<T> cls);

    @Deprecated
    @Override
    T getById(Serializable id);

    T getById(Class<T> cls, Serializable id);
}
