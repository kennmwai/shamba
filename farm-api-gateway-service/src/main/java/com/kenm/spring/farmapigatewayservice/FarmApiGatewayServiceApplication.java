package com.kenm.spring.farmapigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FarmApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmApiGatewayServiceApplication.class, args);
	}

}
