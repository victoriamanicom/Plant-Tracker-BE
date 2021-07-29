package com.bae.tracker.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.tracker.data.Plant;
import com.bae.tracker.data.repos.PlantRepo;

@SpringBootTest
@ActiveProfiles("test")
public class PlantServiceDBUnitTest {
	@Autowired
	private PlantServiceDB service;

	@MockBean
	private PlantRepo repo;

	@Test
	void testUpdate() {
		// GIVEN
		int id = 1;
		Plant testPlant = new Plant(id, "Snake Plant", 7, "Variegated", true,
				"https://images.bunches.co.uk/products/large/1806-psans-1.jpg");
		Plant testNewPlant = new Plant(id, "Chinese Money", 11, "Green", false,
				"https://cdn.shopify.com/s/files/1/0174/7796/products/8D2A1989_2048x2048.jpg?v=1595286173");

		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testPlant));
		Mockito.when(this.repo.save(new Plant(id, "Chinese Money", 11, "Green", false,
				"https://cdn.shopify.com/s/files/1/0174/7796/products/8D2A1989_2048x2048.jpg?v=1595286173")))
				.thenReturn(testNewPlant);

		Plant actual = this.service.updatePlant(id, testNewPlant);
		// THEN
		assertThat(actual).isEqualTo(testNewPlant);

		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findById(1);
		Mockito.verify(this.repo, Mockito.times(1)).save(new Plant(id, "Chinese Money", 11, "Green", false,
				"https://cdn.shopify.com/s/files/1/0174/7796/products/8D2A1989_2048x2048.jpg?v=1595286173"));
	}

	@Test
	void testDeleteSucceeds() {
		// GIVEN
		int id = 1;
		// WHEN
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		// THEN
		assertThat(this.service.deletePlant(id)).isEqualTo("Plant Deleted: " + id);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1);
	}

	@Test
	void testDeleteFails() {
		// GIVEN
		int id = 1;
		// WHEN
		Mockito.when(this.repo.existsById(id)).thenReturn(true);
		// THEN
		assertThat(this.service.deletePlant(id)).isEqualTo("Not deleted: " + id);
		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1);
	}

	@Test
	void testCreate() {
		Plant newPlant = new Plant("Aloe", 3, "Variegated", true,
				"https://cdn.shopify.com/s/files/1/0045/4613/4065/products/aloe1_900x.jpg?v=1620204349");
		Plant savedPlant = new Plant("Aloe", 3, "Variegated", true,
				"https://cdn.shopify.com/s/files/1/0045/4613/4065/products/aloe1_900x.jpg?v=1620204349");

		Mockito.when(this.repo.save(newPlant)).thenReturn(savedPlant);

		assertThat(this.service.createPlant(newPlant)).isEqualTo(savedPlant);

		Mockito.verify(this.repo, Mockito.times(1)).save(newPlant);
	}

	@Test
	void testGetPlants() {
		List<Plant> testPlants = List.of(new Plant("Aloe", 3, "Variegated", true,
				"https://cdn.shopify.com/s/files/1/0045/4613/4065/products/aloe1_900x.jpg?v=1620204349"));

		Mockito.when(this.repo.findAll()).thenReturn(testPlants);

		assertThat(this.service.getPlants()).isEqualTo(testPlants);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testGetByName() {
		Plant testPlant = new Plant("Aloe", 3, "Variegated", true,
				"https://cdn.shopify.com/s/files/1/0045/4613/4065/products/aloe1_900x.jpg?v=1620204349");

		String name = "Aloe";
		Mockito.when(this.repo.findByName(name)).thenReturn(testPlant);

		assertThat(this.service.getByName(name)).isEqualTo(testPlant);

		Mockito.verify(this.repo, Mockito.times(1)).findByName(name);
	}

	@Test
	void testGetSucculent() {
		List<Plant> testPlants = List.of(new Plant("Aloe", 3, "Variegated", true,
				"https://cdn.shopify.com/s/files/1/0045/4613/4065/products/aloe1_900x.jpg?v=1620204349"));

		Mockito.when(this.repo.findByIsSucculent(true)).thenReturn(testPlants);

		assertThat(this.service.getSucculent(true)).isEqualTo(testPlants);

		Mockito.verify(this.repo, Mockito.times(1)).findByIsSucculent(true);
	}

}
