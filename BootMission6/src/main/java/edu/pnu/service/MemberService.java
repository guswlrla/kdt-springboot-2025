package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	
	public List<MemberDTO> getAllMember() {
		return memberDao.getAllMember();
	}
	public MemberDTO getMemberById(Integer id) {
		return memberDao.getMemberById(id);
	}
	public MemberDTO postMember(MemberDTO memberDTO) {
		return memberDao.postMember(memberDTO);
	}
	public MemberDTO putMember(Integer id, MemberDTO memberDTO) {
		return memberDao.putMember(id, memberDTO);
	}
	 public MemberDTO patchMember(Integer id, MemberDTO memberDTO) {	
		 return memberDao.patchMember(id, memberDTO);
	 }
	public void deleteMember(Integer id) {
		memberDao.deleteMember(id);
	}
}
