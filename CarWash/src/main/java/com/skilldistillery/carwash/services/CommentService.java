package com.skilldistillery.carwash.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.carwash.entities.Comment;

public interface CommentService {
	public List<Comment> findComment_ByStoreId(int storeId);
	public Set<Comment> findComment_ByUserId(int userId);

    public Comment create(String username, Comment comment, int id);
    public Comment update(String username, int tid, Comment comment);
    public boolean destroy(String username, int tid);

	public List<Comment> findAll();
}
