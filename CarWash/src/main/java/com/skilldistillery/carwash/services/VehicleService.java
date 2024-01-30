package com.skilldistillery.carwash.services;

import java.util.List;

import com.skilldistillery.carwash.entities.Vehicle;

public interface VehicleService {
	public List<Vehicle> returnAllVehicles();
	public List<Vehicle> returnAllVehiclesByUser(String userName);
<<<<<<< HEAD
	public Vehicle registerNewVehicle(Vehicle vehicle);
	public Vehicle findById(int id);
	public Vehicle updateVehicle(Vehicle vehicle);
=======
	public Vehicle registerNewVehicle(Vehicle vehicle,String username);

>>>>>>> 61f1877ac5e58ef37d78cc0a60bdf9e6674b0d5f
}