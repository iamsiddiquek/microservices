package com.iris.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
public class IrisGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrisGatewayApplication.class, args);
	}


	@Bean
	public RouteLocator irisRouteLocator(RouteLocatorBuilder locatorBuilder){
		return locatorBuilder.routes()
				.route(p -> p
						.path("/irisbank/accounts/**")
						.filters(f -> f.rewritePath("irisbank/accounts/(?<segment>.*)", "/${segment}")
								.addResponseHeader("x-response-time", new Date().toString()))
						.uri("lb://ACCOUNTS"))

				.route(p -> p
						.path("/irisbank/loans/**")
						.filters(f -> f.rewritePath("irisbank/loans/(?<segment>.*)", "/${segment}")
								.addResponseHeader("x-response-time", new Date().toString()))
						.uri("lb://=loans"))


				.route(p -> p
						.path("/irisbank/cards/**")
						.filters(f -> f.rewritePath("irisbank/cards/(?<segment>.*)", "/${segment}")
								.addResponseHeader("x-response-time", new Date().toString()))
						.uri("lb://cards"))

				.build();

	}

}
