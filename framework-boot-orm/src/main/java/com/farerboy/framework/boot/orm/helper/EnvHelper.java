package com.farerboy.framework.boot.orm.helper;

import com.farerboy.framework.boot.util.AssertUtil;

/**
 * @author linjianbin
 */
public class EnvHelper {

    private static String env;

    public static void setEnv(String e) {
        AssertUtil.notBlank(e, "Env is required.");
        EnvHelper.env = e;
    }

    public static String getEnv() {
        return env;
    }

}
