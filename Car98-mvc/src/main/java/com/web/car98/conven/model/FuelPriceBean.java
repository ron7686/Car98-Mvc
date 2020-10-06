package com.web.car98.conven.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fuelPrice")
public class FuelPriceBean {
	@Column(name = "typeNo", unique = true, nullable = false)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer typeNo;
	String type;
	Double typePrice;
	
	

	public FuelPriceBean() {
		super();
	}

	public FuelPriceBean(Integer typeNo, String type, Double typePrice) {
		super();
		this.typeNo = typeNo;
		this.type = type;
		this.typePrice = typePrice;
	}

	public Integer getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(Integer typeNo) {
		this.typeNo = typeNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getTypePrice() {
		return typePrice;
	}

	public void setTypePrice(Double typePrice) {
		this.typePrice = typePrice;
	}

	@Override
	public String toString() {
		return "FuelPriceBean [typeNo=" + typeNo + ", type=" + type + ", typePrice=" + typePrice + "]";
	}
	
}
