package com.web.car98.commodity.model;

import java.sql.Blob;
import java.util.List;

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

@Entity
@Table(name="bidPic")
public class BidPicBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer picId; //照片序號(主鍵)
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bidId")
	private BidBean bidBean;
	
	@Transient
	private Integer bidId;//作品集主檔編號 (外鍵)
	private String 	fileName; //照片名稱
	private Blob bidPic; //照片
	
	@Transient
	private List<MultipartFile> picImages ;

	public BidPicBean() {
	}

	public BidPicBean(Integer picId, BidBean bidBean, Integer bidId, String fileName, Blob bidPic,
			List<MultipartFile> picImages) {
		super();
		this.picId = picId;
		this.bidBean = bidBean;
		this.bidId = bidId;
		this.fileName = fileName;
		this.bidPic = bidPic;
		this.picImages = picImages;
	}

	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	public BidBean getBidBean() {
		return bidBean;
	}

	public void setBidBean(BidBean bidBean) {
		this.bidBean = bidBean;
	}

	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Blob getBidPic() {
		return bidPic;
	}

	public void setBidPic(Blob bidPic) {
		this.bidPic = bidPic;
	}

	public List<MultipartFile> getPicImages() {
		return picImages;
	}

	public void setPicImages(List<MultipartFile> picImages) {
		this.picImages = picImages;
	}
	
	
}
