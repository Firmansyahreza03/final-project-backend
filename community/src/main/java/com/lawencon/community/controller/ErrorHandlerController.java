package com.lawencon.community.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.community.exception.InvalidLoginException;
import com.lawencon.community.pojo.PojoErrorRes;
import com.lawencon.util.VerificationCodeUtil;
import com.lawencon.util.VerificationCodeUtil.InvalidVerificationCodeException;


@ControllerAdvice
public class ErrorHandlerController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleDtoValidation(MethodArgumentNotValidException e){
		List<String> msg = new ArrayList<>();
		
		for(FieldError ror : e.getBindingResult().getFieldErrors()) {	
			msg.add(ror.getDefaultMessage());
		}
		
		PojoErrorRes<List<String>> erorRes = new PojoErrorRes<List<String>>();
		erorRes.setMessage(msg);
		
		return new ResponseEntity<>(erorRes, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<?> handleFailLogin(InvalidLoginException e){
		PojoErrorRes<String> respond = new PojoErrorRes<>();
		
		respond.setMessage(NestedExceptionUtils.getMostSpecificCause(e).getMessage());

		return new ResponseEntity<>(respond, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(VerificationCodeUtil.InvalidVerificationCodeException.class)
	public ResponseEntity<?> handleInvalidVerifCode(InvalidVerificationCodeException e){
		PojoErrorRes<String> respond = new PojoErrorRes<>();
		
		respond.setMessage(NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		
		return new ResponseEntity<>(respond, HttpStatus.BAD_REQUEST);
	}
	
}
