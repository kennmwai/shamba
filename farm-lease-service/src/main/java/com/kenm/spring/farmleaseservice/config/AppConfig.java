/**
 *
 */
package com.kenm.spring.farmleaseservice.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.kenm.spring.farmleaseservice.mapper.FarmLeaseMapper;
import com.kenm.spring.farmleaseservice.mapper.impl.FarmLeaseMapperImpl;
import com.kenm.spring.farmleaseservice.service.FarmLeaseService;
import com.kenm.spring.farmleaseservice.service.impl.FarmLeaseServiceImpl;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

/**
 * @author Ken Mwai
 */
@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.kenm.spring.farmleaseservice.repository")
@ComponentScan(basePackages = "com.kenm.spring.farmleaseservice")
@EnableTransactionManagement
public class AppConfig {

	@Bean
	CircuitBreakerConfig circuitBreakerConfig() {
		return CircuitBreakerConfig.custom().waitDurationInOpenState(Duration.ofMillis(5000)).build();
	}

    @Bean
    FarmLeaseMapper farmLeaseMapper() {
        return new FarmLeaseMapperImpl();
    }

	@Bean
	FarmLeaseService farmLeaseService() {
		return new FarmLeaseServiceImpl();
	}

	@Bean
	TimeLimiterConfig timeLimiterConfig() {
		return TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(5000)).build();
	}

}
