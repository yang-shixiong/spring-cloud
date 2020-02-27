package com.example.demo.config;

import com.example.demo.inteceptor.ApiInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 将拦截器注册到springboot中
 */
@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        // 注册我们写的拦截器
        registry.addInterceptor(new ApiInterceptor());
    }
}
