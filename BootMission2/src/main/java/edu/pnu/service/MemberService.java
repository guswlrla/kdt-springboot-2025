package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberDTO;

public class MemberService {
	private List<MemberDTO> list = new ArrayList<>();
	
	public MemberService() { // 데이터 초기화
		for(int i = 1; i <= 10; i++) {
			list.add(MemberDTO.builder()
							.id(i).name("name" + i).pass("pass" + i)
							.regidate(new Date()).build());
		}
	}

	public List<MemberDTO> getAllMember() {
		return list;
	}
	public MemberDTO getMemberById(Integer id) {
		MemberDTO dto = list.get(id-1);
		return dto;
	}
	public MemberDTO postMember(MemberDTO memberDTO) {
		int maxId = -1;
		for(MemberDTO i : list) {
			if(i.getId() > maxId) {
				maxId = i.getId();
			}
		}
		memberDTO.setId(maxId+1);
		memberDTO.setRegidate(new Date());
		return memberDTO;
	}
	public MemberDTO putMember(Integer id, MemberDTO memberDTO) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id) {
				memberDTO.setId(id);
				list.set(i, memberDTO);
				return memberDTO;
			}
		}
		return null;
	}
	 public MemberDTO patchMember(Integer id,MemberDTO memberDTO) {
		for(MemberDTO i : list) {
			if(i.getId().equals(id)) {
				i.setName(memberDTO.getName());
				i.setPass(memberDTO.getPass());
			}
		}
		return memberDTO;
	 }
	public void deleteMember(Integer id) {
		MemberDTO dto = null;
		for(MemberDTO i : list) {
			if(i.getId().equals(id)) {
				dto = i;
			}
		}
		list.remove(dto);
	}
}
