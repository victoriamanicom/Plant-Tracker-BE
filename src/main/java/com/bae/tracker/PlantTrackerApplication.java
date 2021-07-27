package com.bae.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.bae.tracker.rest.PlantController;
import com.bae.tracker.service.PlantService;

@SpringBootApplication
public class PlantTrackerApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(PlantTrackerApplication.class, args);
		PlantController controller = beanBag.getBean(PlantController.class);
		System.out.println("Controller:" + controller);
		PlantService service = beanBag.getBean(PlantService.class);
		System.out.println("Service:" + service);
	}

}
