package com.web.car98.commodity.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.service.ProductService;
import com.web.car98.member.model.MemberBean;
import com.web.car98.order.model.OrderItemBean;
import com.web.car98.order.model.ShoppingCart;

@Controller
@RequestMapping("comm")
@SessionAttributes({ "LoginOK","ShoppingCart","products","pagePNo"})
public class BuyBidController {
	
	@Autowired
	ServletContext context;
	@Autowired
	ProductService service;
	

	@SuppressWarnings("unchecked")
	@PostMapping("/BuyBid.do")
	protected String buyBid(Model model
			,@RequestParam("bidId")Integer bidId
			,@RequestParam("qty")Integer qty
			, HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
	
		HttpSession session = request.getSession(false); 
		if (session == null) {
			return "redirect:/login";
		}
		
		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart) model.getAttribute("ShoppingCart");
		// 如果找不到ShoppingCart物件
		if (cart == null) {
			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
			// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
			model.addAttribute("ShoppingCart", cart);   
		}
				
//		List<BidBean> list =(List<BidBean>) session.getAttribute("products");
		Map<Integer,BidBean> map =(Map<Integer,BidBean>) session.getAttribute("products");
		BidBean bean = map.get(bidId);
		
		String pagePNo = request.getParameter("pagePNo");
		if (pagePNo == null || pagePNo.trim().length() == 0){
			pagePNo = (String) model.getAttribute("pagePNo") ;
			if (pagePNo == null){
			   pagePNo = "1";
			} 
		} 
		// 將訂單資料(價格，數量與BidBean)封裝到OrderItemBean物件內
		OrderItemBean oib = new  OrderItemBean(null, qty ,bean.getBidPrice(), 
				bidId, bean.getBidItemName(), bean.getMemberBean().getName());
		// 將OrderItem物件內加入ShoppingCart的物件內
		
		cart.addToCart(bidId, oib);
		
		return  "redirect:/comm/products";
	}
}
