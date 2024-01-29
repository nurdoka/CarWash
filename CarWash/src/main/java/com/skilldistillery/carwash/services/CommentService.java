package com.skilldistillery.carwash.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.carwash.entities.Comment;

public interface CommentService {
	public Set<Comment> findComment_ByStoreId(int storeId);
	public Set<Comment> findComment_ByUserId(int userId);

	//public Comment create(Comment comment);
    public Comment create(String username, Comment comment);

	public List<Comment> findAll();

	
	
	
}
