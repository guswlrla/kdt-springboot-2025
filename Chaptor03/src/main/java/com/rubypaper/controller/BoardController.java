package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

@RestController // 사용자가 호출하는 url을 처리하는 핸들러 클래스(@Controller)
public class BoardController {
	public BoardController() {
		System.out.println("===> BoardController생성");
	}

	@GetMapping("/hello") // get method 호출을 처리
	public String hello(String name) {
		return "Hello : " + name;
	}
	
	@GetMapping("/getBoard") // @RestContorller는 리턴되는 데이터가 VO 또는 컬렉션이면 자동으로 JSON으로 변환
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목...");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다..............");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
	}
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = new ArrayList<>();
		for(int i = 0; i <= 10; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("제목" + i);
			board.setWriter("테스터");
			board.setContent(i + "번 내용입니다.");
			board.setCreateDate(new Date());
			board.setCnt(0);
			boardList.add(board);
		}
		return boardList;
	}
}
