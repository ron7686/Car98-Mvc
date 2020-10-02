package com.web.car98.order.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("comm")
@SessionAttributes({ "ShoppingCart" })
public class RemoveShoppingCartController {
	@Autowired
	ServletContext context;
	@RequestMapping("removeShoppingCart")
	public String removeCart(Model model, WebRequest webRequest, SessionStatus status) {
		status.setComplete();
		webRequest.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
		return "redirect:/comm/products";
	}
	@RequestMapping("removeOredrShoppingCart")
	public String removeOrderCart(Model model, WebRequest webRequest, SessionStatus status) {
		status.setComplete();
		webRequest.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
		return "comm/thanksForOrdering";
	}
}
