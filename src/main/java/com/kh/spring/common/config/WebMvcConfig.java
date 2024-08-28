package com.kh.spring.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.spring.common.interceptor.CheckAdminInterceptor;
import com.kh.spring.common.interceptor.CheckLoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 이미지 파일을 위한 리소스 핸들러
        registry.addResourceHandler("/uploadFiles/**")
                .addResourceLocations("file:///C:/uploadFiles/"
						,"classpath:/static/image/","/uploadFiles/**","C:/uploadFiles");
        
        // 정적 리소스를 위한 핸들러
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override 
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckLoginInterceptor())
                .addPathPatterns("/myInfo.me", "/editMyInfo.me", "/updateMember.me")
                .addPathPatterns("/*.bo", "/*.at")
                .excludePathPatterns("/list.bo", "/topList.bo", "/list.at");

        registry.addInterceptor(new CheckAdminInterceptor())
                .addPathPatterns("/*.ad")
        		.excludePathPatterns("/selectImageBoard.ad");
    }
}