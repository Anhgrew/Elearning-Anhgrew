package com.myclass.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
				.basePackage("com.myclass.controller"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("My REST API", "Api of Elearning", "API TOS", "http://localhost:8080/swagger-ui.html",
				new Contact("Nguyễn Tiến Anh", "www.nguyentienanh.com", "socksockmoizui@gmail.com"), "License of API",
				"API license URL", Collections.emptyList());
	}
}
