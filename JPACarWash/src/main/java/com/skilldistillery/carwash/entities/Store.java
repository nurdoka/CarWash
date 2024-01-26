package com.skilldistillery.carwash.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Store {
	
	
	//MEMBER FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
//	private Integer managerId;   TO RETURN TO THIS FIELD
	@ManyToOne
	@JoinColumn(name="manager_id")
	private User user;
	
	private String phone;
	
	private String email;
	
	private String name;
	
	//MAKING STORE ENTITY AWARE OF ADDRESS ASSOCIATION
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	
	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;
	
	private boolean enabled;
	
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private String description;
	
	@OneToMany(mappedBy = "store")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "store")
	private List<Service> services;

	@OneToMany(mappedBy = "store")
	private List<Wash> washes;
	
	
	

	//CONSTRUCTORS

	
	
	public Store(int id, String phone, String email, String name, LocalDateTime createDate, LocalDateTime lastUpdate,
			boolean enabled, String imageUrl, String description) {
		super();
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.enabled = enabled;
		this.imageUrl = imageUrl;
		this.description = description;
	}

	public Store() {
		super();
	}

	
	//GETTERS AND SETTERS

	public List<Wash> getWashes() {
		return washes;
	}

	public void setWashes(List<Wash> washes) {
		this.washes = washes;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//HASH CODE
	
	@Override
	public int hashCode() {
		return Objects.hash(createDate, description, email, enabled, id, imageUrl, lastUpdate, name, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return Objects.equals(createDate, other.createDate) && Objects.equals(description, other.description)
				&& Objects.equals(email, other.email) && enabled == other.enabled && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl) && Objects.equals(lastUpdate, other.lastUpdate)
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone);
	}

	
	//TO STRING
	@Override
	public String toString() {
		return "Store [id=" + id + ", phone=" + phone + ", email=" + email + ", name=" + name + ", createDate="
				+ createDate + ", lastUpdate=" + lastUpdate + ", enabled=" + enabled + ", imageUrl=" + imageUrl
				+ ", description=" + description + "]";
	}
	
	
	
	
	
	
	
	

	

	
	
	
	
	
	
	

}
