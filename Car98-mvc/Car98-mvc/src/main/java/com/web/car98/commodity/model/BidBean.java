﻿package com.web.car98.commodity.model;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.web.car98.member.model.MemberBean;


@Entity
@Table(name="bid")
// 本類別封裝單筆商品資料
public class BidBean {
	//商品只能有一個種類，種類可以對應多個商品
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer bidId;//商品ID
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="memId") 	
    private MemberBean memberBean;
	
	private String bidItemName;//品名
	private String	bidFormat;//商品描述
	private Integer bidPrice;//價格
	private Integer bidStock;//庫存
	private Timestamp bidTime;//上架時間
	private Integer bidScore;//買家評價
	private Blob bidPic;//商品照片
	private String fileName;//照片名稱
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bidItemId", nullable = false) 	
	private BidItemBean bidItemBean;

	@Transient
	private MultipartFile productImage;

	
	public BidBean() {
	}

	public BidBean(Integer bidId, MemberBean memberBean, String bidItemName, String bidFormat, Integer bidPrice,
			Integer bidStock, Timestamp bidTime, Integer bidScore, Blob bidPic, String fileName,
			BidItemBean bidItemBean, MultipartFile productImage) {
		super();
		this.bidId = bidId;
		this.memberBean = memberBean;
		this.bidItemName = bidItemName;
		this.bidFormat = bidFormat;
		this.bidPrice = bidPrice;
		this.bidStock = bidStock;
		this.bidTime = bidTime;
		this.bidScore = bidScore;
		this.bidPic = bidPic;
		this.fileName = fileName;
		this.bidItemBean = bidItemBean;
		this.productImage = productImage;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public String getBidItemName() {
		return bidItemName;
	}

	public void setBidItemName(String bidItemName) {
		this.bidItemName = bidItemName;
	}

	public String getBidFormat() {
		return bidFormat;
	}

	public void setBidFormat(String bidFormat) {
		this.bidFormat = bidFormat;
	}

	public Integer getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Integer bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Integer getBidStock() {
		return bidStock;
	}

	public void setBidStock(Integer bidStock) {
		this.bidStock = bidStock;
	}

	public Timestamp getBidTime() {
		return bidTime;
	}

	public void setBidTime(Timestamp bidTime) {
		this.bidTime = bidTime;
	}

	public Integer getBidScore() {
		return bidScore;
	}

	public void setBidScore(Integer bidScore) {
		this.bidScore = bidScore;
	}

	public Blob getBidPic() {
		return bidPic;
	}

	public void setBidPic(Blob bidPic) {
		this.bidPic = bidPic;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BidItemBean getBidItemBean() {
		return bidItemBean;
	}

	public void setBidItemBean(BidItemBean bidItemBean) {
		this.bidItemBean = bidItemBean;
	}

	@Override
	public String toString() {
		return "BidBean [bidId=" + bidId + ", memberBean=" + memberBean + ", bidItemName=" + bidItemName
				+ ", bidFormat=" + bidFormat + ", bidPrice=" + bidPrice + ", bidStock=" + bidStock + ", bidTime="
				+ bidTime + ", bidScore=" + bidScore + ", bidPic=" + bidPic + ", fileName=" + fileName
				+ ", bidItemBean=" + bidItemBean + ", productImage=" + productImage + "]";
	}

}
	

