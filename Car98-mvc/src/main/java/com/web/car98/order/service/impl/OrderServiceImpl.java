package com.web.car98.order.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.car98.member.dao.MemberDao;
import com.web.car98.order.dao.OrderDao;
import com.web.car98.order.dao.OrderItemDao;
import com.web.car98.order.model.OrderBean;
import com.web.car98.order.model.OrderItemBean;
import com.web.car98.order.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderItemDao oidao;
	@Autowired
	private OrderDao odao;
	@Autowired
	private MemberDao mdao;

	public OrderServiceImpl() {
		super();
	}

	public OrderDao getOdao() {
		return odao;
	}

	public void setOdao(OrderDao odao) {
		this.odao = odao;
	}

	@Transactional
	@Override
	public void persistOrder(OrderBean ob) {
		// 檢查並更新會員的未付款餘額
//		mdao.updateUnpaidOrderAmount(ob);
		// 檢查每筆訂單明細所訂購之商品的庫存數量是否足夠
		checkStock(ob);
		// 儲存該筆訂單
		odao.insertOrder(ob);
	}

	@Transactional
	@Override
	public OrderBean getOrder(int orderNo) {
		return odao.getOrder(orderNo);
	}
	@Transactional
	@Override
	public List<OrderBean> getAllOrders() {
		return odao.getAllOrders();
	}
	@Transactional
	@Override
	public List<OrderBean> getMemberOrders(String memId) {
		return odao.getMemberOrders(memId);
	}
	
	//商品數量確認
	public void checkStock(OrderBean ob) {
		Set<OrderItemBean> items = ob.getItems();
		for (OrderItemBean oib : items) {
			oidao.updateProductStock(oib);
		}
	}
	@Transactional
	@Override
	public void updateOrder(OrderBean bean) {
		odao.updateOrder(bean);
	}
	@Transactional
	@Override
	public List<OrderItemBean> getAllItems(Integer orderNO) {
		return oidao.getAllItems(orderNO);
	}
	@Transactional
	@Override
	public void delete(Integer orderNo) {
		odao.delete(orderNo);	
	}

}
