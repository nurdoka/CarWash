package com.skilldistillery.carwash.services;

import java.util.List;

import com.skilldistillery.carwash.entities.Vehicle;

public interface VehicleService {
	public List<Vehicle> returnAllVehicles();
	public List<Vehicle> returnAllVehiclesByUserId(int id);
	public Vehicle registerVehicle(Vehicle vehicle);

}