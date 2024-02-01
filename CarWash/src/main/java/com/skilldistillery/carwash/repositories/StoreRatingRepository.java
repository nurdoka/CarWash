package com.skilldistillery.carwash.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.carwash.entities.StoreRating;

public interface StoreRatingRepository extends JpaRepository<StoreRating, Integer> {
	
	StoreRating findByUser_UsernameAndStoreId(String username,int storeId);
	List<StoreRating> findByStoreId(int storeId);
	
}
