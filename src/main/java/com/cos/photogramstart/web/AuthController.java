package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor//final 걸린 모든애들 생성자 만들어줌(참고로 전역변수 final은 첨에 객체 생성할때 무적권 초기화해야함).DI할때씀
@Controller //이것의 의미: 1.ioc에 등록됐다 //2.파일을 리턴한다
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	private final AuthService authService;
	//원래는 아래처럼(의존성주입) 생성자 만드는건데,(이미 ioc된 authService를 이 클래스의 authService에 연결해줘야 하므로)
	//걍 final붙이고 어노테이션으로 해결
//	public AuthController(AuthService authService) {
//		this.authService = authService;
//	}
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	//회원가입버튼누르면 -> 포스트방식으로 singup.jsp의 form요소의 input요소 데리고 /auth/signup 으로 오는데 리턴하는곳인 auth/signin으로 되야함
	//But..CSRF토큰 이슈가 있어(설명은 노션에)
	//그래서 지금  SecurityConfig.java에서 토큰비활성화 코드 집어넣고옴ㅋ
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {//스프링은 기본으로 x-www-form방식으로 데이터를 받는다...
		
		if(bindingResult.hasErrors()) {
			Map<String,String> errorMap = new HashMap<>();
			for(FieldError error: bindingResult.getFieldErrors()){
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationException("유효성검사 실패함",errorMap);
		}else {
			log.info(signupDto.toString());
			//SignupDto -> User
			User user = signupDto.toEntity();
			log.info(user.toString());
			User userEntity = authService.회원가입(user);
			System.out.print(userEntity);
			return "auth/signin";
		}
	}
}
