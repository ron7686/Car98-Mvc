package com.web.car98.conven.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="fuel")
public class Fuel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer fuelId;
	
	private Date time;
	private Integer price;
	private Double gallon;
	private Integer mileage;
	private Double consumption;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="typeNo") 	
	private FuelPriceBean fuelPriceBean;
	
    private Integer memId;

	public Fuel() {
		super();
	}

	public Fuel(Integer fuelId, Date time, Integer price, Double gallon, Integer mileage, Double consumption,
			FuelPriceBean fuelPriceBean, Integer memId) {
		super();
		this.fuelId = fuelId;
		this.time = time;
		this.price = price;
		this.gallon = gallon;
		this.mileage = mileage;
		this.consumption = consumption;
		this.fuelPriceBean = fuelPriceBean;
		this.memId = memId;
	}

	public Integer getFuelId() {
		return fuelId;
	}

	public void setFuelId(Integer fuelId) {
		this.fuelId = fuelId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setGallon(Double gallon) {
		this.gallon = gallon;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Double getGallon() {
		return gallon;
	}

	public FuelPriceBean getFuelPriceBean() {
		return fuelPriceBean;
	}

	public void setFuelPriceBean(FuelPriceBean fuelPriceBean) {
		this.fuelPriceBean = fuelPriceBean;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public Double getConsumption() {
		return consumption;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	} 
	
	
}
