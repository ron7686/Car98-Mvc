package com.web.car98.rentcar.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.service.CarTypeService;
import com.web.car98.rentcar.service.RentCarService;

@Controller
public class MenuController {
	
	@Autowired
	CarTypeService carTypeService;
	
	@Autowired 
	RentCarService rentCarService;
		
	@GetMapping("/area")
	@ResponseBody
	protected List<RentCarBean> doGet(){
		List<RentCarBean> areaData = rentCarService.showArea();
		return areaData;
	}
	
//	@RequestMapping(value = "/cartype", method = RequestMethod.GET) 舊的寫法
	@GetMapping("/cartype")
	@ResponseBody
	protected Collection<CarTypeBean> getCarTypeList(){
		Collection<CarTypeBean> carTypeData = carTypeService.getCarTypeData();
		return carTypeData;
	}
}


