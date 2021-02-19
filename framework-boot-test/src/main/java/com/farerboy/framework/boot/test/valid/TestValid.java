package com.farerboy.framework.boot.test.valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/19 4:51 下午
 */
public class TestValid {
    public static void main(String[] args) {
        User user= new User();
        user.setId(1);
        user.setName(" s");
        user.setList(new ArrayList(){{add("aa");}});
        user.setSet(new HashSet(){{add("sss");}});
        user.setMap(new HashMap(){{put("s","s");}});
        user.validate();
    }
}
