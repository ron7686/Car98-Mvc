package com.web.car98.conven.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.web.car98.member.model.MemberBean;

@Entity
@Table(name="fuel")
public class Fuel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer fuelId;
	
	private Timestamp time;
	private Integer price;
	private Integer gallon;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="typeNo", nullable = false) 	
	private FuelPriceBean fuelPriceBean;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="memId") 	
    private MemberBean memberBean;

	public Fuel() {
		super();
	}

	public Fuel(Integer fuelId, Timestamp time, Integer price, Integer gallon, FuelPriceBean fuelPriceBean,
			MemberBean memberBean) {
		super();
		this.fuelId = fuelId;
		this.time = time;
		this.price = price;
		this.gallon = gallon;
		this.fuelPriceBean = fuelPriceBean;
		this.memberBean = memberBean;
	}

	public Integer getFuelId() {
		return fuelId;
	}

	public void setFuelId(Integer fuelId) {
		this.fuelId = fuelId;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getGallon() {
		return gallon;
	}

	public void setGallon(Integer gallon) {
		this.gallon = gallon;
	}

	public FuelPriceBean getFuelPriceBean() {
		return fuelPriceBean;
	}

	public void setFuelPriceBean(FuelPriceBean fuelPriceBean) {
		this.fuelPriceBean = fuelPriceBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	} 
	
	
}
