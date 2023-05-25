package com.bkk.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Knife4j配置
 *
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(Collections.singleton("https"))
                .host("https://www.xiaoakai.top")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bkk.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客API文档")
                .description("基于SpringBoot + Vue开发的博客项目")
                .termsOfServiceUrl("https://www.xiaoakai.top/api")
                .contact(new Contact("小阿凯", "https://github.com/xiao_a_kai", "1172531498@qq.com"))
                .version("2.0")
                .build();
    }

}