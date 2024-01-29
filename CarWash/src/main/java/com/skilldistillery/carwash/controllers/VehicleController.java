package com.skilldistillery.carwash.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.entities.Vehicle;
import com.skilldistillery.carwash.services.UserService;
import com.skilldistillery.carwash.services.VehicleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private UserService userService;
	
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
	
	// GET VEHICLES BY USER NAME
	@GetMapping(path="vehicles/byuser")
	public List<Vehicle> returningAllVehiclesByUserIdController(HttpServletRequest req, HttpServletResponse res, Principal principal){
	  return vehicleService.returnAllVehiclesByUser(principal.getName());
	}

	// REGISTER VEHICLE
	@PostMapping(path="vehicle")
	public Vehicle createNewVehicle(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Vehicle vehicle){
		try {
	        // Fetch the user by username
	        User user = userService.findByUsername(principal.getName());
	        // Associate the user with the vehicle
	        vehicle.setUser(user);

	        // Save the vehicle
	        vehicleService.registerNewVehicle(vehicle);

	        return vehicle;
	    } catch (Exception e) {
	        res.setStatus(400);
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
}