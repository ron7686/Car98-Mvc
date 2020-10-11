package com.web.car98.rentcar.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.service.CarTypeService;
import com.web.car98.rentcar.service.RentCarService;

@Controller
//@RequestMapping(value = "/rentcar", method = RequestMethod.GET)
public class CarTypeController {

	@Autowired
	CarTypeService carTypeService;
	
	@Autowired 
	RentCarService rentCarService;

//	@RequestMapping(value = "/cartype", method = RequestMethod.GET) 舊的寫法
	@GetMapping("/cartype")
	@ResponseBody
	protected Collection<CarTypeBean> getCarTypeList(){
		Collection<CarTypeBean> carTypeData = carTypeService.getCarTypeData();
		return carTypeData;
	}
}

	

