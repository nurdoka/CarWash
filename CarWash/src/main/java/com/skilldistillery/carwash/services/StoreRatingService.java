package com.skilldistillery.carwash.services;

import java.util.List;

import com.skilldistillery.carwash.entities.StoreRating;

public interface StoreRatingService {
	
	StoreRating findByUser_UsernameAndStoreId(String username,int storeId);
	List<StoreRating> listByStoreId(int storeId);
	StoreRating addRating(StoreRating storeRating,String username);

}