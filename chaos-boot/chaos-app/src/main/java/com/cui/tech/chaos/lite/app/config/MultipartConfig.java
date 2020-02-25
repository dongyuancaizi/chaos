package com.cui.tech.chaos.lite.app.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 文件上传配置
 *
 * @author xiaoshiyilang
 * @version 1.0
 * @date 2018/10/16
 */
@Configuration
@Component
public class MultipartConfig {

    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大
        //factory.setMaxFileSize("100MB"); // KB,MB
        /// 设置总上传数据总大小
        //factory.setMaxRequestSize("1000MB");
        return factory.createMultipartConfig();
    }

}
