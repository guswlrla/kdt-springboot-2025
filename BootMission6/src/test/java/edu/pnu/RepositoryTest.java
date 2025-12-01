package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.persistence.LogRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class RepositoryTest {
	@Autowired
	private LogRepository logRepo;
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	public void insert() {
		
	}
}
