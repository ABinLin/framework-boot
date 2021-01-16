package com.farerboy.framework.boot.core.helper.env;

import com.farerboy.framework.boot.common.enums.EnvEnum;
import com.farerboy.framework.boot.util.AssertUtil;

/**
 * @author linjianbin
 */
public class EnvHelper {

    private static EnvEnum envEnum;

    public static void setEnv(String e) {
        AssertUtil.notBlank(e, "env is required.");
        EnvEnum envEnum = EnvEnum.getEnv(e);
        AssertUtil.notNull(envEnum, "envEnum is required.");
        EnvHelper.envEnum = envEnum;
    }

    public static EnvEnum getEnv() {
        return envEnum;
    }

    public static String getEnvCode() {
        return envEnum.name();
    }
}
