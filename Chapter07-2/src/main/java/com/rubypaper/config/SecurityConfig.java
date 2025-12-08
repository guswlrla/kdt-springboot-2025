package com.rubypaper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 빈을 등록할 때 싱글톤이 되도록 보장, 스프링 컨테이너에서 빈 관리할 수 있게 해줌
// Configuration 어노테이션을 통해 프록시(가짜) 객체를 빈으로 등록해서 싱글톤 유지
// @Bean이 있는 메소드를 여러 번 호출하여도 항상 동일한 객체를 반환하여 싱글톤을 보장
public class SecurityConfig {
	@Bean // 매서드가 반환하는 객체를 스프링 빈으로 수동 등록
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(security->security
				.requestMatchers("/member/**").authenticated() // member로 시작하는 모든 url은 로그인한 사용자만 접근 가능
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()); // 위의 url을 제외한 모든 요청은 그냥 허용
		http.csrf(cf->cf.disable());
		// "/login" url을 사용자 정의 로그인 페이지로 사용
		// 로그인 성공 시 항상 "/loginSuccess" 페이지로 이동
		// true로 인해, 이전 페이지 기억안하고 무조건 "/loginSuccess"로 이동
		http.formLogin(form->form.loginPage("/login").defaultSuccessUrl("/loginSuccess", true));
		// 권한이 없는 사용자가 접근하면 "/accessDenied"로 이동 
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		// logout.invalidateHttpSession(true) - 로그아웃 시 세션 삭제
		// deleteCookies("JSESSIONID") - JSESSIONID 쿠키 삭제
		// logoutSuccessUrl("/login") - 로그아웃 후 "/logout" 페이지로 이동
		http.logout(logout->logout.invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/login"));
		return http.build(); // 설정이 모두 적용된 SecurityFilterChain 빈을 생성
	}
}
