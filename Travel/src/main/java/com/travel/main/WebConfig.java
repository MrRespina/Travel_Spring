package com.travel.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 해당 URL에 대한 CORS 허용 설정
                .allowedOrigins("http://localhost:3001"); // Node.js 서버의 주소로 변경
    }
}