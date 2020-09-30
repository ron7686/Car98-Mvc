package com.web.car98.searchresource.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carwashstation")
public class CarWashBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String carwash; // 洗車
	private String city; // 城市
	private String district; // 區
	private String street; // 街道

	public CarWashBean() {
	}

	public CarWashBean(String carwash, String city, String district, String street) {
		super();
		this.carwash = carwash;
		this.city = city;
		this.district = district;
		this.street = street;
	}
	
	

	public String getCarwash() {
		return carwash;
	}

	public void setCarwash(String carwash) {
		this.carwash = carwash;
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
		return "CarWashBean [carwash=" + carwash + ", city=" + city + ", district=" + district + ", street=" + street
				+ "]";
	}

}
