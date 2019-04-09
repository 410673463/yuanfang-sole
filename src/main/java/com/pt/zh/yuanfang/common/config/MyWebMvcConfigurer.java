package com.pt.zh.yuanfang.common.config;


import com.pt.zh.yuanfang.modules.core.interceptor.AuthorizationTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Bean
    public AuthorizationTokenInterceptor authorizationTokenInterceptor() {
        return new AuthorizationTokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationTokenInterceptor()).addPathPatterns("/**");
    }


}
