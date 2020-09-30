package com.web.car98.order.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.car98.commodity.model.BidBean;
import com.web.car98.member.model.MemberBean;
import com.web.car98.order.model.ShoppingCart;

@Controller
@RequestMapping("comm")
@SessionAttributes({ "LoginOK", "ShoppingCart","porducts"})
public class ShoppingCartController {
	@Autowired
	ServletContext context;
	private final static String SHOW_CART_CONTENT = "comm/showCartContent"; 
	@GetMapping("showCartContent")
	protected String showCartContent(Model model, SessionStatus status) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			status.setComplete();
			return "redirect:/login";
		}
		BidBean bb = (BidBean) model.getAttribute("porducts");
		return  "comm/showCartContent";
	}
	
	@GetMapping("checkout")
	protected String checkout(Model model, SessionStatus status) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			status.setComplete();
			return "redirect:/login";
		}
		return	"comm/shopCheckout";
	}
	@PostMapping("UpdateItem.do")
	protected String UpdateItem(
			@RequestParam("cmd")   String cmd,  
			@RequestParam(value = "bidId", required = false) Integer  bidId, 
			@RequestParam(value = "newQty", required = false) Integer  newQty, 
			Model model, 
			RedirectAttributes ra, 
			HttpSession session, SessionStatus status
			) {
		ShoppingCart sc = (ShoppingCart) model.getAttribute("ShoppingCart");
		if (sc == null) {
			status.setComplete();
			return "redirect:/login";
		}
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");

		if (memberBean == null) {
			status.setComplete();
			return "redirect:/login";
		}
		if (cmd.equalsIgnoreCase("DEL")) {
	        sc.deleteBid(bidId); // 刪除購物車內的某項商品
		    return SHOW_CART_CONTENT;
		} else if (cmd.equalsIgnoreCase("MOD")) {
			sc.modifyQty(bidId, newQty);   // 修改某項商品的數項
		    return SHOW_CART_CONTENT;
		} else {
			return SHOW_CART_CONTENT;
		}

	}
}
