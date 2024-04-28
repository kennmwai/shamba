package com.kenm.spring.farmleaseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FarmLeaseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmLeaseServiceApplication.class, args);
	}

}
