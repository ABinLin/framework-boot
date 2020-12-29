package com.farerboy.framework.boot.core.configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger配置
 *
 * @author farerboy
 * @date 2020/12/22 11:23 上午
 */
@Configuration
@EnableSwagger2
@Profile({"dev","test"})
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(value = {"knife4j.enable"}, matchIfMissing = true)
public class SwaggerConfiguration {

    @Value("${spring.application.name:yiyu-application}")
    private String applicationName;

    @Value("${yiyu.app.description:易宇应用系统}")
    private String yiyu_app_description;

    @Value("${yiyu.app.termsOfServiceUrl:http://www.xueerqin.net}")
    private String yiyu_app_termsOfServiceUrl;

    @Value("${yiyu.app.version:0.0.1}")
    private String yiyu_app_version;

    @Value("${yiyu.app.security:token,roleCode}")
    private String yiyu_app_security;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 包下的类，才生成接口文档
                // .apis(RequestHandlerSelectors.basePackage("demo.yiyu.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(yiyu_app_description)
                .termsOfServiceUrl(yiyu_app_termsOfServiceUrl)
                .contact(new Contact("Java超神组合","",""))
                .version(yiyu_app_version)
                .build();
    }

    private List<ApiKey> security() {
        List<ApiKey> apiKeyList = newArrayList();
        String[] headers = this.yiyu_app_security.split(",");
        for (String header : headers) {
            apiKeyList.add(new ApiKey(header,header,"header"));
        }
        return apiKeyList;
    }
}
