package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.persistence.MemberRepository;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final AuthenticationConfiguration authenticationConfiguration;
	private final MemberRepository memberRepository;
	
	@Resource(name="${project.oauth2login.qualifier.name}")
	private AuthenticationSuccessHandler oauth2SuccessHandler;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests(auth->auth.requestMatchers("/member/**").authenticated()
											.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
											.requestMatchers("/admin/**").hasRole("ADMIN")
											.anyRequest().permitAll());
		http.formLogin(frmLogin->frmLogin.disable());
		http.httpBasic(basic->basic.disable());
		http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// 스프링 시큐리티의 필터 체인에 작성한 필터를 추가한다. UsernamePasswordAuthenticationFilter를 상속한 필터이므로
		// 원래 UsernamePasswordAuthenticationFilter가 위치하는 곳에 대신 추가된다.
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		// 스프링 시큐리티가 등록한 필터들 중에서 AuthorizationFilter 앞에 JWTAuthorizationFilter를 삽입
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);
		
		http.oauth2Login(oauth2->oauth2.successHandler(oauth2SuccessHandler));
		
		return http.build();
	}
	

	
	
}
