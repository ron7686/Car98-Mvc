package com.web.car98.order.dao;

import java.sql.Connection;
import java.util.List;

import com.web.car98.order.model.OrderBean;

public interface OrderDao {

	void insertOrder(OrderBean ob);

	void setConnection(Connection con);

	OrderBean getOrder(int orderNo);

	List<OrderBean> getAllOrders();

	List<OrderBean> getMemberOrders(String memId);
}
