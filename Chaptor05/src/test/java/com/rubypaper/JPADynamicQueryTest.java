package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@SpringBootTest
public class JPADynamicQueryTest {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void testDynamicQuery() {
		String searchCondition = "title";
		String searchKeyword = "테스트 제목 10";
		
		// 질의문 생성
		StringBuilder sb = new StringBuilder("select b from Board b where 1=1");
		if(searchCondition.equals("title"))
			sb.append(" and b.title like '%" + searchKeyword + "%'");
		else if(searchCondition.equals("content"))
			sb.append(" and b.content like '%" + searchKeyword + "%'");
		sb.append(" order by b.seq asc");
		
		// 질의 객체 생성
		TypedQuery<Board> query = entityManager.createQuery(sb.toString(), Board.class);
		
		// 페이징 설정
		query.setFirstResult(0); // 시작위치: (pageNum-1)*pageSize
		query.setMaxResults(5); // 페이지사이즈: pageSize
		
		// 데이터 베이스 연결 및 질의 실행
		List<Board> list = query.getResultList();
		
		// 검색 결과 출력
		for(Board b : list) {
			System.out.println(b);
		}
	}
}
