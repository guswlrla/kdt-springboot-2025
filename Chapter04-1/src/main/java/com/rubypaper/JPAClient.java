package com.rubypaper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

/**
 * Hello world!
 *
 */
public class JPAClient {
    public static void main( String[] args ) {

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04-1");
    	EntityManager em = emf.createEntityManager();
    	
    	// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			// DB에 저장할 객체 생성
			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			// 글 등록
			em.persist(board);
			// Transaction commit
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Transaction rollback
			tx.rollback();
		} finally {
			// 사용한 리소스 객체 닫기
			em.close();
			emf.close();
		}
    }
}
