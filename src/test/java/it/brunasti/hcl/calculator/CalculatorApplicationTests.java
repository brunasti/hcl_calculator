package it.brunasti.hcl.calculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.brunasti.hcl.calculator.rest.response.OperationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorApplicationTests {

	ObjectMapper om = new ObjectMapper();

	@Autowired
	MockMvc mockMvc;


	@Test
	void contextLoads() {
	}


	@Test
	public void testAdd() throws Exception {
		OperationResponse operationResponse;

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/add?a=1&b=2")
		)
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(3, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/add?a=0&b=123")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(123, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/add?a=0&b=0")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(0, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/add?a=1&b=-1")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(0, operationResponse.getResult());
	}


	@Test
	public void testCallGetMatchError() throws Exception {
		mockMvc.perform(get("/calculator/sqrt"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

}
