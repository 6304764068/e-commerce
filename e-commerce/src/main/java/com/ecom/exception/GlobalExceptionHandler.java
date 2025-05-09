package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecom.response.UserRegisterationAndLoginResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = InvalidUserDetailsException.class)
	public ResponseEntity<UserRegisterationAndLoginResponse> invlidUserDetailsException(InvalidUserDetailsException e){
		return new ResponseEntity<UserRegisterationAndLoginResponse>(e.getUserRegisterationResponse(),HttpStatus.BAD_REQUEST);
	}
	
}


