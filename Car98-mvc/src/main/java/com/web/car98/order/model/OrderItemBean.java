package com.web.car98.order.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//本類別封裝單筆訂單資料
@Entity
@Table(name="bidOrderItems")
public class OrderItemBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer orderItemId;
	Integer quantity;
	Integer unitPrice;
	Integer bidId;
	private String bidName;
	private String sellMan;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="orderId") 	
	private OrderBean orderBean;
	
	public OrderItemBean() {
		super();
	}

	public OrderItemBean(Integer orderItemId, Integer quantity, Integer unitPrice, Integer bidId, String bidName,
			String sellMan, OrderBean orderBean) {
		super();
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.bidId = bidId;
		this.bidName = bidName;
		this.sellMan = sellMan;
		this.orderBean = orderBean;
	}



	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public String getBidName() {
		return bidName;
	}

	public void setBidName(String bidName) {
		this.bidName = bidName;
	}

	public String getSellMan() {
		return sellMan;
	}

	public void setSellMan(String sellMan) {
		this.sellMan = sellMan;
	}

	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}

	@Override
	public String toString() {
		return "OrderItemBean [orderItemId=" + orderItemId + ", quantity=" + quantity + ", unitPrice=" + unitPrice
				+ ", bidId=" + bidId + ", bidName=" + bidName + ", sellMan=" + sellMan + ", orderBean=" + orderBean
				+ "]";
	}
}