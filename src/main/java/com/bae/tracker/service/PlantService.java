package com.bae.tracker.service;

import java.util.List;

import com.bae.tracker.data.Plant;

public interface PlantService {
	public Plant createPlant(Plant plant);

	public List<Plant> getPlants();

	public Plant updatePlant(int id, Plant plant);

	public String deletePlant(int id);

	public Plant getByName(String name);
}