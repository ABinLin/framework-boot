package com.farerboy.framework.boot.core.configuration;

import com.farerboy.framework.boot.core.properties.ProjectProperties;
import com.farerboy.framework.boot.core.properties.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
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
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(value = {"farerboy.swagger.enable"}, matchIfMissing = true)
public class SwaggerConfiguration {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Value("${spring.application.name:farerboy-application}")
    private String application;

    @Autowired
    private ProjectProperties projectProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(RestController.class))
                // 包下的类，才生成接口文档
                // .apis(RequestHandlerSelectors.basePackage("demo.yiyu.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        String title = swaggerProperties.getTitle();
        if(StringUtils.isBlank(title)){
            title = projectProperties.getName();
            apiInfoBuilder.title(swaggerProperties.getTitle());
        }
        if(StringUtils.isBlank(title)){
            title = application;
        }
        apiInfoBuilder.title(title);
        String description = swaggerProperties.getDescription();

        if(StringUtils.isBlank(description)){
            description = title;
        }
        apiInfoBuilder.description(description);

        if(StringUtils.isNotBlank(swaggerProperties.getTermsOfServiceUrl())){
            apiInfoBuilder.termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl());
        }
        if(StringUtils.isNotBlank(swaggerProperties.getContactName())){
            apiInfoBuilder.contact(new Contact(swaggerProperties.getContactName(),swaggerProperties.getContactUrl(),swaggerProperties.getContactEmail()));
        }
        if(StringUtils.isNotBlank(swaggerProperties.getVersion())){
            apiInfoBuilder.version(swaggerProperties.getVersion());
        }
        return apiInfoBuilder.build();
    }

    private List<ApiKey> security() {
        List<ApiKey> apiKeyList = newArrayList();
        if(StringUtils.isNotBlank(swaggerProperties.getSecuritySchemes())){
            String[] headers = this.swaggerProperties.getSecuritySchemes().split(",");
            for (String header : headers) {
                apiKeyList.add(new ApiKey(header,header,"header"));
            }
        }
        return apiKeyList;
    }
}
