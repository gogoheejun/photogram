package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity//해당파일로 시큐리티를 활성화
@Configuration //ioc에 등록.메모리에 뜨게  
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean//user 비밀번호 암호화하려고...SecurityConfig가 ioc에 등록될때 @Bean읽어서 리턴값을 들고있음. DI해서 쓰기만 하면돼
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super 삭제 - 기존시큐리티가 가지고 있는 기능 다 비활성화됨
		http.csrf().disable();//csrf토큰 비활성화
		http.authorizeRequests()
			.antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**","/api/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin() //아디넣고 비번치는 그런걸로 로그인한다
			.loginPage("/auth/signin")// 그 로그인페이지는 요 경로다.....이건 Get으로
			//위 loginPage()는 로그인이 필요한 페이지를 get으로 요청하면 저 경로를 보여주는거고, 누군가가 post로 로그인요청하면 아래 loginProcessingUrl()이 실행됨
			.loginProcessingUrl("/auth/signin")//POST=> 스프링 시큐리티가 로그인 프로세스 진행
			.defaultSuccessUrl("/");//정상적으로 로그인하면 요 경로로 간다
	}
}
