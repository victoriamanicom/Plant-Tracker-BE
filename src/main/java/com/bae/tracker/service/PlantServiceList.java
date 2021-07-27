package com.bae.tracker.service;

import java.util.ArrayList;
import java.util.List;

import com.bae.tracker.data.Plant;

public class PlantServiceList implements PlantService {
	private List<Plant> plants = new ArrayList<>();

	@Override
	public Plant createPlant(Plant plant) {
		System.out.println(plant);
		this.plants.add(plant);
		return plant;
	}

}