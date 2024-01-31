package com.skilldistillery.carwash.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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
import com.skilldistillery.carwash.entities.Comment;

import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.services.CommentService;
import com.skilldistillery.carwash.services.StoreService;
import com.skilldistillery.carwash.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private UserService userService;

	private String username = "admin";

	// find comment by store id
	@GetMapping("comments/store/{tid}")
	public Set<Comment> showCommentByStoreId(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("tid") int tid) {
		//Set<Comment> todos = commentService.findComment_ByStoreId(tid);
		Set<Comment> comments = commentService.findComment_ByStoreId(tid);

		return comments;
	}

	// find comment by user id
	@GetMapping("comments/user/{tid}")
	public Set<Comment> showCommentByUserId(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("tid") int tid) {
		Set<Comment> comments = commentService.findComment_ByUserId(tid);
		return comments;
	}

	@GetMapping("comments")
	public List<Comment> listPosts(Principal principal, HttpServletResponse res) {
		return commentService.findAll();
	}

	@GetMapping("showUser")
	public List<User> listUsers(Principal principal, HttpServletResponse res) {
		return userService.findAll();
	}

	@PostMapping("comments")
	public Comment create(HttpServletRequest req, HttpServletResponse res, @RequestBody Comment comment) {
		try {
			comment = commentService.create(username, comment);
			if (comment == null) {
				res.setStatus(401);
			} else {
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

	@PutMapping("comments/{tid}")
	public Comment update(HttpServletRequest req, HttpServletResponse res, @PathVariable("tid") int tid,
			@RequestBody Comment comment) {
		Comment updated = null;
		try {
			updated = commentService.update(username, tid, comment);
			if (updated == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}

	@DeleteMapping("comments/{tid}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable("tid") int tid) {
		try {
			if (commentService.destroy(username, tid)) {
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
