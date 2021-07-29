package com.bae.tracker.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.tracker.data.Plant;

public interface PlantRepo extends JpaRepository<Plant, Integer> {
	Plant findByName(String name);

	List<Plant> findByIsSucculent(boolean isSucculent);
}
