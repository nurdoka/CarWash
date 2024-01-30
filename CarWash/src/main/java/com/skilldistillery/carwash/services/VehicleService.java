package com.skilldistillery.carwash.services;

import java.util.List;

import com.skilldistillery.carwash.entities.Vehicle;

public interface VehicleService {
	public List<Vehicle> returnAllVehicles();
	public List<Vehicle> returnAllVehiclesByUser(String userName);
	public Vehicle findById(int id);
	public Vehicle updateVehicle(Vehicle vehicle);
	public Vehicle registerNewVehicle(Vehicle vehicle,String username);

}