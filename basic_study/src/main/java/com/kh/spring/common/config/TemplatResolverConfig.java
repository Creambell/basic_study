package com.kh.spring.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class TemplatResolverConfig {
	//반환값을 bean으로 등록하겠다.
	@Bean 
	public ClassLoaderTemplateResolver dotDoResolver() {
		ClassLoaderTemplateResolver dotDo = new ClassLoaderTemplateResolver();
		dotDo.setPrefix("templates/views/"); 		 //접두사
 		dotDo.setSuffix(".html"); 					//접미사
		dotDo.setTemplateMode(TemplateMode.HTML);   //어느 템플릿으로 보낼것인지
		dotDo.setCharacterEncoding("UTF-8");
		dotDo.setCacheable(false);                  // 캐시를 꺼주는 코드; 새로고침으로 뷰단 변경사항 확인 가능
		dotDo.setOrder(1); 							// 시작점을 잡아준것, 없어도 무벙방
		dotDo.setCheckExistence(true);              //checkExistence가 없으면 16행,17행까지만 하고 수행을 멈춤
                                                    //  ㄴ여러개의 설정에 대해 읽을 수 있게 만드는 설정	. resolver가 연쇄적으로 작동 할 수 있게 해주는 코드	
		return dotDo;
	}
	@Bean
	public ClassLoaderTemplateResolver doMeResolver() {
		ClassLoaderTemplateResolver dotMe = new ClassLoaderTemplateResolver();
		dotMe.setPrefix("templates/views/member/");
		dotMe.setSuffix(".html");
		dotMe.setTemplateMode(TemplateMode.HTML); 
		dotMe.setCharacterEncoding("UTF-8");
		dotMe.setCacheable(false);
		dotMe.setOrder(1); 			
		dotMe.setCheckExistence(true);
		
		return dotMe;
 	}
	
	@Bean	public ClassLoaderTemplateResolver dotBoResolver() {
		ClassLoaderTemplateResolver dotBo = new ClassLoaderTemplateResolver();
		dotBo.setPrefix("templates/views/board/");
		dotBo.setSuffix(".html");
		dotBo.setTemplateMode(TemplateMode.HTML); 
		dotBo.setCharacterEncoding("UTF-8");
		dotBo.setCacheable(false);
		dotBo.setOrder(2); 			
		dotBo.setCheckExistence(true);
		
		return dotBo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
