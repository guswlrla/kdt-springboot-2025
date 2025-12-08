package com.rubypaper;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;

import lombok.RequiredArgsConstructor;

@Component // 클래스를 빈으로 등록
/* 
 * @Component vs @Bean
 * 
 * @Component : 클래스 레벨에서 사용
 * @Bean : 메서드 레벨에서 사용
*/
@RequiredArgsConstructor // final, @NonNull이 붙은 필드를 매개변수로 하는 생성자를 생성
public class MemberInit implements ApplicationRunner {
	private final MemberRepository memberRepo;
	private final PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		memberRepo.save(Member.builder()
				.id("member")
				.password(encoder.encode("abcd"))
				.name("사용자")
				.role(Role.ROLE_MEMBER)
				.enabled(true)
				.build());
		
		memberRepo.save(Member.builder()
				.id("manager")
				.password(encoder.encode("abcd"))
				.name("관리자")
				.role(Role.ROLE_MANAGER)
				.enabled(true)
				.build());
		
		memberRepo.save(Member.builder()
				.id("admin")
				.password(encoder.encode("abcd"))
				.name("최고관리자")
				.role(Role.ROLE_ADMIN)
				.enabled(true)
				.build());
	}

}
