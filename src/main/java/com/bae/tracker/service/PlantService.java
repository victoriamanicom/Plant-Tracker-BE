package com.bae.tracker.service;

import java.util.List;

import com.bae.tracker.data.Plant;

public interface PlantService {
	public Plant createPlant(Plant plant);

	public List<Plant> getPlants();
}