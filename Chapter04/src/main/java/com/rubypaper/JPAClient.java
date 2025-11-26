package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {
	public static void insert() {
		// EntityManagerFactory 생성 - 서버가 돌아가는 동안 한 번 실행되고 삭제됨
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");

		// EntityManager 생성
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
	
	public static void select() {
		// EntityManagerFactory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		// EntityManager 생성
		EntityManager em = emf.createEntityManager();
		try {
			// 글 상세 조회
			Board searchBoard = em.find(Board.class, 1L);
			System.out.println("---> " + searchBoard.toString());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public static void select2() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		try {
			List<Board> list = em.createQuery("select b from Board b", Board.class).getResultList();
			list.stream().forEach(System.out::println);
			
			@SuppressWarnings("unchecked")
			List<Board> list1 = em.createNativeQuery("select * from board", Board.class).getResultList();
			list1.stream().forEach(System.out::println);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public static void main(String[] args) {
		select2();
	}
}
