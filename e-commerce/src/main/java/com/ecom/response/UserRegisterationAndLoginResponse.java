package com.ecom.response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserRegisterationAndLoginResponse {
	
	private String property;
	private String message;
	
	public UserRegisterationAndLoginResponse(){
	}
	
	public UserRegisterationAndLoginResponse(String property, String message) {
		this.property = property;
		this.message = message;
	}

	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "UserRegisterationResponse [property=" + property + ", message=" + message + "]";
	}
	
}
