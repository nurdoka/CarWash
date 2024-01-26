package com.skilldistillery.carwash.services;

import com.skilldistillery.carwash.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);

}