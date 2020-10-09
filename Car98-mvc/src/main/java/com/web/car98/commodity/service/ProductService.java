package com.web.car98.commodity.service;

import java.util.List;
import java.util.Map;

import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.model.BidItemBean;
import com.web.car98.commodity.model.BidPicBean;

public interface ProductService {
	List<BidBean> getAllProducts();

	public BidBean getProductById(int bidId);

	// 新增
	void addByBidBean(BidBean bb);

	BidItemBean getItemById(Integer bidItemId);

	List<BidItemBean> getItemList();
	
	void updateProducts(BidBean bean); 
	
	void delete(Integer bidId);
	
	Map<Integer, BidBean> getPageProducts(int pagePNo);
	
	public int getTotalPages();
	
	List<BidBean> getByIdProducts(int memId);

	void addPics(BidPicBean pfdBean);

	List<BidPicBean> getPicByBidId(Integer id);

	BidPicBean getPicByPicId(Integer picId);
}
