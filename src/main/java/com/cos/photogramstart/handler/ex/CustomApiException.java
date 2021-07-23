package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException {

	//객체 구분할때 쓰는건데 무시하삼 안중요..jvm한테 중요한거지 나한텐 안중요
	private static final long serialVersionUID = 1L;
	
	
	//얘는 UserService.java에서 orElseThrow()안에 넣으려고 생성자 새로 만든거
	public CustomApiException(String message) {
		super(message);
	}
	
}
