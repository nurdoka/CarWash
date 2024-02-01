package com.skilldistillery.carwash.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.carwash.entities.Wash;

public interface WashRepository extends JpaRepository<Wash, Integer> {
	@Query("SELECT w FROM Wash w WHERE w.vehicle.id = :vehicleId AND w.vehicle.user.username = :username")
    List<Wash> findByVehicleIdAndUsername(@Param("vehicleId") int vehicleId, @Param("username") String username);

	
}
