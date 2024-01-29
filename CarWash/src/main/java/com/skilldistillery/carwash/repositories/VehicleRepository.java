package com.skilldistillery.carwash.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.carwash.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	public List<Vehicle> searchByUserId(int id);
}
