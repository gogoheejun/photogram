package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException {

	//객체 구분할때 쓰는건데 무시하삼 안중요..jvm한테 중요한거지 나한텐 안중요
	private static final long serialVersionUID = 1L;
	
	private Map<String,String> errorMap;
	
	//얘는 UserService.java에서 orElseThrow()안에 넣으려고 생성자 새로 만든거
	public CustomValidationApiException(String message) {
		super(message);
	}
	
	public CustomValidationApiException(String message, Map<String, String> errorMap) {
		super(message);// super(message)를 통해서 부모의 부모의...부모(Throwalbe.class)의 getMessage()함수가 있어서 getter따로 안만들어도 ㄱㅊ
		this.errorMap = errorMap;//반면 얘는 부모한테 넘길 게 아니라 여기서 자체적으로 Map타입의 에러를 리턴해야 하기때매 getter를 만듦
	}

	public Map<String,String> getErrorMap(){
		return errorMap;
	}
}
