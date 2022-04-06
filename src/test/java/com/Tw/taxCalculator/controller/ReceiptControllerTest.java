package com.Tw.taxCalculator.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.Tw.taxCalculator.dto.Item;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ReceiptControllerTest {
	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@BeforeAll
	void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void shouldReturn_200_whenCalledWithCorrectData() throws Exception {
		Item book = new Item("book", 1, Item.Type.BOOK, new BigDecimal(12.49).setScale(2, RoundingMode.HALF_UP), false);
		Item musicCD = new Item("music CD", 1, Item.Type.OTHER, new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP),
				false);
		Item chocolate = new Item("chocolate", 1, Item.Type.FOOD,
				new BigDecimal(0.85).setScale(2, RoundingMode.HALF_UP), false);
		Item[] items = { book, musicCD, chocolate };


		String uri = "/receipt";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapToJson(items))).andReturn();


		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		
		assertTrue(content.contains("$29.83"));
	}
}
