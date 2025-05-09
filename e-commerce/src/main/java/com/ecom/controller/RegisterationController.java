package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecom.entity.User;
import com.ecom.service.JwtService;
import com.ecom.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class RegisterationController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtService jwtService;
	
	@GetMapping("/register")
	public String returnRegisterationForm(HttpServletRequest request) {
		if(jwtService.checkJwtTokenFromCookie(request)==null) {
			return "redirect:/shop";
		}
		return "userRegisteration";
	}
	
	@ResponseBody
	@PostMapping("/register")
	public ResponseEntity<String> registerNewUser(@ModelAttribute User user) {
		userService.registerUser(user);
		return new ResponseEntity<String>("login",HttpStatus.OK);
	}

}
