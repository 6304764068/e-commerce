package com.ecom.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecom.service.JwtService;
import com.ecom.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtService jwtService;

	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		Map<String, Object> currentLoggedInUserInfo = jwtService.checkJwtTokenFromCookie(request);
		if(currentLoggedInUserInfo==null) {
			return "userLogin";
		}
		else {
			return "redirect:";
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(String mobileOrEmail, String password, HttpServletResponse response) {
		String jwt = userService.loginUser(mobileOrEmail, password);
		Cookie cookie = new Cookie("token", jwt);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		
		return new ResponseEntity<String>("",HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		response.addCookie(new Cookie("token", ""));
		return "redirect:/login";
	}
	
}
