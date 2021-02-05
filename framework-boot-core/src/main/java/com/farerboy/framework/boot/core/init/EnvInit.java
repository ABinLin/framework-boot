package com.farerboy.framework.boot.core.init;

import com.farerboy.framework.boot.core.helper.env.EnvHelper;
import com.farerboy.framework.boot.core.properties.ProjectProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Value("${spring.profiles.active:dev}")
    private String env;

    @Autowired
    private ProjectProperties projectProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String e = null;
        if(StringUtils.isNotBlank(projectProperties.getEnv())){
            e = projectProperties.getEnv();
        }
        if(StringUtils.isBlank(e)){
            e = env ;
        }
        EnvHelper.setEnv(e);
        log.info("Init project env = {}",EnvHelper.getEnv());
    }
}
