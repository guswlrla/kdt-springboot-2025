package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private final MemberRepository memberRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 요청 헤더에서 JWT를 얻어옴
		String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(jwtToken == null || !jwtToken.startsWith(JWTUtil.prefix)) {
			// 없거나 "Bearer "로 시작하지 않는다면
			filterChain.doFilter(request, response); // 필터를 그냥 통과
			return;
		}
		
		// 토큰에서 username, provider, email 추출
		// provider와 email은 OAuth2User가 아니면 null
		String username = JWTUtil.getClaim(jwtToken, JWTUtil.usernameClaim);
		String provider = JWTUtil.getClaim(jwtToken, JWTUtil.providerClaim);
		String email = JWTUtil.getClaim(jwtToken, JWTUtil.emailClaim);
		
		User user = null;
		Optional<Member> opt = memberRepository.findById(username);

		if(!opt.isPresent()) { // 사용자가 존재하지 않는다면
			// 데이터베이스는 없지만 provider, email이 null이 아니면
			// 데이터베이스에 사용자를 저장하지 않는 OAuth2 인증인 경우
			if(provider == null || email == null) {
				System.out.println("[JWTAuthorizationFilter]not found user!");
				filterChain.doFilter(request, response); // 필터를 그냥 통과
				return;
			}
			System.out.println("[JWTAuthorizationFilter]username: " + username);
			user = new User(username, "****", AuthorityUtils.createAuthorityList(Role.ROLE_MEMBER.toString()));
		} else {
			Member member = opt.get();
			System.out.println("[JWTAuthorizationFilter]" + member);
			user = new User(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
		}
		// 인증 객체 생성
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		// SecurityContext에 등록
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		// SecurityFilterChain의 다음 필터로 이동
		filterChain.doFilter(request, response);
	}
}
