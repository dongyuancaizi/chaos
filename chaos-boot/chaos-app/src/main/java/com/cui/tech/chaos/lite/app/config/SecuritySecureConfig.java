package com.cui.tech.chaos.lite.app.config;


import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author J.C
 * @date 2020/1/5 13:08
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //拦截所有endpoint，拥有ACTUATOR_ADMIN角色可访问，否则需登录
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR_ADMIN")
                //静态文件允许访问
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // swagger start
                .antMatchers("/swagger-ui.html").authenticated()
                .antMatchers("/swagger-resources/**").authenticated()
                .antMatchers("/v2/api-docs").authenticated()
                // swagger end
                //.antMatchers("/actuator/**").authenticated()
                //根路径允许访问
                //.antMatchers("/").permitAll()
                //所有请求路径可以访问
                .antMatchers("/web/**").permitAll()
                .and().httpBasic();
        http.csrf().disable();
        //http.ignoringAntMatchers("/druid/*");
    }
}
