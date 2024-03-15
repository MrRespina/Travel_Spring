package com.travel.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

// thymeleaf에 대한 config 파일 생성 (Bean 관리용)
@Configuration
public class ApplicationConfig {

	@Bean
	public ClassLoaderTemplateResolver userTemplateResolver() {
	    ClassLoaderTemplateResolver userTemplateResolver = new ClassLoaderTemplateResolver();
	    userTemplateResolver.setPrefix("templates/user/");
	    userTemplateResolver.setSuffix(".html");
	    userTemplateResolver.setTemplateMode(TemplateMode.HTML);
	    userTemplateResolver.setCharacterEncoding("UTF-8");
	    userTemplateResolver.setOrder(1);
	    userTemplateResolver.setCheckExistence(true);
	        
	    return userTemplateResolver;
	}
	
	@Bean
	public ClassLoaderTemplateResolver contentTemplateResolver() {
	    ClassLoaderTemplateResolver contentTemplateResolver = new ClassLoaderTemplateResolver();
	    contentTemplateResolver.setPrefix("templates/content/");
	    contentTemplateResolver.setSuffix(".html");
	    contentTemplateResolver.setTemplateMode(TemplateMode.HTML);
	    contentTemplateResolver.setCharacterEncoding("UTF-8");
	    contentTemplateResolver.setOrder(2);
	    contentTemplateResolver.setCheckExistence(true);
	        
	    return contentTemplateResolver;
	}
	
	@Bean
	public ClassLoaderTemplateResolver boardTemplateResolver() {
	    ClassLoaderTemplateResolver boardTemplateResolver = new ClassLoaderTemplateResolver();
	    boardTemplateResolver.setPrefix("templates/board/");
	    boardTemplateResolver.setSuffix(".html");
	    boardTemplateResolver.setTemplateMode(TemplateMode.HTML);
	    boardTemplateResolver.setCharacterEncoding("UTF-8");
	    boardTemplateResolver.setOrder(3);
	    boardTemplateResolver.setCheckExistence(true);
	        
	    return boardTemplateResolver;
	}
	
	
}
