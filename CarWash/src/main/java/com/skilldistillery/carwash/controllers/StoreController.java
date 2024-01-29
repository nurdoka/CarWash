package com.skilldistillery.carwash.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.carwash.entities.Comment;
import com.skilldistillery.carwash.entities.Store;
import com.skilldistillery.carwash.services.StoreService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class StoreController {

	@Autowired
	private StoreService storeService;

	private String username = "admin";

	@GetMapping("stores")
	public List<Store> listStores(Principal principal, HttpServletResponse res) {
		return storeService.findAll();
	}

	/*
	 * @GetMapping("store/{tid}") public Store
	 * showCommentByStoreId(HttpServletRequest req, HttpServletResponse res,
	 * 
	 * @PathVariable("tid") int tid) { Store store =
	 * storeService.findStore_ByStoreId(tid); return store; }
	 */

	@PostMapping("stores")
	public Store create(HttpServletRequest req, HttpServletResponse res, @RequestBody Store store) {
		try {
			store = storeService.create(username, store);
			if (store == null) {
				res.setStatus(401);
			} else {
				res.setStatus(201);
				res.setHeader("Location", req.getRequestURL().append("/").append(store.getId()).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			store = null;
		}
		return store;
	}

	@PutMapping("stores/{tid}")
	public Store update(HttpServletRequest req, HttpServletResponse res, @PathVariable("tid") int tid,
			@RequestBody Store store) {
		Store updated = null;
		try {
			updated = storeService.update(username, tid, store);
			if (updated == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}

	@DeleteMapping("stores/{tid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable("tid") int tid) {
		try {
			if (storeService.destroy(username, tid)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
