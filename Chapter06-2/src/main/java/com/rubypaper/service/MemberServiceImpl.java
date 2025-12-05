package com.rubypaper.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	public Member getMember(Member member) {
		return getMember(member.getId());
	}

	@Override
	public Member getMember(String id) {
		Optional<Member> findMember = memberRepo.findById(id);
		if(findMember.isPresent())
			return findMember.get();
		else
			return null;
	}

}
