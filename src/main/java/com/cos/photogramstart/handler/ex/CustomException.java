package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomException extends RuntimeException {

	//객체 구분할때 쓰는건데 무시하삼 안중요..jvm한테 중요한거지 나한텐 안중요
	private static final long serialVersionUID = 1L;

	
	public CustomException(String message) {
		super(message);
	}
}
