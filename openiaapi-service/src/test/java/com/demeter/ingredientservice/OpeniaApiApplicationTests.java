package com.demeter.ingredientservice;


import com.demeter.openiaapi.OpeniaApiApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = OpeniaApiApplication.class)
@Testcontainers
@AutoConfigureMockMvc
class OpeniaApiApplicationTests {
	@Container
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;


	@Test
	void contextLoads() {
	}

}
