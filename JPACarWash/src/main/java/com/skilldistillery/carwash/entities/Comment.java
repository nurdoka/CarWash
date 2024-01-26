package com.skilldistillery.carwash.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	// MEMBER FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String content;

	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;

//	@Column(name = "user_id")
//	private User user;             COME BACK AND FIX ME

	@CreationTimestamp
	@Column(name = "comment_date")
	private LocalDateTime commentDate;

	// CONSTRUCTORS

	public Comment(int id, String content, LocalDateTime commentDate) {
		super();
		this.id = id;
		this.content = content;
		this.commentDate = commentDate;
	}

	public Comment() {
		super();
	}

//GETTERS AND SETTERS

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}

//HASH CODE

	@Override
	public int hashCode() {
		return Objects.hash(commentDate, content, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(commentDate, other.commentDate) && Objects.equals(content, other.content)
				&& id == other.id;
	}

//TO STRING

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", commentDate=" + commentDate + "]";
	}

}
