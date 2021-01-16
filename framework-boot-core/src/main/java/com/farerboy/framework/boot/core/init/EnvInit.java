package com.farerboy.framework.boot.core.init;

import com.farerboy.framework.boot.core.helper.env.EnvHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author linjianbin
 */
@ConditionalOnProperty(value = {"farerboy.project.env-enable"}, matchIfMissing = false)
@Component
@Order(Integer.MIN_VALUE)
public class EnvInit implements ApplicationRunner {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(StringUtils.isBlank(env)){
            env = "dev";
        }
        EnvHelper.setEnv(env);
        log.info("Init project env = {}",EnvHelper.getEnvCode());
    }
}
