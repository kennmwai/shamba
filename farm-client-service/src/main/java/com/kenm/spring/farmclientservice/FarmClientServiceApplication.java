package com.kenm.spring.farmclientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class FarmClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmClientServiceApplication.class, args);
	}

}
