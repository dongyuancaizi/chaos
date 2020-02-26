package com.cui.tech.${package.ModuleName};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.cui.tech.${package.ModuleName}.service.mapper")
@ComponentScan(basePackages = {"com.cui.tech.chaos.lite","com.cui.tech.${package.ModuleName}"})
public class Application {

public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}