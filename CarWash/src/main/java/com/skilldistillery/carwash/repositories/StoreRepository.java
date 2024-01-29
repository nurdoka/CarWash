package com.skilldistillery.carwash.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.carwash.entities.Comment;
import com.skilldistillery.carwash.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
	
}