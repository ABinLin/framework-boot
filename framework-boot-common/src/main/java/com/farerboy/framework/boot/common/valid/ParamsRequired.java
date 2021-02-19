package com.farerboy.framework.boot.common.valid;

import com.farerboy.framework.boot.common.annotaion.NotNull;
import com.farerboy.framework.boot.common.exception.BaseException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 参数验证
 *
 * @author linjianbin
 */
public interface ParamsRequired {

    default boolean validate()  {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            if (notNull != null) {
                field.setAccessible(true);
                Object fieldObject = null;
                try {
                    fieldObject = field.get(this);;
                } catch (IllegalAccessException e) {
                    throw new BaseException("IllegalAccessException",e);
                }
                String message = notNull.value();
                if(null == message || "".equals(message.trim())){
                    message = field.getName()+" can not be null !";
                }
                if (fieldObject == null) {
                    throw new BaseException(message);
                }
                if(fieldObject instanceof String && "".equals(fieldObject.toString().trim())){
                    throw new BaseException(message);
                }
                if(fieldObject instanceof Collection){
                    Collection collection = (Collection) fieldObject;
                    if(collection.isEmpty()){
                        throw new BaseException(message);
                    }
                }
                if(fieldObject instanceof Map){
                    Map map = (Map) fieldObject;
                    if(map.isEmpty()){
                        throw new BaseException(message);
                    }
                }
            }
        }
        return true;
    }
}
