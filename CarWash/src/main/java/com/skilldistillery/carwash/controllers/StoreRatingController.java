package com.skilldistillery.carwash.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.carwash.entities.StoreRating;
import com.skilldistillery.carwash.services.StoreRatingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class StoreRatingController {

	@Autowired
	private StoreRatingService storeRatingService;
	

	
	@GetMapping("storeRating/{storeId}")
	public StoreRating show(HttpServletRequest req, HttpServletResponse res,Principal principal,@PathVariable("storeId") int storeId) {
		StoreRating storeRating = storeRatingService.findByUser_UsernameAndStoreId(principal.getName(),storeId);
		if (storeRating == null) {
			res.setStatus(404);
		}
		return storeRating;
	}
	
	@GetMapping("storeRating/{storeId}/index")
	public List<StoreRating> index(HttpServletRequest req, HttpServletResponse res,Principal principal,@PathVariable("storeId") int storeId) {
		List<StoreRating> storeRatings = storeRatingService.listByStoreId(storeId);

		return storeRatings;
	}
	
	@PostMapping("storeRating")
	public StoreRating add(HttpServletRequest req, HttpServletResponse res,Principal principal,@RequestBody StoreRating storeRating) {
		return storeRatingService.addRating(storeRating,principal.getName());
	}
	
}