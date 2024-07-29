package com.kh.spring.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**") // 매핑 URI 설정 예시로 image/uploadfiles...
				.addResourceLocations("file:///C:/uploadFiles/","D:\\basic_study\\basic_study\\src\\main\\resources\\static\\image"); // 이 파일 뒤에 ,"자기 파일경로" 써주세용
	}
}
