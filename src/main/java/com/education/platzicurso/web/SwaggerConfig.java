package com.education.platzicurso.web;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.education.platzicurso.web.controller"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointInfo());
	}

	private ApiInfo apiEndPointInfo() {
		return new ApiInfoBuilder().title("API de productos")
				.description("Servicios para la consulta de productos de una supermercado, esta es un api de prueba")
				.license("Apache 2.0")
				.version("1.0.0")
				.licenseUrl("hhtp://www.apache.org/licenses/LICENSE-2.0.html")
				.contact(new Contact("Jhon Feria", "", "edisonferia4@gmail.com"))
				.build();

	}

}
