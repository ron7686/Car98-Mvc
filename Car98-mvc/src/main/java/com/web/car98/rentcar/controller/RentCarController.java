package com.web.car98.rentcar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.service.RentCarService;

@Controller
public class RentCarController {
	
	@Autowired 
	RentCarService rentCarService;
		
	@GetMapping("/area")
	@ResponseBody
	protected List<RentCarBean> doGet(){
		List<RentCarBean> areaData = rentCarService.showArea();
			return areaData;
		}
	}


