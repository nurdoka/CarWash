package com.skilldistillery.carwash.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.carwash.entities.Wash;
import com.skilldistillery.carwash.services.WashService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class WashController {

	@Autowired
	private WashService washService;

	// GET WASH LOGS BY VEHICLE ID
	@GetMapping(path = "vehicle/{id}/wash")
	public ResponseEntity<List<Wash>> returnWashLogsByVehicleController(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable("id") int vehicleId) {
		String username = principal.getName(); // Get the username from Principal

        // Check if the vehicle belongs to the user
        if (!washService.isVehicleBelongsToUser(vehicleId, username)) {
            // If the vehicle doesn't belong to the user, return FORBIDDEN status
        	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Retrieve wash logs based on the vehicleId
        List<Wash> washLogs = washService.returnWashLogsByVehicleService(vehicleId, username);

        // Check if wash logs are empty
        if (washLogs.isEmpty()) {
            // If no wash logs are returned, you can return a different HttpStatus
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // If wash logs are returned, return them with HttpStatus.OK
            return new ResponseEntity<>(washLogs, HttpStatus.OK);
        }

	}

}