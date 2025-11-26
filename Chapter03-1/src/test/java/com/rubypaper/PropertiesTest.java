package com.rubypaper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(properties = {"author.name=김현지", "author.age=25", "author.nation=Korea"})
public class PropertiesTest {
	@Autowired
	Environment environment;
	
	@Test
	@DisplayName("이름/나이/국가 프로퍼티 출력")
	public void testMethod() {
		System.out.println("이름 : " + environment.getProperty("author.name"));
		System.out.println("나이 : " + environment.getProperty("author.age"));
		System.out.println("국가 : " + environment.getProperty("author.nation"));
	}
	
}
