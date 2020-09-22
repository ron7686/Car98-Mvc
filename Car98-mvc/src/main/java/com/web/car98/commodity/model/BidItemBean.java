package com.web.car98.commodity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bidItem")
public class BidItemBean {

	Integer bidItemId;
 	String bidCategory;

	public BidItemBean() {

	}

	@Column(name = "bidItemId", unique = true, nullable = false)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getBidItemId() {
		return bidItemId;
	}

	public void setBidItemId(Integer bidItemId) {
		this.bidItemId = bidItemId;
	}

	public String getBidCategory() {
		return bidCategory;
	}

	public void setBidCategory(String bidCategory) {
		this.bidCategory = bidCategory;
	}

	@Override
	public String toString() {
		return "BidItemBean [bidItemId=" + bidItemId + ", bidCategory=" + bidCategory + "]";
	}
}
