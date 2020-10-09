package com.web.car98.commodity.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.commodity.dao.ProductDao;
import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.model.BidItemBean;
import com.web.car98.commodity.model.BidPicBean;

import _00_init.util.GlobalService;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	SessionFactory factory;

	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 預設值：每頁8筆
	private int totalPages = -1;
	
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
		if (bean != null && bean.getBidId() != null) {
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

	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));

		return totalPages;
	}
	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String hql = "SELECT count(*) FROM BidBean";
		Session session = factory.getCurrentSession();
		count = (Long)session.createQuery(hql).getSingleResult();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, BidBean> getPageProducts(int pagePNo) {
		Map<Integer, BidBean> map = new LinkedHashMap<>();
		String hql = "FROM BidBean";
        Session session = factory.getCurrentSession();
        int startRecordNo = (pagePNo - 1) * recordsPerPage;
        List<BidBean> list = session.createQuery(hql)
                      .setFirstResult(startRecordNo)
                      .setMaxResults(recordsPerPage)
                      .getResultList();
		for(BidBean bean: list) {
			map.put(bean.getBidId(), bean);
		}
		return map;

	}

	//只有上架商品的memId可做修改
	@SuppressWarnings("unchecked")
	@Override
	public List<BidBean> getByIdProducts(int memId) {
		List<BidBean> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM BidBean ob WHERE ob.memId = :mid";
		list = session.createQuery(hql).setParameter("mid", memId).getResultList();
		return list;
	}

	@Override
	public void getAddPics(BidPicBean pfdBean) {
		Session session = factory.getCurrentSession();
		BidBean bidBean = session.get(BidBean.class,pfdBean.getBidId());
		pfdBean.setBidBean(bidBean);		
		session.save(pfdBean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BidPicBean> getPicByBidId(Integer id) {
		Session session = factory.getCurrentSession();
		List<BidPicBean> list = null;	
		String hql = "FROM BidPicBean WHERE bidId = :bidId";
		list = session.createQuery(hql)
				.setParameter("bidId", id)
				.getResultList();
		return list;
	}

	@Override
	public BidPicBean getPicByPicId(Integer picId) {
		return factory.getCurrentSession().get(BidPicBean.class, picId);
	}
}
