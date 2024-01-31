package com.skilldistillery.carwash.services;

import com.skilldistillery.carwash.entities.Service;
import com.skilldistillery.carwash.entities.Store;

public interface ServiceService {

	public Service create(String name, Service service);
	//public Service create(Store store, Service service);
    public Service update(String username, int tid, Service service);
    public boolean destroy(String username, int tid);
    
}
