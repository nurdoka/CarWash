package com.skilldistillery.carwash.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.carwash.entities.Service;
import com.skilldistillery.carwash.entities.Store;
import com.skilldistillery.carwash.repositories.ServiceRepository;
import com.skilldistillery.carwash.repositories.StoreRepository;
import com.skilldistillery.carwash.repositories.UserRepository;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceRepository serviceRepo;
	
	@Autowired
	private StoreRepository storeRepo;
	
	@Override
	public Service create(String name, Service service) {
		Store store = null;//storeRepo.findByName(name);
		if (store != null) {
			service.setStore(store);
			return serviceRepo.saveAndFlush(service);
		}
		return null;
	}

	@Override
	public Service update(String username, int tid, Service service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroy(String username, int tid) {
		// TODO Auto-generated method stub
		return false;
	}

}
