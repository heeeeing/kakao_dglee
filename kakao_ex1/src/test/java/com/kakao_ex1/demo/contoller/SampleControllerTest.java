package com.kakao_ex1.demo.contoller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class) 
@SpringBootTest 
@AutoConfigureMockMvc
class SampleControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	//@Test
	public void YearAmount() throws Exception {
		mockMvc.perform(get("/test/YearAmount"))
	    .andExpect(status().isOk())
	    .andDo(print());
	}

	//@Test
	public void YearNoTrans() throws Exception {
		mockMvc.perform(get("/test/YearNoTrans"))
		.andExpect(status().isOk())
		.andDo(print());
	}

	//@Test
	public void YearMngAmount() throws Exception {
		mockMvc.perform(get("/test/YearMngAmount"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void ConslidtMng() throws Exception {
		MultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
		paraMap.add("branchCode", "분당점");
		mockMvc.perform(get("/test/ConslidtMng")
		.params(paraMap))
	    .andExpect(status().isOk())
	    .andDo(print());
		 
	}

}
