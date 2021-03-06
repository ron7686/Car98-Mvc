package com.web.car98.rentcar.service.impl;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.car98.rentcar.dao.CarTypeDao;
import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean2;
import com.web.car98.rentcar.service.CarTypeService;

@Service
public class CarTypeServiceImpl implements CarTypeService{
	@Autowired
	CarTypeDao ctdao;
	
	@Transactional
	@Override
	public Collection<CarTypeBean> getCarTypeData(){
		Collection<CarTypeBean> carTypeBean = null;
		carTypeBean = ctdao.getCarTypeData();
		return carTypeBean;
	}
}
