package it.brunasti.hcl.calculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.brunasti.hcl.calculator.logic.SimpleCalculator;
import it.brunasti.hcl.calculator.repository.ResultRepository;
import it.brunasti.hcl.calculator.rest.response.OperationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	ResultRepository resultRepository;

	@BeforeEach
	public void setup() {
		resultRepository.deleteAll();
	}



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
	public void testSubtract() throws Exception {
		OperationResponse operationResponse;

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/subtract?a=1&b=2")
		)
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(-1, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/subtract?a=123&b=0")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(123, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/subtract?a=0&b=0")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(0, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/subtract?a=1&b=-1")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(2, operationResponse.getResult());
	}


	@Test
	public void testMultiply() throws Exception {
		OperationResponse operationResponse;

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/multiply?a=1&b=2")
		)
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(2, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/multiply?a=123&b=0")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(0, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/multiply?a=0&b=0")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(0, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/multiply?a=1&b=-1")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(-1, operationResponse.getResult());
	}


	@Test
	public void testDivide() throws Exception {
		OperationResponse operationResponse;

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/divide?a=1&b=2")
		)
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(0, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/divide?a=0&b=123")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(0, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/divide?a=1&b=1")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(1, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/divide?a=12&b=4")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(3, operationResponse.getResult());

		operationResponse = om.readValue(
				mockMvc.perform(get("/calculator/divide?a=1&b=-1")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), OperationResponse.class);
		assertNotNull(operationResponse);
		assertEquals(-1, operationResponse.getResult());
	}


	@Test
	public void testCallGetMatchError() throws Exception {
		mockMvc.perform(get("/calculator/sqrt"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}




	@Test
	public void testHistory() throws Exception {
		OperationResponse operationResponse;

		mockMvc.perform(get("/calculator/add?a=1&b=2"));
		mockMvc.perform(get("/calculator/add?a=1&b=-1"));
		mockMvc.perform(get("/calculator/subtract?a=1&b=2"));
		mockMvc.perform(get("/calculator/subtract?a=1&b=-1"));
		mockMvc.perform(get("/calculator/multiply?a=1&b=2"));
		mockMvc.perform(get("/calculator/divide?a=1&b=2"));

		List<OperationResponse> list = om.readValue(
				mockMvc.perform(get("/calculator/history")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), ArrayList.class);
		assertNotNull(list);
		assertEquals(6, list.size());

		mockMvc.perform(get("/calculator/divide?a=0&b=123"));
		mockMvc.perform(get("/calculator/divide?a=1&b=1"));
		mockMvc.perform(get("/calculator/divide?a=12&b=4"));

		list = om.readValue(
				mockMvc.perform(get("/calculator/history")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), ArrayList.class);
		assertNotNull(list);
		assertEquals(9, list.size());

		mockMvc.perform(get("/calculator/divide?a=12&b=0"));

		list = om.readValue(
				mockMvc.perform(get("/calculator/history")
				)
						.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), ArrayList.class);
		assertNotNull(list);
		assertEquals(10, list.size());

	}


}
