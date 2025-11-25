package com.rubypaper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

//@WebMvcTest
//@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
//@AutoConfigureMockMvc
//@TestMethodOrder(OrderAnnotation.class) // 메서드에 순서 부여
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {
	@Autowired
//	private MockMvc mockMvc;
	private TestRestTemplate restTemplate;
	
//	@Test
//	@Order(2) // 두번째
//	public void testHello() throws Exception {
//		mockMvc.perform(get("/hello").param("name", "둘리"))
//		.andExpect(status().isOk())
//		.andExpect(content().string("Hello : 둘리"))
//		.andDo(print());
//	}
	
//	@Test
//	@Order(1) // 첫번째
//	public void testHelloJson() throws Exception {
//		ObjectMapper objectMapper = new ObjectMapper();
//		MvcResult mvcResult = mockMvc.perform(get("/getBoard").param("seq", "1"))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$.writer").value("테스터"))
//				.andDo(print())
//				.andReturn();
//		String jsonString = mvcResult.getResponse().getContentAsString();
//		BoardVO board = objectMapper.readValue(jsonString, BoardVO.class);
//		assertEquals(board.getSeq(), 1);
//	}
	
	@Test
	public void testHello() {
		String result = restTemplate.getForObject("/hello?name=둘리", String.class);
		assertEquals("Hello : 둘리", result);
	}
}
