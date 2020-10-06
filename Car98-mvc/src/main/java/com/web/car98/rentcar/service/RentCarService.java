package com.web.car98.rentcar.service;

import java.util.Collection;
import java.util.List;

import com.web.car98.rentcar.model.RentCarBean;

public interface RentCarService {

	
	public Collection<RentCarBean> getAllRentCars();

	public RentCarBean getRentCar();
	
	public List<RentCarBean> getRentCarsByDist(String city , String district);
	
	public List<RentCarBean> showCityDistMenu();
}
