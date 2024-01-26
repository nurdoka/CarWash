package com.skilldistillery.carwash.services;

import com.skilldistillery.carwash.entities.User;

public interface UserService {
	
	User updateUserByUsername(String username,User user);

}