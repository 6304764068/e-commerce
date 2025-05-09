package com.ecom.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.User;
import com.ecom.exception.InvalidUserDetailsException;
import com.ecom.repository.UserRepo;
import com.ecom.response.UserRegisterationAndLoginResponse;
import com.ecom.validation.UserAndAddressValidation;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserAndAddressValidation userAndAddressValidation;
	
	@Autowired
	UserRegisterationAndLoginResponse userRegisterationAndLoginResponse;
	
	public void registerUser(User user) {
		userAndAddressValidation.validateNewUser(user);
		user.setId(UUID.randomUUID().toString());
		userRepo.save(user);
	}

	public String loginUser(String mobileOrEmail, String password) {
		User user = userRepo.findByEmailOrMobile(mobileOrEmail, mobileOrEmail);
		if(user==null) {
			userRegisterationAndLoginResponse.setProperty("MOBILE_OR_EMAIL");
			userRegisterationAndLoginResponse.setMessage("invalid username or mobile");
			throw new InvalidUserDetailsException(userRegisterationAndLoginResponse);
		}
		else {
			if(user.getPassword().equals(password)) {
				return jwtService.generateToken(user);
			}
			else {
				userRegisterationAndLoginResponse.setProperty("PASSWORD");
				userRegisterationAndLoginResponse.setMessage("invalid password");
				throw new InvalidUserDetailsException(userRegisterationAndLoginResponse);
			}
			
		} 
			
	}
}
