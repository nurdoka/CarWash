package com.skilldistillery.carwash.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PutMapping("user/profile")
	public User updateByUsername(HttpServletRequest req, HttpServletResponse res,Principal principal, @RequestBody User user) {
		User userInData = null;
		try {
			userInData = userService.updateUserByUsername(principal.getName(), user);
			if(userInData == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return userInData;
	}
}