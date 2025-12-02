package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	public List<Member> getAllMember() {
		List<Member> list = memberRepo.findAll();
		for(Member m : list) {
			System.out.println(m);
		}
		return list;
	}
	public Member getMemberById(Integer id) {
		return memberRepo.findById(id).get();
	}
	public Member postMember(Member member) {
		if(member.getRegidate() == null) {
	        member.setRegidate(new Date());
	    }
		return memberRepo.save(member);
	}
	public Member putMember(Integer id, Member member) {
		Member m = memberRepo.findById(id).get();
		if(member.getRegidate() == null) {
	        member.setRegidate(m.getRegidate());
	    }
		member.setId(id);
		return memberRepo.save(member);
	}
	 public Member patchMember(Integer id, Member member) {	
		 Member m = memberRepo.findById(id).get();
		 
		 if(member.getPass() != null) {
			 m.setPass(member.getPass());
		 }
		 if(member.getName() != null) {
			 m.setName(member.getName());
		 }
		 return memberRepo.save(m);
	 }
	public void deleteMember(Integer id) {
		memberRepo.deleteById(id);
	}
}
