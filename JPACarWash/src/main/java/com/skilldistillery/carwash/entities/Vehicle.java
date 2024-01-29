package com.skilldistillery.carwash.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"user", "washes"})
public class Vehicle {
	
	
	//MEMBER FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String make;
	
	private String model;
	
	private Integer year;

	//USER ASSOCIATION (A)
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@Column(name = "license_plate")
	private String licensePlate;

	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;
	
	private boolean enabled;
	
	private String color;

	@OneToMany(mappedBy = "vehicle")
	private List<Wash> washes;
	
	
	
	//CONSTRUCTORS
	
	public Vehicle() {
		super();
	}

	public Vehicle(int id, String make, String model, Integer year, String licensePlate, LocalDateTime createDate,
			LocalDateTime lastUpdate, boolean enabled, String color) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
		this.createDate = createDate;
		this.lastUpdate = lastUpdate;
		this.enabled = enabled;
		this.color = color;
	}
	
	
	//GETTERS AND SETTERS
	
	public List<Wash> getWashes() {
		return washes;
	}

	public void setWashes(List<Wash> washes) {
		this.washes = washes;
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

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	//HASH CODE
	
	
	@Override
	public int hashCode() {
		return Objects.hash(color, createDate, enabled, id, lastUpdate, licensePlate, make, model, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(color, other.color) && Objects.equals(createDate, other.createDate)
				&& enabled == other.enabled && id == other.id && Objects.equals(lastUpdate, other.lastUpdate)
				&& Objects.equals(licensePlate, other.licensePlate) && Objects.equals(make, other.make)
				&& Objects.equals(model, other.model) && Objects.equals(year, other.year);
	}

	
	//TO STRING
	
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year + ", licensePlate="
				+ licensePlate + ", createDate=" + createDate + ", lastUpdate=" + lastUpdate + ", enabled=" + enabled
				+ ", color=" + color + "]";
	}
	
	
	
	
	
	
	
	
	

}
