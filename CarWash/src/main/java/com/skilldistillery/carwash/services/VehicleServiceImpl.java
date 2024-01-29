package com.skilldistillery.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.Vehicle;
import com.skilldistillery.carwash.repositories.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepo;

	
	@Override
	public Vehicle registerVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Vehicle> returnAllVehicles() {
		return vehicleRepo.findAll();
	}


	@Override
	public List<Vehicle> returnAllVehiclesByUserId(int id) {
		List<Vehicle> vehicles = vehicleRepo.searchByUserId(id);
		return vehicles;
	}

}
