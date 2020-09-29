package com.web.car98.order.controller;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.web.car98.member.model.MemberBean;
import com.web.car98.order.model.OrderBean;
import com.web.car98.order.model.OrderItemBean;
import com.web.car98.order.model.ShoppingCart;
import com.web.car98.order.service.OrderService;

@Controller
@RequestMapping("comm")
@SessionAttributes({ "LoginOK", "ShoppingCart" })
public class Ordercontroller {
	@Autowired
	ServletContext context;

	@Autowired
	OrderService orderService;

	@PostMapping("ProcessOrder")
	protected String processOrder(Model model, @RequestParam("ShippingAddress") String shippingAddress,
			@RequestParam("BuyName") String buyName, @RequestParam("Email") String email, WebRequest webRequest,
			SessionStatus status) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}

		ShoppingCart sc = (ShoppingCart) model.getAttribute("ShoppingCart");
		if (sc == null) {
			// 處理訂單時如果找不到購物車(通常是Session逾w時)，沒有必要往下執行
			// 導向首頁
			return "redirect:/login";
		}
		String memId = memberBean.getId();// 取出訂購者ID
		double totalAmount = Math.round(sc.getSubtotal() * 1.05);
		Date today = new Date();// 訂單時間
		// 新建訂單物件。OrderBean:封裝一筆訂單資料的容器，包含訂單主檔與訂單明細檔的資料。目前只存放訂單主檔的資料。
		OrderBean ob = new OrderBean(null, memId, shippingAddress, email, today, null, totalAmount, null);

		Map<Integer, OrderItemBean> content = sc.getContent();

		Set<OrderItemBean> items = new LinkedHashSet<>();
		Set<Integer> set = content.keySet();
		for (Integer i : set) {
			OrderItemBean oib = content.get(i);
			oib.setOrderBean(ob);
			items.add(oib);
		}
		ob.setItems(items);
		orderService.persistOrder(ob);
//		try {
//			orderService.persistOrder(ob);
//			return "forward:" + "removeShoppingCart";
//		} catch(RuntimeException ex){
//			String message = ex.getMessage();
//			String shortMsg = "" ;   
//			System.out.println("message=" + message);
//			shortMsg =  message.substring(message.indexOf(":") + 1);
//			System.out.println(shortMsg);
//			model.addAttribute("OrderErrorMessage", "處理訂單時發生異常: " + shortMsg  + "，請調正訂單內容" );
		return "forward:" + "removeOredrShoppingCart";
	}
}
