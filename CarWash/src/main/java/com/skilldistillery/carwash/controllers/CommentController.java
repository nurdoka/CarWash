package com.skilldistillery.carwash.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.carwash.entities.Comment;
import com.skilldistillery.carwash.services.CommentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	

	private String username = "admin";

	// find comment by store id
	@GetMapping("store/comments/{tid}")
	public Set<Comment> showCommentByStoreId(HttpServletRequest req, HttpServletResponse res, @PathVariable("tid") int tid) {
		Set<Comment> todos = commentService.findComment_ByStoreId(tid);
		return todos;
	}

	// find comment by user id
	@GetMapping("user/comments/{tid}")
	public Set<Comment> showCommentByUserId(HttpServletRequest req, HttpServletResponse res, @PathVariable("tid") int tid) {
		Set<Comment> todos = commentService.findComment_ByUserId(tid);
		return todos;
	}

	@GetMapping("show")
	public List<Comment> listPosts(Principal principal, HttpServletResponse res) {
		return commentService.findAll();
	}

	/*@PostMapping("comments")
	public Comment create(HttpServletRequest req, HttpServletResponse res, @RequestBody Comment comment) {
		if (comment == null) {
			res.setStatus(400);
			return null;
		}
		comment = commentService.create(comment);
		return comment;
	}*/
	
	@PostMapping("ok")
	public Comment create(HttpServletRequest req, HttpServletResponse res, @RequestBody Comment comment) {
		try {
			comment = commentService.create(username, comment);
			if (comment == null) {
				res.setStatus(401);
			}
			else {
				res.setStatus(201);
				res.setHeader("Location", req.getRequestURL().append("/").append(comment.getId()).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return comment;
	}
	

}
