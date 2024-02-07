package com.skilldistillery.carwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.repositories.AddressRepository;
import com.skilldistillery.carwash.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public User register(User user) {
		user.setEnabled(true);
		if (user.getRole() == null || user.getRole().equals("")) {
			user.setRole("CUSTOMER");
		}
		String encryptedPassword = encoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		if(user.getAddress() != null) {
			addressRepo.saveAndFlush(user.getAddress());
		}
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}


}