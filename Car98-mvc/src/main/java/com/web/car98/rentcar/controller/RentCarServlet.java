package com.web.car98.rentcar.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.service.RentCarService;

@Controller
public class RentCarServlet {

	@Autowired 
	RentCarService service;
	
//	第一個下拉式選單
	@GetMapping("/dist")
	protected @ResponseBody List<RentCarBean> doGet(){
		List<RentCarBean> list = this.service.showCityDistMenu();
		return list;
	}
	
//	取得符合地區的資料(經過使用者篩選)
	@ResponseBody
	public List<RentCarBean> getRentCarsByDist(String city , String district) {
		List<RentCarBean> rentcarbean  = this.service.getRentCarsByDist(city, district);
		return rentcarbean;
	}
	
	
}
