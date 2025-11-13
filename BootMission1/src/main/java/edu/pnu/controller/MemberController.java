package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;

@RestController
@RequestMapping("/api")
public class MemberController {
	// 데이터 저장용 컬렉션 객체 생성
	private List<MemberDTO> list = new ArrayList<>();
	
	public MemberController() { // 데이터 초기화
		for(int i = 1; i <= 10; i++) {
			list.add(MemberDTO.builder()
							.id(i).name("name" + i).pass("pass" + i)
							.regidate(new Date()).build());
		}
	}
	@GetMapping("/member") // 검색(Read - select All)
	public List<MemberDTO> getAllMember() { 
		return list;
	}
	@GetMapping("/member/{id}") // 검색(Read – select One)
	public MemberDTO getMemberById(@PathVariable Integer id) {
		MemberDTO dto = list.get(id-1);
		return dto;
	}
	@PostMapping("/member") // 입력(Create - insert)
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
	@PutMapping("/member/{id}") // 수정(Update – 객체 교체)
	public MemberDTO putMember(@PathVariable Integer id, MemberDTO memberDTO) {
		for(int i = 1; i < list.size(); i++) {
			MemberDTO
		}
		return memberDTO;
	}
}
