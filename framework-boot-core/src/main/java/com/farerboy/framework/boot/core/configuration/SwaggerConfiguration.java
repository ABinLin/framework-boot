package com.farerboy.framework.boot.core.configuration;

import com.farerboy.framework.boot.common.exception.BaseException;
import com.farerboy.framework.boot.core.properties.ProjectProperties;
import com.farerboy.framework.boot.core.properties.SwaggerProperties;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.Annotation;
import java.util.List;
import static com.google.common.collect.Lists.newArrayList;

/**
 * Swagger配置
 *
 * @author farerboy
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(value = {"farerboy.swagger.enable"}, matchIfMissing = true)
public class SwaggerConfiguration {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private SwaggerProperties swaggerProperties;

    @Value("${spring.application.name:farerboy-application}")
    private String application;

    @Autowired
    private ProjectProperties projectProperties;

    @Bean
    public Docket restApi(){
        ApiSelectorBuilder apiSelectorBuilder =new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select();
        if(StringUtils.isNotBlank(swaggerProperties.getScanAnnotation())){
            Class<? extends Annotation> annotation = null;
            Class clazz = null;
            try {
                clazz = Class.forName(swaggerProperties.getScanAnnotation());
            }catch (ClassNotFoundException e){
                throw new BaseException("ClassNotFoundException",e.getMessage(),e);
            }
            annotation = clazz;
            apiSelectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(annotation));
        } else if(StringUtils.isNotBlank(swaggerProperties.getScanBasePackage())){
            apiSelectorBuilder.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getScanBasePackage()));
        } else {
            apiSelectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        }
        return apiSelectorBuilder.paths(PathSelectors.any()).build().securitySchemes(security());
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
