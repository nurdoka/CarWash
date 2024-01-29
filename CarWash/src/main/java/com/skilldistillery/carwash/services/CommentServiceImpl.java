package com.skilldistillery.carwash.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.carwash.entities.Comment;
import com.skilldistillery.carwash.entities.User;
import com.skilldistillery.carwash.repositories.CommentRepository;
import com.skilldistillery.carwash.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Set<Comment> findComment_ByStoreId(int storeId) {
		return commentRepo.findComment_ByStoreId(storeId);
	}

	@Override
	public Set<Comment> findComment_ByUserId(int userId) {
		return commentRepo.findComment_ByUserId(userId);
	}

	@Override
	public List<Comment> findAll() {
		return commentRepo.findAll();
	}

	@Override
	public Comment create(String username, Comment comment) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			comment.setUser(user);
			return commentRepo.saveAndFlush(comment);
		}
		return null;
	}

	@Override
	public Comment update(String username, int tid, Comment comment) {
		Comment existing = commentRepo.findByUser_UsernameAndId(username, tid);
		if (existing != null) {
			existing.setContent(comment.getContent());
			existing.setCommentDate(comment.getCommentDate());
			return commentRepo.saveAndFlush(existing);
		}
		return existing;
	}

	@Override
	public boolean destroy(String username, int tid) {
		boolean deleted = false;
		Comment toBeDeleted = commentRepo.findByUser_UsernameAndId(username, tid);
		if (toBeDeleted != null) {
			commentRepo.delete(toBeDeleted);
			deleted = true;
		}
		return deleted;
	}

}
