package com.bae.tracker.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.tracker.data.Plant;
import com.bae.tracker.service.PlantService;

@RestController
@CrossOrigin
public class PlantController {
	private PlantService service;

	public PlantController(PlantService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createPlant")
	public ResponseEntity<Plant> createPlant(@RequestBody Plant plant) {
		Plant created = this.service.createPlant(plant);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/getPlants")
	public List<Plant> getPlants() {
		return this.service.getPlants();
	}

	@PutMapping("/updatePlant/{id}")
	public ResponseEntity<Plant> updatePlant(@PathVariable int id, @RequestBody Plant plant) {
		Plant body = this.service.updatePlant(id, plant);
		return new ResponseEntity<Plant>(body, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deletePlant/{id}")
	public ResponseEntity<String> deletePlant(@PathVariable int id) {
		String body = this.service.deletePlant(id);
		return new ResponseEntity<String>(body, HttpStatus.NO_CONTENT);

	}

	@GetMapping("/getByName/{name}")
	public Plant getByName(@PathVariable String name) {
		return this.service.getByName(name);
	}

}