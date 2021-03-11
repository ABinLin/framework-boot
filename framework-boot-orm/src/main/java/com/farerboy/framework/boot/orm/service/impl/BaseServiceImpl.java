package com.farerboy.framework.boot.orm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.farerboy.framework.boot.orm.context.AbstractDefaultColumnContext;
import com.farerboy.framework.boot.orm.service.BaseService;
import java.io.Serializable;
import java.util.Map;

/**
 * TODO description
 *
 * @author linjianbin
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements BaseService<T> {

//    @Override
//    public boolean save(T entity) {
//        Class cls = entity.getClass();
//        Field[] fields = cls.getDeclaredFields();
//        for (Field f : fields) {
//            f.setAccessible(true);
//            String columnStr = null;
//            TableField tableField = f.getAnnotation(TableField.class);
//            if(tableField != null && StringUtils.isNotBlank(tableField.value())){
//                columnStr = tableField.value();
//            }
//            if(columnStr == null){
//                columnStr = f.getName();
//            }
//            if(AbstractDefaultColumnContext.contains(columnStr)){
//                try {
//                    f.set(entity,AbstractDefaultColumnContext.getDefaultColumn(columnStr));
//                }catch (Exception e){
//                    throw new BaseException("SET_DEFAULT_COLUMN_ERROR",e.getMessage(),e);
//                }
//            }
//        }
//        return retBool(baseMapper.insert(entity));
//    }

    @Override
    public Map<String, Object> getBaseColumn(Class<T> cls) {
        return AbstractDefaultColumnContext.getBaseColumn(cls);
    }

    @Override
    public QueryWrapper<T> getBaseQueryWrapper(Class<T> cls) {
        return AbstractDefaultColumnContext.getBaseQueryWrapper(cls);
    }

    @Override
    public QueryWrapper<T> getQueryWrapper(T o) {
        return AbstractDefaultColumnContext.getQueryWrapper(o);
    }

    @Override
    public T newInstance(Class<T> cls) {
        return AbstractDefaultColumnContext.newInstance(cls);
    }

    @Override
    public T getById(Serializable id){
        return super.getById(id);
    }

    @Override
    public T getById(Class<T> cls, Serializable id) {
        QueryWrapper wrapper = AbstractDefaultColumnContext.getIdQueryWrapper(cls,id);
        getOne(wrapper);
        return null;
    }
}
