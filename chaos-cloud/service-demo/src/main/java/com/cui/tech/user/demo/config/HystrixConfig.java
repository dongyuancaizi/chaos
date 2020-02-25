package com.cui.tech.user.demo.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class HystrixConfig {
    //http://localhost:8081/actuator/hystrix.stream
}
