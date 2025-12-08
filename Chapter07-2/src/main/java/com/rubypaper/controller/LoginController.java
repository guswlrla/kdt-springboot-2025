package com.rubypaper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied");
	}
	
	@GetMapping("/auth1")
	@ResponseBody // http 요청의 본문(body) 부분이 그대로 전달
	// @RestController(@Controller + @ResponseBody)
	public ResponseEntity<?> auth1(@AuthenticationPrincipal User user) {
		if(user == null)
			return ResponseEntity.ok("로그인 상태가 아닙니다.");
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/auth2")
	public @ResponseBody ResponseEntity<?> auth2(Authentication auth) {
		System.out.println(auth.getPrincipal());
		System.out.println(auth.getCredentials());
		System.out.println(auth.getAuthorities());
		return ResponseEntity.status(HttpStatus.OK).body(auth);
	}
}
