package com.skilldistillery.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.entities.Vehicle;
import com.skilldistillery.carwash.repositories.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepo;

	@Autowired
	private UserService userService;
	
	@Override
	public Vehicle registerNewVehicle(Vehicle vehicle,String username) {
        // Fetch the user by username
        User user = userService.findByUsername(username);
        // Associate the user with the vehicle
        vehicle.setUser(user);
        vehicle.setEnabled(true);
		vehicleRepo.saveAndFlush(vehicle);
		return vehicle;
	}


	@Override
	public List<Vehicle> returnAllVehicles() {
		return vehicleRepo.findAll();
	}


	@Override
	public List<Vehicle> returnAllVehiclesByUser(String userName) {
//		List<Vehicle> vehicles = vehicleRepo.searchByUserName(userName); --ORIGINAL CODE
		List<Vehicle> vehicles = vehicleRepo.searchByUser_Username(userName);
		return vehicles;
	}

}
