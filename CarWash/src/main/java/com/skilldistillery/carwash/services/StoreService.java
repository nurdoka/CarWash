package com.skilldistillery.carwash.services;

import java.util.List;
import com.skilldistillery.carwash.entities.Store;

public interface StoreService {
	public Store create(String username, Store store);
    public Store update(String username, int tid, Store store);
    public boolean destroy(String username, int tid);
    
    public Store findStore_ByStoreId(int id);
	public List<Store> findAll();
}
