package com.cui.tech.chaos.lite.web.config;

import com.cui.tech.chaos.lite.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jinbin
 * @date 2018-07-08 22:33
 */
@Configuration
@Order(120)
public class JwtInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/manage/**")
                .addPathPatterns("/wxmini/**");
    }

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;
}
