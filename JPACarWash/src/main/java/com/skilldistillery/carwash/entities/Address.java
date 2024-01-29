package com.skilldistillery.carwash.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Address {

	// MEMBER FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String street;

	private String city;

	private String state;

	private String zip;
<<<<<<< HEAD

	@JsonIgnore
	@OneToMany(mappedBy = "address")
	private List<User> users;

	// MAKING ADDRESS ENTITY AWARE OF STORE ASSOCIATION
=======
	
	@JsonIgnore
	@OneToMany(mappedBy = "address", cascade = CascadeType.MERGE)
	private List <User> users;
	
	//MAKING ADDRESS ENTITY AWARE OF STORE ASSOCIATION
>>>>>>> e65409b1d2b1a6221d408bc2ade9db8859e138be
	@JsonIgnore
	@OneToOne(mappedBy = "address", cascade = CascadeType.MERGE)
	private Store store;

	// CONSTRUCTORS

	public Address(int id, String street, String city, String state, String zip) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Address() {
		super();
	}

	// GETTERS AND SETTERS

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	// HASH CODE

	@Override
	public int hashCode() {
		return Objects.hash(city, id, state, street, zip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && id == other.id && Objects.equals(state, other.state)
				&& Objects.equals(street, other.street) && Objects.equals(zip, other.zip);
	}

	// TO STRING

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ "]";
	}

}
