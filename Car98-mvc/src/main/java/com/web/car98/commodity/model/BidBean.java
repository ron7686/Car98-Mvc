package com.web.car98.commodity.model;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="bid")
// 本類別封裝單筆商品資料
public class BidBean {
	//商品只能有一個種類，種類可以對應多個商品
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bidId;//商品ID
	
    private Integer memId;
    private String memName;
	
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
	
	@Transient
	BidPicBean bidPicBean;
	
	@OneToMany(mappedBy = "bidBean",orphanRemoval = true , cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	List<BidPicBean> detailList ;
	
	public BidBean() {
	}

	public BidBean(Integer bidId, Integer memId, String memName, String bidItemName, String bidFormat, Integer bidPrice,
			Integer bidStock, Timestamp bidTime, Integer bidScore, Blob bidPic, String fileName,
			BidItemBean bidItemBean, MultipartFile productImage, BidPicBean bidPicBean, List<BidPicBean> detailList) {
		super();
		this.bidId = bidId;
		this.memId = memId;
		this.memName = memName;
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
		this.bidPicBean = bidPicBean;
		this.detailList = detailList;
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

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
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

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public List<BidPicBean> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<BidPicBean> detailList) {
		this.detailList = detailList;
	}

	public BidPicBean getBidPicBean() {
		return bidPicBean;
	}

	public void setBidPicBean(BidPicBean bidPicBean) {
		this.bidPicBean = bidPicBean;
	}

}
	

