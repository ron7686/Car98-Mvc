package com.web.car98.commodity.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.commodity.dao.ProductDao;
import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.model.BidItemBean;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<BidBean> getAllProducts() {
		String hql = "FROM BidBean";
		Session session = null;
		List<BidBean> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	
	@Override
	public BidBean getProductById(int bidId) {
		return factory.getCurrentSession().get(BidBean.class, bidId);
	}

	@Override
	public void addByBidBean(BidBean bb) {
		Session session = factory.getCurrentSession();
		BidItemBean bib = getItemById(bb.getBidItemBean().getBidItemId());
		bb.setBidItemBean(bib);
		session.save(bb);
	}

	@Override
	public BidItemBean getItemById(Integer bidItemId) {
		Session session = factory.getCurrentSession();	 
		return session.get(BidItemBean.class, bidItemId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BidItemBean> getItemList() {
		String hql = "FROM BidItemBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public void updateProducts(BidBean bean) {
		if (bean != null && bean.getBidId() != null) 	{
			Session session = factory.getCurrentSession();
			BidItemBean bib = getItemById(bean.getBidItemBean().getBidItemId());
			bean.setBidItemBean(bib);
			session.saveOrUpdate(bean);
		}
	}

	@Override
	public void delete(Integer bidId) {
		Session session = factory.getCurrentSession();
		BidBean bid = session.get(BidBean.class, bidId);
		if (bid != null) {
			bid.setBidItemBean(null);
			session.delete(bid);
		}
	}
}
