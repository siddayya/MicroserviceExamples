package com.dev.ewt.configclient;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@EnableDiscoveryClient
//@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
public class ConfigServerClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerClientApplication.class, args);
	}
	
	
	@Bean
	public Docket newApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .groupName("Config Server client")
	            .apiInfo(apiInfo()).select()
	            .paths(regex("/.*")).build();
	}

	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("SID POC   -  Config server Service API")
	            .description("Config Service API Spec")
	            .contact("@sid")
	            .version("1.0")
	            .build();
	}
	
}
