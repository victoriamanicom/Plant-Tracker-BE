package com.bae.tracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.tracker.data.Plant;

@Service
public class PlantServiceList implements PlantService {
	private List<Plant> plants = new ArrayList<>();

	@Override
	public Plant createPlant(Plant plant) {
		System.out.println(plant);
		this.plants.add(plant);
		return plant;
	}

	@Override
	public List<Plant> getPlants() {
		for (Plant plant : plants) {
			System.out.println(plant);
		}
		return this.plants;
	}

}