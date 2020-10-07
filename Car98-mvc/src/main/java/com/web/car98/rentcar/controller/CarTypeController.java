package com.web.car98.rentcar.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.service.CarTypeService;
import com.web.car98.rentcar.service.RentCarService;

@Controller
//@RequestMapping(value = "/rentcar", method = RequestMethod.GET)
public class CarTypeController{

	@Autowired 
	CarTypeService carTypeService;
	
//	@Autowired
//	RentCarService service;
	
//	第三個下拉式選單
//	@RequestMapping(value = "/cartype", method = RequestMethod.GET) 舊的寫法
	@GetMapping("/cartype")
	protected @ResponseBody List<CarTypeBean> doGet(){
		List<CarTypeBean> list = this.carTypeService.showBrandTypeMenu();
		return list;
	}
	
	@PostMapping("/brand")
	protected @ResponseBody List<CarTypeBean> doPost(HttpServletRequest request, 
			@RequestBody CarTypeBean cb, 
			Model model) throws Exception {
		List<CarTypeBean> carTypeBean = this.carTypeService.getRentCarsByType(cb.getCarBrand() , cb.getCarType());
		model.addAttribute("rcbean", carTypeBean);
		
		return carTypeBean;
	}
	
	@PostMapping("/dollar")
	@ResponseBody
	protected List<CarTypeBean> doPost1(HttpServletRequest request, 
			@RequestBody String dollar , Model model) throws Exception {
		int first = dollar.indexOf("=");
		int last = dollar.lastIndexOf("=");
		// 將String 轉成 Integer
		String mindollar = dollar.substring(first+1, first+4);
		Integer min = Integer.parseInt(mindollar);
		
		String maxdollar = dollar.substring(last+1, last+4);
		Integer max = Integer.parseInt(maxdollar);
		
		List<CarTypeBean> carTypeBean = this.carTypeService.getRentCarsByPrice(min, max);
		model.addAttribute("rcbean", carTypeBean);
		
		System.out.println(carTypeBean);
		return carTypeBean;
	}

}
