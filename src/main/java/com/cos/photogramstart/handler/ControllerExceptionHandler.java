package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController //응답을 데이터로
@ControllerAdvice //이거 붙이면 모든 익셉션 다 낚아챔
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)//CustomValidationException이 발동하는 모든 익셉션을 이 함수가 가로채
	//CMRespDto, Script 비교
	//1.클라이언트에게 응답할 때는 Script가 좋지만
	//2.Ajax통신, Android에는 CMRespDto를 씀
	public String validationException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString());
	}
	
	@ExceptionHandler(CustomValidationApiException.class)//CustomValidationException이 발동하는 모든 익셉션을 이 함수가 가로채
	public ResponseEntity<?> validationException(CustomValidationApiException e) {
		//e.getMessage()는 ajax가 error.responseJSON.message로 보내지고, e.getErrorMap()는 error.reponseJSON.data로 보내짐
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class)//CustomValidationException이 발동하는 모든 익셉션을 이 함수가 가로채
	public ResponseEntity<?> apiException(CustomApiException e) {
		//e.getMessage()는 ajax가 error.responseJSON.message로 보내지고, e.getErrorMap()는 error.reponseJSON.data로 보내짐
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
	}
}
