package com.skilldistillery.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.Store;
import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.repositories.StoreRepository;
import com.skilldistillery.carwash.repositories.UserRepository;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Store> findAll() {
		return storeRepo.findAll();
	}
	
	@Override
	public Store findStore_ByStoreId(int id) {
		return storeRepo.findById(id);
	}

	@Override
	public Store create(String username, Store store) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			store.setUser(user);
			return storeRepo.saveAndFlush(store);
		}
		return null;
	}

	@Override
	public Store update(String username, int tid, Store store) {
		Store existing = storeRepo.findByUser_UsernameAndId(username, tid);
		if (existing != null) {
			existing.setPhone(store.getPhone());
			existing.setEmail(store.getEmail());
			existing.setName(store.getName());
			existing.setImageUrl(store.getImageUrl());
			existing.setDescription(store.getDescription());
			existing.setAddress(store.getAddress());
			
			return storeRepo.saveAndFlush(existing);
		}
		return existing;
	}

	@Override
	public boolean destroy(String username, int tid) {
		boolean deleted = false;
		Store toBeDeleted = storeRepo.findByUser_UsernameAndId(username, tid);
		if (toBeDeleted != null) {
			storeRepo.delete(toBeDeleted);
			deleted = true;
		}
		return deleted;
	}



}
