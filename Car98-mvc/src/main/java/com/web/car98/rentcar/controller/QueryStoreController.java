package com.web.car98.rentcar.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean2;
import com.web.car98.rentcar.service.CarTypeService;
import com.web.car98.rentcar.service.RentCarService;

@Controller
public class QueryStoreController{
	
	@Autowired
	CarTypeService carTypeService;
	
	@Autowired 
	RentCarService rentCarService;
	String rentCar = "/rent/carRent";
	
	@GetMapping("/carRent")
	public ModelAndView getStoreList(Model model) {
		return new ModelAndView("/rent/carRent");
	}
	
	@GetMapping("/getStoreList")
	@ResponseBody
	protected Collection<RentCarBean2> getStoreList(String city, String district, boolean isHoliday, boolean isWeekday, Integer min, Integer max, String carBrand, String carType) {
		System.out.println(city);
		System.out.println(district);
		System.out.println(isHoliday);
		System.out.println(isWeekday);
		System.out.println(min);
		System.out.println(max);
		System.out.println(carBrand);
		System.out.println(carType);
		System.out.println("======================");
		
		if(city==null) {
			city = "%";
		}
		
		if(district==null) {
			district = "%";
		}
		
		if(carBrand==null) {
			carBrand = "%";
		}
		
		if(carType==null) {
			carType = "%";
		}
		
		System.out.println(city);
		System.out.println(district);
		System.out.println(carBrand);
		System.out.println(carType);
		System.out.println("======================");
		
		if(isHoliday==true && isWeekday==false) {
			Collection<RentCarBean2> storeData = rentCarService.queryStoreHoliday(city, district, isHoliday, isWeekday, min, max, carBrand, carType);
			System.out.println(storeData);
			return storeData;
		}else if(isHoliday==false && isWeekday==true) {
			Collection<RentCarBean2> storeData = rentCarService.queryStoreWeekday(city, district, isHoliday, isWeekday, min, max, carBrand, carType);
			System.out.println(storeData);
			return storeData;
		}else {
			Collection<RentCarBean2> storeData = rentCarService.queryStoreAllday(city, district, isHoliday, isWeekday, min, max, carBrand, carType);
			System.out.println(storeData);
			return storeData;
		}
	}
}
