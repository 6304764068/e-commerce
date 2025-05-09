package com.ecom.service;

import java.security.Key;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ecom.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
	
	private static final String SECRET = "ashokkukmarlagadapatiashokkumarlagadapati";

	public String generateToken(User user) {
		Map<String, String> claims = new HashMap<String, String>();
		claims.put("user-id", user.getId());
		claims.put("full-name", user.getFullName());
		claims.put("email", user.getEmail());
		
		Date expireTime = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(expireTime);
		calendar.add(Calendar.MINUTE, 2);
		expireTime = calendar.getTime();
		
		return Jwts.builder()
			.setIssuedAt(new Date())
			.setClaims(claims)
			.setExpiration(expireTime)
			.signWith(getHmacShaSecret())
			.compact();
		
	}
	
	private Key getHmacShaSecret() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	
	public Map<String, Object> checkJwtTokenFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies==null) {
			return null;
		}
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("token")) {
				Map<String, Object> currentLoggedInUserInfo = checkJwtTokenValid(cookie.getValue());
				if(currentLoggedInUserInfo==null) {
					return null;
				}
				else {
					return currentLoggedInUserInfo;
				}
			}
		}
		return null;
	}
	
	public Map<String, Object> checkJwtTokenValid(String token) {
		
		if(token==null || token=="") {
			return null;
		}
		else {
			
			JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder();
			jwtParserBuilder.setSigningKey(getHmacShaSecret());
			
			JwtParser jwtParser = jwtParserBuilder.build();
			
			try {
				
				Jws<Claims> jwsClaims = jwtParser.parseClaimsJws(token);
				Claims claims = jwsClaims.getBody();
				String usernameFull = (String) claims.get("full-name");
				System.err.println(usernameFull);
				
				Map<String, Object> currentLoggedInUserInfo = new HashMap<String, Object>();
				currentLoggedInUserInfo.put("id",claims.get("user-id"));
				currentLoggedInUserInfo.put("name",claims.get("full-name"));
				currentLoggedInUserInfo.put("email",claims.get("email"));
				return currentLoggedInUserInfo;
				
			} catch (ExpiredJwtException e) {
				System.out.println("token expired");
				return null;
			}
			
		}
		
	}

}




