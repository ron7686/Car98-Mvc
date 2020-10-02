package com.web.car98.searchresource.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parkingstation")
public class ParkingBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String parking;
	private String city; // 城市
	private String district; // 區
	private String street; // 街道

	public ParkingBean() {

	}

	public ParkingBean(String parking, String city, String district, String street) {
		super();
		this.parking = parking;
		this.city = city;
		this.district = district;
		this.street = street;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return "ParkingBean [parking=" + parking + ", city=" + city + ", district=" + district + ", street=" + street
				+ "]";
	}

}
