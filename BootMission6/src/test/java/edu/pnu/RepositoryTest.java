package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.persistence.LogRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class RepositoryTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void insert() {
		for(int i = 1; i <= 10; i++) {
			Member member = new Member();
			member.setId(i);
			member.setPass("테스트 비밀번호" + i);
			member.setName("테스트 이름 " + i);
			member.setRegidate(new Date());
			memberRepo.save(member);
		}
	}
}
