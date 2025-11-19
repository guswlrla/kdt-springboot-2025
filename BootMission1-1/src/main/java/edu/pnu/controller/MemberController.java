package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;

@RestController
@RequestMapping("/api")
public class MemberController {
	private List<MemberDTO> list = new ArrayList<>();

	// 생성자에서 왜 초기화? - 클래스가 처음 생성될 때 생성자가 한 번만 실행
	// api 호출 시마다 리스트를 만들 필요 없이, 한 번만 만들고 재사용
	public MemberController() {
		for(int i = 1; i <= 10; i++) {
			list.add(MemberDTO.builder()
					.id(i).name("name" + i).pass("pass" + i)
					.regidate(new Date()).build());
		}
	}
	
	@GetMapping("/member") // 검색(Read –select All)
	 public List< MemberDTO> getAllMember() {
		return list;
	 }
	
	 @GetMapping("/member/{id}") // 검색(Read –select One)
	 public MemberDTO getMemberById(@PathVariable Integer id) {
		 return list.get(id-1);
	 }
	 
	 @PostMapping("/member") // 입력(Create -insert)
	 public MemberDTO postMember(MemberDTO memberDTO) {
		 
		 return null;
	 }
	 
	 
	
}
