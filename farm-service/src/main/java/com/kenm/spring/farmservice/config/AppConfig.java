/**
 *
 */
package com.kenm.spring.farmservice.config;

import java.time.Duration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kenm.spring.farmservice.mapper.FarmMapper;
import com.kenm.spring.farmservice.mapper.impl.FarmMapperImpl;
import com.kenm.spring.farmservice.service.FarmService;
import com.kenm.spring.farmservice.service.LeaseServiceClient;
import com.kenm.spring.farmservice.service.impl.FarmServiceImpl;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

/**
 * @author User F
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.kenm.spring.farmservice")
@EnableJpaRepositories(basePackages = "com.kenm.spring.farmservice.repository")
@EnableFeignClients(clients = LeaseServiceClient.class)
public class AppConfig {
	@Bean
	CircuitBreakerConfig circuitBreakerConfig() {
		return CircuitBreakerConfig.custom().waitDurationInOpenState(Duration.ofMillis(5000)).build();
	}

	@Bean
	FarmMapper farmMapper() {
		return new FarmMapperImpl();
	}

	@Bean
	FarmService farmService() {
		return new FarmServiceImpl();
	}

	@Bean
	TimeLimiterConfig timeLimiterConfig() {
		return TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(5000)).build();
	}
}
