package com.kenm.spring.farmapigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FarmApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmApiGatewayServiceApplication.class, args);
	}

}
