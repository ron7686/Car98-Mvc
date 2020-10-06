package com.web.car98.rentcar.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.service.RentCarService;

@Controller
public class RentCarController {

	@Autowired 
	RentCarService service;
	
//	第一個下拉式選單
	@GetMapping("/area")
	@ResponseBody 
	protected List<RentCarBean> doGet(){
		List<RentCarBean> list = service.showCityDistMenu();
		return list;
	}
	
//	取得符合地區的資料(經過使用者篩選)
//	@ResponseBody
//	public List<RentCarBean> getRentCarsByDist(String city , String district) {
//		List<RentCarBean> rentcarbean = this.service.getRentCarsByDist(city, district);
//		return rentcarbean;
//	}
	@PostMapping("/dist")
	protected @ResponseBody List<RentCarBean> doPost(HttpServletRequest request,
			@RequestBody RentCarBean rb,
			Model model) throws Exception {
		List<RentCarBean> rentcarbean = service.getRentCarsByDist(rb.getCity() ,rb.getDistrict());
		model.addAttribute("rcbean", rentcarbean);
		
		return rentcarbean;
	}
}
