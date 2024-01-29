package com.skilldistillery.carwash.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Wash {

	// MEMBER FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle; 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createDate;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

	// CONSTRUCTORS

	public Wash(int id, LocalDateTime createDate) {
		super();
		this.id = id;
		this.createDate = createDate;
	}

	public Wash() {
		super();
	}

	// GETTERS AND SETTERS

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	
	// HASH CODE

	@Override
	public int hashCode() {
		return Objects.hash(createDate, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wash other = (Wash) obj;
		return Objects.equals(createDate, other.createDate) && id == other.id;
	}

	// TO STRING
	
	@Override
	public String toString() {
		return "Wash [id=" + id + ", createDate=" + createDate + "]";
	}

}
