package com.skilldistillery.carwash.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.carwash.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
}
