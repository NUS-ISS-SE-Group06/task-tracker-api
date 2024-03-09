package com.nus.iss.tasktracker.config;

import com.nus.iss.tasktracker.interceptor.TaskTrackerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TaskTrackerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login"); // Exclude specific URL pattern
    }
}