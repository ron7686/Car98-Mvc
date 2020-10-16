package com.web.car98.searchresource.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gasstation")
public class GasStation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gasNo;
	private String gasstation; // 加油站
	private String city; // 城市
	private String district; // 區
	private String street; // 街道

	public GasStation() {
		super();

	}

	public GasStation(String gasstation, String city, String district, String street) {
		super();
		this.gasstation = gasstation;
		this.city = city;
		this.district = district;
		this.street = street;
	}

	public String getGasstation() {
		return gasstation;
	}

	public void setGasstation(String gasstation) {
		this.gasstation = gasstation;
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
		return "GasStation [gasstation=" + gasstation + ", city=" + city + ", district=" + district + ", street="
				+ street + "]";
	}

}
