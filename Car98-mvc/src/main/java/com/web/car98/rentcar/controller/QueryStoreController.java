package com.web.car98.rentcar.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.member.model.MemberBean;
import com.web.car98.rentcar.model.RentCarBean2;
import com.web.car98.rentcar.service.CarTypeService;
import com.web.car98.rentcar.service.RentCarService;

@Controller
@SessionAttributes({"LoginOK"})
public class QueryStoreController{
	
	@Autowired
	CarTypeService carTypeService;
	
	@Autowired 
	RentCarService rentCarService;
	
	String rentCar = "/rent/carRent";
	
	@GetMapping("/carRent")
	public String getStoreList(Model model) {
		MemberBean memberBean=(MemberBean) model.getAttribute("LoginOK");
		if(memberBean==null) {
			return "redirect:/login";
		}
		return "/rent/carRent";
	}
	
	@PostMapping("/getStoreList")
	@ResponseBody
	protected Collection<RentCarBean2> getStoreList(String city, String district, 
			boolean isHolihour, boolean isWeekhour, boolean isHoliday, boolean isWeekday, 
			Integer min, Integer max, String carBrand, String carType) {
//		System.out.println(city);
//		System.out.println(district);
//		System.out.println(isHolihour);
//		System.out.println(isWeekhour);
//		System.out.println(isHoliday);
//		System.out.println(isWeekday);
//		System.out.println(min);
//		System.out.println(max);
//		System.out.println(carBrand);
//		System.out.println(carType);
//		System.out.println("======================");
		
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
		
//		System.out.println(city);
//		System.out.println(district);
//		System.out.println(carBrand);
//		System.out.println(carType);
//		System.out.println("======================");
		
		
		if (isHolihour==false && isWeekhour==true) {
			Collection<RentCarBean2> storeData = rentCarService.queryStoreWeekhour(city, district, isHolihour, isWeekhour, 
					isHoliday, isWeekday, min, max, carBrand, carType);
//			System.out.println(storeData);
			return storeData;
		} else if (isHolihour==true && isWeekhour==false) {
			Collection<RentCarBean2> storeData = rentCarService.queryStoreHolihour(city, district, isHolihour, isWeekhour, 
					isHoliday, isWeekday, min, max, carBrand, carType);
//			System.out.println(storeData);
			return storeData;
		} else if (isHoliday==false && isWeekday==true) {
			Collection<RentCarBean2> storeData = rentCarService.queryStoreWeekday(city, district, isHolihour, isWeekhour, 
					isHoliday, isWeekday, min, max, carBrand, carType);
//			System.out.println(storeData);
			return storeData;
		} else if (isHoliday==true && isWeekday==false) {
			Collection<RentCarBean2> storeData = rentCarService.queryStoreHoliday(city, district, isHolihour, isWeekhour, 
					isHoliday, isWeekday, min, max, carBrand, carType);
//			System.out.println(storeData);
			return storeData;
		} else {
			Collection<RentCarBean2> storeData = rentCarService.queryStoreAllday(city, district, isHolihour, isWeekhour, 
					isHoliday, isWeekday, min, max, carBrand, carType);
//			System.out.println(storeData);
			return storeData;
		}
	}
}
