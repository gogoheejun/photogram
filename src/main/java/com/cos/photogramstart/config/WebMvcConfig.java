package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration//web설정파일
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		//이런거 외우지말고 복붙해서 수정해서 사용만해
		registry
		.addResourceHandler("/upload/**")//jsp페이지에서 /upload/**이런 주소 패턴이 나오면 발동
		.addResourceLocations("file:///"+uploadFolder)//C:/workspace/springbootwork/upload/
		.setCachePeriod(60*10*6)//1시간동안 이미지 캐시
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
	}
}
