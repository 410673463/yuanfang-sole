package com.pt.zh.yuanfang.common.config;


import com.pt.zh.yuanfang.modules.core.interceptor.AuthorizationTokenInterceptor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@CommonsLog
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Bean
    public AuthorizationTokenInterceptor authorizationTokenInterceptor() {
        return new AuthorizationTokenInterceptor();
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册token认证拦截器
        registry.addInterceptor(authorizationTokenInterceptor()).addPathPatterns("/**");
        System.out.println("token认证拦截器,注册成功");
    }


}
