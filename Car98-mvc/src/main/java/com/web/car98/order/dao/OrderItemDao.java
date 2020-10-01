package com.web.car98.order.dao;

import java.sql.Connection;
import java.util.List;

import com.web.car98.order.model.OrderItemBean;

public interface OrderItemDao {
	
	// 由 OrderItemBean取得商品價格(eBook#Price)。
		
	double findItemAmount(OrderItemBean oib);

	int updateProductStock(OrderItemBean ob);
	
	void setConnection(Connection conn);
	
	List<OrderItemBean> getAllItems(Integer orderNO);
}
