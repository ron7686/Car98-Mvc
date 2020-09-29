package com.web.car98.order.dao.impl;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.order.dao.OrderItemDao;
import com.web.car98.order.model.OrderItemBean;
import com.web.car98.order.ude.ProductStockException;

@Repository
public class OrderItemDaoImpl implements OrderItemDao{

	@Autowired
	SessionFactory factory;
	
	
	public OrderItemDaoImpl() {
		super();
	}
//	計算客戶欲購買之某項商品(以OrderItemBean物件oib來表示)的小計金額(subtotal)，
//	計算公式為: 商品的數量 * 商品的單價

	public double findItemAmount(OrderItemBean oib) {
		double subtotal = oib.getQuantity() * oib.getUnitPrice();
		return subtotal;
	}

	@Override
	public int updateProductStock(OrderItemBean oib) {
		int n = 0;
		Integer stock = 0;
		Session session = factory.getCurrentSession();
		String hql0 = "SELECT bidStock FROM BidBean WHERE bidId = :bidId";
		String hql1 = "UPDATE BidBean SET bidStock = bidStock - :orderAmount WHERE bidId = :bidId";
		stock = (Integer) session.createQuery(hql0)
								 .setParameter("bidId", oib.getBidId())
								 .getSingleResult();
		if (stock == null) {
			stock = 0;
		} 
		int stockLeft = stock - oib.getQuantity();
		if (stockLeft < 0) {
			throw new ProductStockException(
					"庫存數量不足: BidId: " + oib.getBidId() + ", 在庫量: " 
				    + stock + ", 訂購量: " + oib.getQuantity());
		}
		n = session.createQuery(hql1)
				   .setParameter("bidId", oib.getBidId())
				   .setParameter("orderAmount", oib.getQuantity())
				   .executeUpdate();
		return n;
	}

	@Override
	public void setConnection(Connection conn) {
		throw new RuntimeException("本類別未實作此方法");
	}

}
