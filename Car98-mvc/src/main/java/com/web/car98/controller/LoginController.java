package com.web.car98.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.model.LoginBean;
import com.web.car98.service.MemberService;

import _00_init.util.GlobalService;

@Controller
@RequestMapping("login")
@SessionAttributes("LoginOK")
public class LoginController {
	String loginForm = "/login/login";
	String loginOut = "/login/logout";
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public String login(
			HttpServletRequest request,Model model,
			@CookieValue(value="user", required = false) String user,
			@CookieValue(value="password", required = false) String password,
			@CookieValue(value="rm", required = false) Boolean rm
			) {
		if(user == null) {
			user = "";
		}
		if(password == null) {
			password = "";
		}else {
			password = GlobalService.decryptString(GlobalService.KEY,password);
		}
		if(rm!=null) {
			rm = Boolean.valueOf(rm);
		}else {
			rm = false;
		}
		LoginBean loginBean = new LoginBean(user,password,rm);
		model.addAttribute(loginBean);
		
		return loginForm;
	}
}
