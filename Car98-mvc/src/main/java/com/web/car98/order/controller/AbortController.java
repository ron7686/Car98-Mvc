package com.web.car98.order.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("comm")
@SessionAttributes({"ShoppingCart"})
public class AbortController {
	@Autowired
	ServletContext context;
//	 執行『結帳』功能時，選擇『放棄購物』
	@GetMapping("abort")
	protected String abort(HttpSession session, Model model, WebRequest webRequest, SessionStatus status)  {
		status.setComplete();    // 移除所有被@SessionAttributes({"ShoppingCart"})標示的物件
//		webRequest.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
		return  "redirect:/comm/products";
	}
}
