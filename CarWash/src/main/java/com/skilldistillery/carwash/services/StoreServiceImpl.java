package com.skilldistillery.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.Store;
import com.skilldistillery.carwash.repositories.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepo;
	
	@Override
	public List<Store> findAll() {
		return storeRepo.findAll();
	}

}
