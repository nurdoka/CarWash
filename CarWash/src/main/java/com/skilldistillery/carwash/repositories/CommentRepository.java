package com.skilldistillery.carwash.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.carwash.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	Set<Comment> findComment_ByStoreId(int storeId);
	Set<Comment> findComment_ByUserId(int userId);
	
}
