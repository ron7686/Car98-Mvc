package com.web.car98.order.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bidOrder")
public class OrderBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer orderNo;
	String 	memId;
	String	address; 
	String email;
	Date  buyDay;
	String	cancelTag;
	Double	totalPrice;
	@OneToMany(mappedBy="orderBean", cascade=CascadeType.ALL)
	Set<OrderItemBean> items = new LinkedHashSet<>();

	
	public OrderBean() {
		super();
	}

	public OrderBean(Integer orderNo, String memId, String address, String email, Date buyDay, String cancelTag,
			Double totalPrice, Set<OrderItemBean> items) {
		super();
		this.orderNo = orderNo;
		this.memId = memId;
		this.address = address;
		this.email = email;
		this.buyDay = buyDay;
		this.cancelTag = cancelTag;
		this.totalPrice = totalPrice;
		this.items = items;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public Date getBuyDay() {
		return buyDay;
	}

	public void setBuyDay(Date buyDay) {
		this.buyDay = buyDay;
	}

	public Set<OrderItemBean> getItems() {
		return items;
	}

	public void setItems(Set<OrderItemBean> items) {
		this.items = items;
	}

	public String getCancelTag() {
		return cancelTag;
	}

	public void setCancelTag(String cancelTag) {
		this.cancelTag = cancelTag;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderBean [orderNo=" + orderNo + ", memId=" + memId + ", address=" + address + ", email=" + email
				+ ", buyDay=" + buyDay + ", cancelTag=" + cancelTag + ", totalPrice=" + totalPrice + ", items=" + items
				+ "]";
	}
}
