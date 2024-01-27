package com.skilldistillery.carwash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.carwash.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
