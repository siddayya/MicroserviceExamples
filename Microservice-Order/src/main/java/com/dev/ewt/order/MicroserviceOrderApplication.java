package com.dev.ewt.order;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableCircuitBreaker
//@RibbonClient("order")
@EnableSwagger2
public class MicroserviceOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceOrderApplication.class, args);
	}
	
	
	
	//@Profile("standalone")
	@Bean
	public Docket newApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .groupName("Order")
	            .apiInfo(apiInfo()).select()
	            .paths(regex("/.*")).build();
	}

	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("Sid Order Service  - Order Service API")
	            .description("Order Service API Spec")
	            .contact("Sid @ ")
	            .version("1.0")
	            .build();
	}
}
