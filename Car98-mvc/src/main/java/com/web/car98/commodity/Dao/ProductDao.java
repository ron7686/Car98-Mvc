package com.web.car98.commodity.dao;

import java.util.List;

import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.model.BidItemBean;

public interface ProductDao {
	List<BidBean> getAllProducts();
	
	public BidBean getProductById(int bidId);
	
	// 新增
	void addByBidBean(BidBean bb);
	
	BidItemBean getItemById(Integer bidItemId);

	List<BidItemBean> getItemList();
	
	void updateProducts(BidBean bean); 

	void delete(Integer bidId);
}
