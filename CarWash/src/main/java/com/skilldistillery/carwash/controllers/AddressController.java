package com.skilldistillery.carwash.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.carwash.entities.Address;
import com.skilldistillery.carwash.services.AddressService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping("address")
	public Address show(HttpServletRequest req, HttpServletResponse res,Principal principal) {
		Address address = addressService.findByUsername(principal.getName());
		if (address == null) {
			res.setStatus(404);
		}
		return address;
	}
	
}