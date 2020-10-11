package com.web.car98.rentcar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RentCarBean2 implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer rentId;			//建檔編號	
	private String store;			//租車商店
	private String city;			//城市
	private String district;		//區
	private String street;			//街道
	@Id
	private Integer typeId;				//建檔編號
	private String carBrand;			//車牌
	private String carType;				//車型
	private Integer weekdayHourly;		//平日時租
	private Integer holidayHourly;		//假日時租
	private Integer weekdayDaily;		//平日日租
	private Integer holidayDaily;		//假日日租
	
	public Integer getRentId() {
		return rentId;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
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

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getWeekdayHourly() {
		return weekdayHourly;
	}

	public void setWeekdayHourly(Integer weekdayHourly) {
		this.weekdayHourly = weekdayHourly;
	}

	public Integer getWeekdayDaily() {
		return weekdayDaily;
	}

	public void setWeekdayDaily(Integer weekdayDaily) {
		this.weekdayDaily = weekdayDaily;
	}

	public Integer getHolidayHourly() {
		return holidayHourly;
	}

	public void setHolidayHourly(Integer holidayHourly) {
		this.holidayHourly = holidayHourly;
	}

	public Integer getHolidayDaily() {
		return holidayDaily;
	}

	public void setHolidayDaily(Integer holidayDaily) {
		this.holidayDaily = holidayDaily;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

}
