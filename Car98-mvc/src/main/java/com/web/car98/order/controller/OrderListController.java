package com.web.car98.order.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.member.model.MemberBean;
import com.web.car98.order.model.OrderBean;
import com.web.car98.order.service.OrderService;

@Controller
@RequestMapping("comm")
@SessionAttributes({ "LoginOK", "products", "ShoppingCart"})
public class OrderListController {

	@Autowired
	ServletContext context;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("orderList")
	protected String orderList(Model model) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}

		List<OrderBean> memberOrders = orderService.getMemberOrders(memberBean.getId());
		model.addAttribute("memberOrders", memberOrders);
		return "comm/orderList";
	}
	
	@GetMapping("orderDetail")
	protected String orderDetail(Model model, 
			@RequestParam("orderNo") Integer no 
			) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}

		OrderBean ob = orderService.getOrder(no);
		model.addAttribute("OrderBean", ob);
		return "comm/ShowOrderDetail";
	}
}
