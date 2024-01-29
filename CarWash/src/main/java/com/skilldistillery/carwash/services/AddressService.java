package com.skilldistillery.carwash.services;

import com.skilldistillery.carwash.entities.Address;

public interface AddressService {
	
	Address updateAddress(Address address);
	Address findByUsername(String username);

}