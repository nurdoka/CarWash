package com.skilldistillery.carwash.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.entities.Vehicle;
import com.skilldistillery.carwash.services.VehicleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping("vehicles")
	public List<Vehicle> returningAllVehiclesController(HttpServletRequest req, HttpServletResponse res) {
		List<Vehicle> VehiclesInDatabase = null;
		try {
			VehiclesInDatabase = vehicleService.returnAllVehicles();
			if(VehiclesInDatabase.size() < 1) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return VehiclesInDatabase;
	}
	
	// GET VEHICLES BY USER ID
	@GetMapping(path="vehicles/user/{id}")
	public List<Vehicle> returningAllVehiclesByUserIdController(HttpServletRequest req, HttpServletResponse res, @RequestBody User user){
	  return vehicleService.returnAllVehiclesByUserId(id);
	}
	
	
}