package com.skilldistillery.carwash.services;

import java.util.List;

import com.skilldistillery.carwash.entities.Comment;

public interface CommentService {
	public Comment create(Comment comment);
	public List<Comment> findAll();
	Comment findById(int commentId);
	
	
	
}
