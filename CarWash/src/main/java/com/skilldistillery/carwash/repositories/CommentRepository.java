package com.skilldistillery.carwash.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.carwash.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	Set<Comment> findByUser_Username(String username);
	Comment findByUser_UsernameAndId(String username, int commentId);
	
	//Set<Comment> findComment_ByUserIdAndStoreId(String username, int todoId);
	List<Comment> findByStoreId(int storeId);
	Set<Comment> findComment_ByUserId(int userId);
	
}
