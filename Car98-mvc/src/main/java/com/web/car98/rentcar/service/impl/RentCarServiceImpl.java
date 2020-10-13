package com.web.car98.rentcar.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.car98.rentcar.dao.RentCarDao;
import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.model.RentCarBean2;
import com.web.car98.rentcar.service.RentCarService;

@Service
public class RentCarServiceImpl implements RentCarService{
	
	@Autowired
	RentCarDao dao;
	
	@Transactional
	@Override
	public Collection<RentCarBean2> queryStoreWeekhour(String city, String district, 
			boolean isHolihour, boolean isWeekhour, boolean isHoliday, boolean isWeekday, 
			Integer min, Integer max, String carBrand, String carType) {
		return dao.queryStoreWeekhour(city, district, isHolihour, isWeekhour, 
				isHoliday, isWeekday, min, max, carBrand, carType);
	}
	
	@Transactional
	@Override
	public Collection<RentCarBean2> queryStoreHolihour(String city, String district, 
			boolean isHolihour, boolean isWeekhour, boolean isHoliday, boolean isWeekday, 
			Integer min, Integer max, String carBrand, String carType) {	
		return dao.queryStoreHolihour(city, district, isHolihour, isWeekhour, 
				isHoliday, isWeekday, min, max, carBrand, carType);
	}
	
	@Transactional
	@Override
	public Collection<RentCarBean2> queryStoreHoliday(String city, String district, 
			boolean isHolihour, boolean isWeekhour, boolean isHoliday, boolean isWeekday, 
			Integer min, Integer max, String carBrand, String carType) {
		return dao.queryStoreHoliday(city, district, isHolihour, isWeekhour, 
				isHoliday, isWeekday, min, max, carBrand, carType);
	}
	
	@Transactional
	@Override
	public Collection<RentCarBean2> queryStoreWeekday(String city, String district, 
			boolean isHolihour, boolean isWeekhour, boolean isHoliday, boolean isWeekday, 
			Integer min, Integer max, String carBrand, String carType) {
		return dao.queryStoreWeekday(city, district, isHolihour, isWeekhour, 
				isHoliday, isWeekday, min, max, carBrand, carType);
	}
	
	@Transactional
	@Override
	public Collection<RentCarBean2> queryStoreAllday(String city, String district, 
			boolean isHolihour, boolean isWeekhour, boolean isHoliday, boolean isWeekday, 
			Integer min, Integer max, String carBrand, String carType) {
		return dao.queryStoreAllday(city, district, isHolihour, isWeekhour, 
				isHoliday, isWeekday, min, max, carBrand, carType);
	}
	
	@Transactional
	@Override
	public List<RentCarBean> showArea() {
		return dao.showArea();
	}
	
	@Transactional
	@Override
	public Collection<RentCarBean> getAllRentCars() {	
		Collection<RentCarBean> rentCarBean = null;
		rentCarBean = dao.getAllRentCars();
		return rentCarBean;
	}
}
