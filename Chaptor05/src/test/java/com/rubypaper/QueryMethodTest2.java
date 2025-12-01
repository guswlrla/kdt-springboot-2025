package com.rubypaper;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class QueryMethodTest2 {
	@Autowired
	private BoardRepository boardRepo;
	
	/*
	@BeforeAll
	static void dataPrepare(@Autowired BoardRepository boardRepo) {
		Random rnd = new Random();
		System.out.println("dataPrepare()");
		for(int i = 1; i <= 100; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 " + i);
			board.setWriter("테스터");
			board.setContent("테스트 내용 " + i);
			board.setCreateDate(new Date());
			board.setCnt(rnd.nextLong(101));
			boardRepo.save(board);
		}
	}
//	@Test
	@Order(1)
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitleContaining("1");
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
//	@Test
	@Order(2)
	public void testFindByTitleContainingAndCntGreaterThan() {
		List<Board> boardList = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50);
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	@Test
	@Order(3)
	public void testFindByCntBetweenOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByCntBetweenOrderBySeqDesc(10, 50);
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	@Test
	@Order(4)
	public void testFindByTitleContainingOrContentContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContainingOrderBySeqDesc("10", "2");
		System.out.println("검색 결과");
		for(Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	*/
}
