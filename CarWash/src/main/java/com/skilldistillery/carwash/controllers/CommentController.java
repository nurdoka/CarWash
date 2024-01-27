package com.skilldistillery.carwash.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.carwash.entities.Comment;
import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.services.CommentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("test")
	public void test() {
		System.out.println("test");
	}
	
	@GetMapping("show")
	public List<Comment> listPosts(Principal principal, HttpServletResponse res){
		return commentService.findAll();
	}

	@PostMapping("comments")
	public Comment create(HttpServletRequest req, HttpServletResponse res,
			@RequestBody Comment comment) {
		if (comment == null) {
			res.setStatus(400);
			return null;
		}
		comment = commentService.create(comment);
		return comment;
	}

}
