package com.farerboy.framework.boot.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/3 3:02 下午
 */
@SpringBootApplication(scanBasePackages = {"com.farerboy"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
