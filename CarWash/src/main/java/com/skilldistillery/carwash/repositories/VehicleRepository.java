package com.skilldistillery.carwash.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.carwash.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	public List<Vehicle> searchByUserId(int id);

//	@Query("SELECT v FROM Vehicle v WHERE v.user.username = :userName")  --ORIGINAL CODE
//	public List<Vehicle> searchByUserName(@Param("userName") String userName);
	
	@Query("SELECT v FROM Vehicle v WHERE v.user.username = :username AND v.user.enabled = true AND v.enabled = true")
    List<Vehicle> findEnabledVehiclesByUsername(@Param("username") String username);


	public List<Vehicle> searchByUser_Username(String userName);
	public Vehicle searchById(int id);
}
