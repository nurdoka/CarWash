package com.skilldistillery.carwash.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "store_rating")
public class StoreRating {

		//MEMBER FIELDS
	
		@EmbeddedId
		private StoreRatingId id;
		
		// BEGIN COMPOSITE KEYS
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "user_id") 
		@MapsId(value = "userId")     
		private User user;
		
		@JsonIgnore
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "store_id") 
		@MapsId(value = "storeId")    
		private Store store;
		
		// END COMPOSITE KEYS
		
		private Integer rating;

		@CreationTimestamp
		@Column(name = "create_date")
		private LocalDateTime createDate;
		
		@Column(name = "rating_comment")
		private String ratingComment;
		
		
		//CONSTRUCTORS

		public StoreRating(StoreRatingId id, User user, Store store, Integer rating, LocalDateTime createDate,
				String ratingComment) {
			super();
			this.id = id;
			this.user = user;
			this.store = store;
			this.rating = rating;
			this.createDate = createDate;
			this.ratingComment = ratingComment;
		}

		public StoreRating() {
			super();
		}
		
		
		//GETTERS AND SETTERS
		

		public StoreRatingId getId() {
			return id;
		}

		public void setId(StoreRatingId id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Store getStore() {
			return store;
		}

		public void setStore(Store store) {
			this.store = store;
		}

		public Integer getRating() {
			return rating;
		}

		public void setRating(Integer rating) {
			this.rating = rating;
		}

		public LocalDateTime getCreateDate() {
			return createDate;
		}

		public void setCreateDate(LocalDateTime createDate) {
			this.createDate = createDate;
		}

		public String getRatingComment() {
			return ratingComment;
		}

		public void setRatingComment(String ratingComment) {
			this.ratingComment = ratingComment;
		}
		
		
		
		//HASH CODE
		

		@Override
		public int hashCode() {
			return Objects.hash(createDate, id, rating, ratingComment, store, user);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StoreRating other = (StoreRating) obj;
			return Objects.equals(createDate, other.createDate) && Objects.equals(id, other.id)
					&& Objects.equals(rating, other.rating) && Objects.equals(ratingComment, other.ratingComment)
					&& Objects.equals(store, other.store) && Objects.equals(user, other.user);
		}

		
		
		//TO STRING
		
		
		@Override
		public String toString() {
			return "StoreRating [id=" + id + ", user=" + user + ", store=" + store + ", rating=" + rating
					+ ", createDate=" + createDate + ", ratingComment=" + ratingComment + "]";
		}
		
	
	
	




}
