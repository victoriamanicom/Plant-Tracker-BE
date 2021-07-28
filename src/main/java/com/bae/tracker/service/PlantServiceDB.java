package com.bae.tracker.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bae.tracker.data.Plant;
import com.bae.tracker.data.repos.PlantRepo;

@Service
@Primary
public class PlantServiceDB implements PlantService {

	private PlantRepo repo;

	public PlantServiceDB(PlantRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Plant createPlant(Plant plant) {
		return this.repo.save(plant);
	}

	@Override
	public List<Plant> getPlants() {
		return this.repo.findAll();
	}

	@Override
	public Plant getByName(String name) {
		return this.repo.findByName(name);
	}

	@Override
	public String deletePlant(int id) {
		this.repo.deleteById(id);

		if (this.repo.existsById(id)) {
			return "Not deleted: " + id;
		} else {
			return "Plant Deleted: " + id;
		}

	}

	@Override
	public Plant updatePlant(int id, Plant plant) {
		Plant found = this.repo.findById(id).get();

		found.setLeafColour(plant.getLeafColour());
		found.setName(plant.getName());
		found.setPotSize(plant.getPotSize());
		found.setIsSucculent(plant.getIsSucculent());
		found.setImgUrl(plant.getImgUrl());

		Plant updated = this.repo.save(found);
		return updated;
	}

}
