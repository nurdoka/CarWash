package com.skilldistillery.carwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.repositories.AddressRepository;
import com.skilldistillery.carwash.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public User updateUserByUsername(String username,User user) {
		User userInData = userRepo.findByUsername(username);
		if (userInData != null) {
			userInData.setEmail(user.getEmail());
			userInData.setFirstName(user.getFirstName());
			userInData.setLastName(user.getLastName());
			if(user.getAddress() != null) {
				userInData.setAddress(user.getAddress());
				addressRepo.saveAndFlush(userInData.getAddress());
			}
			
			return userRepo.saveAndFlush(userInData);
		}
		return null;
	}

}
