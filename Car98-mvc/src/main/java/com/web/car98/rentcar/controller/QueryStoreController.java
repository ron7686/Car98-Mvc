package com.web.car98.rentcar.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.service.RentCarService;

@Controller
public class QueryStoreController{
	String rentCar = "/rent/carRent";
	
	@Autowired 
	RentCarService service;
	
//	@RequestMapping(value = "/carRent", method = RequestMethod.GET) 舊的寫法
	@GetMapping("/carRent")
	public ModelAndView getStoreList(Model model) {
//		public ModelAndView getStoreList(String addr,int priceMin,int priceMax,String carBrand,String carType) {
//		Collection<RentCarBean>  list = service.queryStoreData();
//		model.addAttribute("carRent", list);
//		return rentCar;
		return new ModelAndView("/rent/carRent");  //jsp檔名
	}
}
