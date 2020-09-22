package com.web.car98.rentcar.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.car98.rentcar.dao.RentCarDao;
import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.service.RentCarService;

@Service
public class RentCarServiceImpl implements RentCarService{
	
	@Autowired
	RentCarDao dao;
	
	@Transactional
	@Override
	public Collection<RentCarBean> queryStoreData() {
		return dao.queryStoreData();
	}
	
	@Transactional
	@Override
	public List<RentCarBean> getAllRentCars() {	
		List<RentCarBean> rentCarBean = null;
		rentCarBean = dao.getAllRentCars();
		return rentCarBean;
	}
	
	@Transactional
	@Override
	public RentCarBean getRentCar() {	
		RentCarBean rentcarbean = null;
		rentcarbean = dao.getRentCar();
		return rentcarbean;
	}
}
