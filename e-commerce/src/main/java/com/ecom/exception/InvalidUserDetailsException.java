package com.ecom.exception;

import com.ecom.response.UserRegisterationAndLoginResponse;

public class InvalidUserDetailsException extends RuntimeException {
	
	private UserRegisterationAndLoginResponse userRegisterationResponse;
	
	public InvalidUserDetailsException(UserRegisterationAndLoginResponse userRegisterationResponse) {
		this.userRegisterationResponse = userRegisterationResponse;
	}
	
	public UserRegisterationAndLoginResponse getUserRegisterationResponse() {
		return userRegisterationResponse;
	}

	public void setUserRegisterationResponse(UserRegisterationAndLoginResponse userRegisterationResponse) {
		this.userRegisterationResponse = userRegisterationResponse;
	}
	
}
