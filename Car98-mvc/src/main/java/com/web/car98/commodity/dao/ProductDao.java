package com.web.car98.commodity.dao;

import java.util.List;
import java.util.Map;

import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.model.BidItemBean;
import com.web.car98.commodity.model.BidPicBean;

public interface ProductDao {
	List<BidBean> getAllProducts();
	
	List<BidBean> getByIdProducts(int memId);
	
	Map<Integer, BidBean> getPageProducts(int pagePNo);
	
	public BidBean getProductById(int bidId);
	
	// 新增
	void addByBidBean(BidBean bb);
	
	BidItemBean getItemById(Integer bidItemId);

	List<BidItemBean> getItemList();
	
	void updateProducts(BidBean bean); 

	void delete(Integer bidId);
	
	public int getTotalPages();

	long getRecordCounts();

	void getAddPics(BidPicBean pfdBean);

	List<BidPicBean> getPicByBidId(Integer id);

	BidPicBean getPicByPicId(Integer picId);
}
