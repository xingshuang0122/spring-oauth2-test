package com.github.xingshuangs.mqtt.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author xingshuang
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    private static final String AUTHORIZATION = "Authorization";

    @Bean
    public Docket commonDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("通用API接口文档")
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                // 指向自己的controller即可
                .apis(RequestHandlerSelectors.basePackage("com.github.xingshuangs.mqtt"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * 设置文档信息
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("用户管理模块")
                //设置作者联系方式,可有可无
                .contact(new Contact("huibo", "www.xingshuangs.com", "market@xingshuang.com"))
                //版本号
                .version("1.0.0")
                //描述
                .description("API 描述")
                .build();

    }

    /**
     * add authorization field to request header
     *
     * @return list of ApiKey
     */
    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(new ApiKey(AUTHORIZATION, AUTHORIZATION, "header"));
    }

    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.any())
                        .build()
        );
    }

    /**
     * create default auth, set authorization scope as global
     *
     * @return list of SecurityReference
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = {authorizationScope};
        return Lists.newArrayList(new SecurityReference(AUTHORIZATION, authorizationScopes));
    }
}

