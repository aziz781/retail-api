package com.retail.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by abdulaziz on 20/10/2016.
 */


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.retail.manager.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Retail Manager API",
                "Retail Manager Restful Web Services.",
                "v1",
                "https://uk.linkedin.com/in/abdulaziz786",
                new springfox.documentation.service.Contact("Abdul Aziz", "https://uk.linkedin.com/in/abdulaziz786", "seeazee@gmail.com"),
                "Apache 2.0 licence",
                "https://uk.linkedin.com/in/abdulaziz786");
        return apiInfo;
    }
}