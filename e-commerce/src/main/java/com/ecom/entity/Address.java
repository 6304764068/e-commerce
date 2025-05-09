package com.ecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	private String id;
	private String city;
	private String village;
	private int pincode;
	private String street;
	private String landmark;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", village=" + village + ", pincode=" + pincode + ", street="
				+ street + ", landmark=" + landmark + "]";
	}
	
}
