package com.skilldistillery.carwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.Address;
import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.repositories.AddressRepository;
import com.skilldistillery.carwash.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	

	@Override
	public Address updateAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address findByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			return addressRepo.findByUsers_Username(username);
		}
		return null;
	}

}
