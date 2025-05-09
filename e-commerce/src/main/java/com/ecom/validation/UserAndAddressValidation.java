package com.ecom.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecom.entity.Address;
import com.ecom.entity.User;
import com.ecom.exception.InvalidUserDetailsException;
import com.ecom.response.UserRegisterationAndLoginResponse;

@Component
public class UserAndAddressValidation {
	
	@Autowired
	UserRegisterationAndLoginResponse userRegisterationResponse;

	public boolean validateNewUser(User user) {
		if(validateFullName(user.getFullName()) && validateUsername(user.getUsername()) && validateEmail(user.getEmail()) && validatePassword(user.getPassword()) && validateMobile(user.getMobile()))
			return true;
		else
			return false;
	}
	
	public boolean validateAddress(Address address) {
		if(validateCity(address.getCity()) && validateVillage(address.getVillage()) && validateLandmark(address.getLandmark()) && validatePincode(address.getPincode()) && validateStreet(address.getStreet()))
			return true;
		
		else
			return false;
	}
	
//	-------------(validate user)----------------
	
	public boolean validateUsername(String username) {
		username = username.trim();
		for(int i=0; i<username.length(); i++) {
			if(!((username.charAt(i)>='a' && username.charAt(i)<='z') || (username.charAt(i)>='A' && username.charAt(i)<='Z') || (username.charAt(i)>='0' && username.charAt(i)<='9'))) {
				userRegisterationResponse.setProperty("USERNAME");
				userRegisterationResponse.setMessage("invalid username");
				throw new InvalidUserDetailsException(userRegisterationResponse);
			}
		}
		return true;
	}
	
	public boolean validatePassword(String password) {
		return true;
	}
	
	public boolean validateFullName(String fullName) {
		return true;
	}
	
	public boolean validateMobile(String mobile) {
		return true;
	}
	
	public boolean validateEmail(String email) {
		return true;
	}
	
//	-----------(address validation)---------------
	
	public boolean validateCity(String city) {
		return true;
	}
	
	public boolean validateVillage(String village) {
		return true;
	}
	
	public boolean validatePincode(int pincode) {
		return true;
	}
	
	public boolean validateStreet(String street) {
		return true;
	}
	
	public boolean validateLandmark(String landmark) {
		return true;
	}
}
