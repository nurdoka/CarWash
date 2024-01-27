package com.skilldistillery.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.Comment;
import com.skilldistillery.carwash.repositories.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	
	@Override
    public Comment findById(int commentId) {
        return commentRepo.findById(commentId).orElse(null);
        // or you can throw an exception if not found
        // return commentRepo.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found with id: " + commentId));
    }
	
	@Override
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }
	
	@Override
	public Comment create(Comment newComment) {
		if (newComment != null) {
			// Save the new comment to the database
			Comment savedComment = commentRepo.save(newComment);
			return savedComment;
		}
        return null;
	}

	

}
