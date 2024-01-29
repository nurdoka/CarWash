package com.skilldistillery.carwash.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class StoreRatingId implements Serializable{
	
		private static final long serialVersionUID = 1L;
		
		@Column(name = "user_id")
		private int userId;
		
		@Column(name = "store_id")
		private int storeId;
		
		
		//CONSTRUCTORS
		
		public StoreRatingId() {}

		public StoreRatingId(int userId, int storeId) {
			super();
			this.userId = userId;
			this.storeId = storeId;
		}
		
		//GETTERS AND SETTERS

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getStoreId() {
			return storeId;
		}

		public void setStoreId(int storeId) {
			this.storeId = storeId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		
		//HASH CODE
		
		@Override
		public int hashCode() {
			return Objects.hash(storeId, userId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StoreRatingId other = (StoreRatingId) obj;
			return storeId == other.storeId && userId == other.userId;
		}

		
		//TO STRING
		
		@Override
		public String toString() {
			return "StoreRatingId [userId=" + userId + ", storeId=" + storeId + "]";
		}
		
	
	
	


}
