package com.rubypaper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 웹어플리케이션 요청과 응답 처리, view 반환
// "/~"로 요청하면 주소를 받아들여 어디로 갈지 분석하고 맞는 길로 연결시켜주는 역할
public class SecurityController {
	@GetMapping("/")
	public String index() {
		System.out.println("index 요청");
		return "index";
	}
	
	@GetMapping("/member")
	public void member() {
		System.out.println("Member 요청");
	}
	
	@GetMapping("/manager")
	public void manager() {
		System.out.println("Manager 요청");
	}
	
	@GetMapping("/admin")
	public void admin() {
		System.out.println("Admin 요청");
	}
}
