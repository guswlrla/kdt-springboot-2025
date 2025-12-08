package com.rubypaper;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;

import lombok.RequiredArgsConstructor;

@Service // Repository를 통해 데이터를 가져오며 가공 후 컨트롤러에게 전달
// Controller -> Service -> Repository 순서
// service 계층을 하나 더 만들어서 정보를 가공하는 이유 - 직접 CRUD하면 원본 데이터의 손상 때문
@RequiredArgsConstructor
public class BoardUserDetailService implements UserDetailsService {
	private final MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepo.findById(username).orElseThrow(()-> new UsernameNotFoundException(username + " 사용자 없음"));
		return new User(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}

}
