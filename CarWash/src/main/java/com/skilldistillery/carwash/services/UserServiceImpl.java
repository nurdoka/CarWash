package com.skilldistillery.carwash.services;

import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepo;

	@Override
	public User updateUserByUsername(String username,User user) {
		User userInData = userRepo.findByUsername(username);
		if (userInData != null) {
			user.setId(userInData.getId());
			return userRepo.saveAndFlush(user);
		}
		return null;
	}

}
