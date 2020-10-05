package com.web.car98.conven.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.conven.service.FuelService;
import com.web.car98.member.model.MemberBean;

@Controller
@RequestMapping("/config")
@SessionAttributes("LoginOK")
public class FuelController {
	@Autowired
	ServletContext context;
	
	@Autowired
	FuelService service;
	
	@RequestMapping("/fuels")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
		model.addAttribute("fuel", service.getByMemId(memberBean.getMemId()));
		
		return "config/showFuels";
	}
}
