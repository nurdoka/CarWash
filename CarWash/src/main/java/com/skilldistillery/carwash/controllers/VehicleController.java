package com.skilldistillery.carwash.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	// GET VEHICLES BY USER NAME
	@GetMapping("vehicles")
	public List<Vehicle> returningAllVehiclesByUserIdController(HttpServletRequest req, HttpServletResponse res, Principal principal){
	  return vehicleService.returnAllVehiclesByUser(principal.getName());
	}

	// REGISTER VEHICLE
	@PostMapping("vehicles")
	public Vehicle createNewVehicle(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Vehicle vehicle){
		try {
	        // Save the vehicle
	        vehicleService.registerNewVehicle(vehicle,principal.getName());

	        return vehicle;
	    } catch (Exception e) {
	        res.setStatus(400);
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	
	// UPDATE VEHICLE CONTROLLER, UPDATING SPECIFIC FIELDS BY VEHICLE ID
		@PutMapping(path="vehicles")
		public ResponseEntity<Vehicle> updateVehicle(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Vehicle vehicleToBeUpdated){
			// Step 1: Extract the username
		    String username = principal.getName();
		    
		    // Step 2: Fetch the existing vehicle from the database
	        Vehicle existingVehicle = vehicleService.findById(vehicleToBeUpdated.getId());

	        if (existingVehicle == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        // Step 3: Check if the authenticated user is the owner of the vehicle
	        if (!existingVehicle.getUser().getUsername().equals(username)) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
	        }
	        
//	        //Update the existing vehicle with the new data
	        existingVehicle.setMake(vehicleToBeUpdated.getMake());
	        existingVehicle.setModel(vehicleToBeUpdated.getModel());
	        existingVehicle.setYear(vehicleToBeUpdated.getYear());
	        existingVehicle.setLicensePlate(vehicleToBeUpdated.getLicensePlate());
	        existingVehicle.setColor(vehicleToBeUpdated.getColor());
//
//	        // Save the updated vehicle to the database
	        Vehicle savedVehicle = vehicleService.updateVehicle(existingVehicle);
//
//	        // Return the updated vehicle in the response
	        return new ResponseEntity<>(savedVehicle, HttpStatus.OK);
	    }
	
		
		
		// DELETE VEHICLE CONTROLLER, UPDATING THE VEHICLE TO DEACTIVIATE BY ID
		// THIS IS A PSUDO DELETE
		@DeleteMapping(path="vehicles/{id}")
		public ResponseEntity<Vehicle> deleteVehicle(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable("id") int vehicleId){
			// Step 1: Extract the username
	        String username = principal.getName();
	        
	        // Step 2: delete from the Database
	        Vehicle existingVehicle = vehicleService.findById(vehicleId);

	        if (existingVehicle == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
	        }

	        // Step 3: Perform necessary validation or authorization checks
	        if (!existingVehicle.getUser().getUsername().equals(username)) {
	            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
	        }
	        
	        // Step 5: Final processing step for Deleting the vehicle
	        boolean deleted = vehicleService.deleteVehicle(existingVehicle);

	        if (deleted) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content - meaning data is cleared now
	        } else { 
	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Data no longer Found
	        }
			
			
		}
	
	
}