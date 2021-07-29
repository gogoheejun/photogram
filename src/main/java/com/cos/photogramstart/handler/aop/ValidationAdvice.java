package com.cos.photogramstart.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;

@Component //ioc에 띄워야 하는데, 애매할땐 component. restController,Service이런애들이다 component를 상속해서 만든애들임
@Aspect //이걸 써야 aop할수있는 애가 됨
public class ValidationAdvice {

	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))") //어떤 특정함수시작 전에 시작해서 끝날때까지 관여하기...@Before는 시작전에 발동, @After는 끝난담에 발동
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint)throws Throwable {
		//proceedingJoinPoint란, 접근하는 클래스의 함수의 모든곳에 접근할 수 있는 변수임.
		
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg: args) {
			if(arg instanceof BindingResult) {
				System.out.println("유효성 검사를 하는 함수입니다!!");
				BindingResult bindingResult = (BindingResult) arg;
				
				if(bindingResult.hasErrors()) {
					Map<String,String> errorMap = new HashMap<>();
					for(FieldError error: bindingResult.getFieldErrors()){
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					throw new CustomValidationApiException("유효성검사 실패함",errorMap);
				}
			}
		}
		
		return proceedingJoinPoint.proceed();//다시 돌아가서 원래함수 실행시킴
	}
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint)throws Throwable {
		
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg: args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				if(bindingResult.hasErrors()) {
					Map<String,String> errorMap = new HashMap<>();
					for(FieldError error: bindingResult.getFieldErrors()){
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					throw new CustomValidationException("유효성검사 실패함",errorMap);
				}
			}
		}
		
		return proceedingJoinPoint.proceed();
	}
}
