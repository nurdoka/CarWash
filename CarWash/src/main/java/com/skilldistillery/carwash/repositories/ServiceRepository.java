package com.skilldistillery.carwash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.carwash.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
	//Service findByName(String name);
}
