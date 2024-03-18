package com.kenm.spring.farmservice.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kenm.spring.farmservice.service.FarmLeaseServiceClient;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class FeignClientConfig {

    @Value("${farm-lease-service.url}")
    private String farmLeaseServiceUrl;

    @Bean
    FarmLeaseServiceClient farmServiceClient() {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(FarmLeaseServiceClient.class))
                .logLevel(Logger.Level.FULL)
                .target(FarmLeaseServiceClient.class, farmLeaseServiceUrl);
    }

    @Bean
    CircuitBreakerConfig circuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .waitDurationInOpenState(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    TimeLimiterConfig timeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(5000))
                .build();
    }
}
