package com.farerboy.framework.boot.test.valid;

import com.farerboy.framework.boot.common.annotaion.NotNull;
import com.farerboy.framework.boot.common.valid.ParamsRequired;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 * @author linjianbin
 * @date 2021/2/19 4:51 下午
 */
@Data
public class User implements ParamsRequired {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private List list;
    @NotNull("你好吗")
    private Set set;
    @NotNull
    private Map map;
}
