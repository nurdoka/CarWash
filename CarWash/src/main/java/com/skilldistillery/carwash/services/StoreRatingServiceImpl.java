package com.skilldistillery.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.StoreRating;
import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.repositories.StoreRatingRepository;
import com.skilldistillery.carwash.repositories.StoreRepository;
import com.skilldistillery.carwash.repositories.UserRepository;

@Service
public class StoreRatingServiceImpl implements StoreRatingService {
	
	@Autowired
	private StoreRatingRepository storeRatingRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private StoreRepository storeRepo;

	@Override
	public StoreRating findByUser_UsernameAndStoreId(String username, int storeI) {
		return storeRatingRepo.findByUser_UsernameAndStoreId(username, storeI);
	}

	@Override
	public List<StoreRating> listByStoreId(int storeId) {
		return storeRatingRepo.findByStoreId(storeId);
	}

	@Override
	public StoreRating addRating(StoreRating storeRating,String username) {
		User user = userRepo.findByUsername(username);
		storeRating.setUser(user);
		storeRating.setStore(storeRepo.findById(storeRating.getId().getStoreId()));
		return storeRatingRepo.saveAndFlush(storeRating);
	}

}
