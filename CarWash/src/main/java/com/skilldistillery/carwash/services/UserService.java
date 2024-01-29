package com.skilldistillery.carwash.services;

import java.util.List;

import com.skilldistillery.carwash.entities.Store;
import com.skilldistillery.carwash.entities.User;

public interface UserService {
	public List<User> findAll();
	User updateUserByUsername(String username,User user);

}