package com.kh.spring.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.spring.common.interceptor.CheckLoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**") // 매핑 URI 설정 예시로 image/uploadfiles...
				.addResourceLocations("file:///C:/uploadFiles/"
						,"classpath:/static/image/"); // 이 파일 뒤에 ,"자기 파일경로" 써주세용
	}
	
	/* 로그인 구현전에는 보류
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(new CheckLoginInterceptor())
	 * .addPathPatterns("/myInfo.me","/editMyInfo.me","/updateMember.me",
	 * "/updatePassword.me","/deleteMember.me") .addPathPatterns("/*.bo","/*.at") //
	 * 스프링부트에서는 .bo 와 .at등 설정해준걸로 해줘야해요.
	 * .excludePathPatterns("/list.bo","/topList.bo","/list.at"); // 이는 제외해주는 코드
	 * 
	 * 
	 * 
	 * }
	 */
}
