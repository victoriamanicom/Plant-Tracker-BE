package com.bae.tracker.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.tracker.data.Plant;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:plant-schema.sql",
		"classpath:plant-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
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
		testCreatedPlant.setId(3);
		String testCreatedPlantAsJSON = this.mapper.writeValueAsString(testCreatedPlant);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedPlantAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete("/deletePlant/1");

		ResultMatcher checkStatus = status().is(204);
		ResultMatcher checkBody = content().string("Plant Deleted: 1");

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdate() throws Exception {
		Plant testPlant = new Plant("Snake Plant", 7, "Variegated", true,
				"https://images.bunches.co.uk/products/large/1806-psans-1.jpg");
		String testPlantAsJSON = this.mapper.writeValueAsString(testPlant);

		RequestBuilder request = put("/updatePlant/1").contentType(MediaType.APPLICATION_JSON).content(testPlantAsJSON);

		Plant testUpdatedPlant = new Plant(1, "Snake Plant", 7, "Variegated", true,
				"https://images.bunches.co.uk/products/large/1806-psans-1.jpg");
		String testUpdatedPlantAsJSON = this.mapper.writeValueAsString(testUpdatedPlant);

		ResultMatcher checkStatus = status().is(202);
		ResultMatcher checkBody = content().json(testUpdatedPlantAsJSON);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGet() throws Exception {
		RequestBuilder request = get("/getPlants");

		// created test version of plant already in DB
		Plant testPlant = new Plant("Calathea Ornata", 9, "Green", false,
				"https://cdn.shopify.com/s/files/1/1802/1289/products/calathea_ornata_530x@2x.jpg?v=1596811596");
		testPlant.setId(1);
		String testPlantAsJSON = this.mapper.writeValueAsString(testPlant);
		Plant testPlant2 = new Plant("Aloe", 3, "Variegated", true,
				"https://cdn.shopify.com/s/files/1/0045/4613/4065/products/aloe1_900x.jpg?v=1620204349");
		testPlant2.setId(2);
		String testPlant2AsJSON = this.mapper.writeValueAsString(testPlant2);

		ResultMatcher checkStatus = status().is(200);
		ResultMatcher checkBody = content().json("[" + testPlantAsJSON + "," + testPlant2AsJSON + "]");

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testGetByName() throws Exception {
		this.mockMVC.perform(get("/getByName/Calathea Ornata")).andExpect(status().is(200))
				.andExpect(content().json(this.mapper.writeValueAsString(new Plant(1, "Calathea Ornata", 9, "Green",
						false,
						"https://cdn.shopify.com/s/files/1/1802/1289/products/calathea_ornata_530x@2x.jpg?v=1596811596"))));
	}
}
