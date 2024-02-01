package com.skilldistillery.carwash.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Service {
	
	
	//MEMBER FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private Double cost;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store; 
	
	@JsonIgnore
	@OneToMany(mappedBy = "service")
	private List<Wash> washes;
	
	//CONSTRUCTORS
	
	
	public Service(int id, String name, String description, String imageUrl, Double cost) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.cost = cost;
	}

	public Service() {
		super();
	}

	
	//GETTERS AND SETTERS
	
	
	public List<Wash> getWashes() {
		return washes;
	}

	public void setWashes(List<Wash> washes) {
		this.washes = washes;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
	//HASH CODE

	@Override
	public int hashCode() {
		return Objects.hash(cost, description, id, imageUrl, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		return Objects.equals(cost, other.cost) && Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl) && Objects.equals(name, other.name);
	}

	
	
	//TO STRING
	
	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", cost=" + cost + "]";
	}
	

	
	

	



	

}
