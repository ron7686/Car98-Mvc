package com.web.car98.commodity.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.car98.commodity.dao.ProductDao;
import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.model.BidItemBean;
import com.web.car98.commodity.model.BidPicBean;
import com.web.car98.commodity.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao dao;

	@Transactional
	@Override
	public List<BidBean> getAllProducts() {
		return dao.getAllProducts(); 
	}
	
	@Transactional
	@Override
	public BidBean getProductById(int bidId) {
		return dao.getProductById(bidId);
	}
	
	@Transactional
	@Override
	public void addByBidBean(BidBean bb) {
		dao.addByBidBean(bb);
	}
	@Transactional
	@Override
	public BidItemBean getItemById(Integer bidItemId) {
		return dao.getItemById(bidItemId);
	}
	
	@Transactional
	@Override
	public List<BidItemBean> getItemList() {
		return dao.getItemList();
	}
	
	@Transactional
	@Override
	public void updateProducts(BidBean bean) {
		dao.updateProducts(bean);
	}
	
	@Transactional
	@Override
	public void delete(Integer bidId) {
		dao.delete(bidId);
	}
	

	
	
	@Transactional
	@Override
	public int getTotalPages() {
		return dao.getTotalPages();
	}
	@Transactional
	@Override
	public Map<Integer, BidBean> getPageProducts(int pagePNo) {
		return dao.getPageProducts(pagePNo);
	}
	@Transactional
	@Override
	public List<BidBean> getByIdProducts(int memId) {
		return dao.getByIdProducts(memId);
	}
	@Transactional
	@Override
	public void addPics(BidPicBean pfdBean) {
		dao.getAddPics(pfdBean);
	}
	@Transactional
	@Override
	public List<BidPicBean> getPicByBidId(Integer id) {
		return dao.getPicByBidId(id);
	}
	@Transactional
	@Override
	public BidPicBean getPicByPicId(Integer picId) {
		return dao.getPicByPicId(picId);
	}
}
