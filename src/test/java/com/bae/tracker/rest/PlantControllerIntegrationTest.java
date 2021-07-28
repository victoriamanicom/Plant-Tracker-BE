package com.bae.tracker.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.tracker.data.Plant;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PlantControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Plant testPlant = new Plant("Pothos", 12, "Variegated", false,
				"http://cdn.shopify.com/s/files/1/0056/8970/4482/products/B_D_Day-2-013web.jpg?v=1607519442");
		String testPlantAsJSON = this.mapper.writeValueAsString(testPlant);
		RequestBuilder request = post("/createPlant").contentType(MediaType.APPLICATION_JSON).content(testPlantAsJSON);

		Plant testCreatedPlant = new Plant("Pothos", 12, "Variegated", false,
				"http://cdn.shopify.com/s/files/1/0056/8970/4482/products/B_D_Day-2-013web.jpg?v=1607519442");
		testCreatedPlant.setId(1);
		String testCreatedPlantAsJSON = this.mapper.writeValueAsString(testCreatedPlant);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedPlantAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
}
