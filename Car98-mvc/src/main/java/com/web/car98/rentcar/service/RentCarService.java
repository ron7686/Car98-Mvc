package com.web.car98.rentcar.service;

import java.util.Collection;
import java.util.List;

import com.web.car98.rentcar.model.RentCarBean;

public interface RentCarService {

	Collection<RentCarBean> queryStoreData();
	
	public List<RentCarBean> getAllRentCars();

	public RentCarBean getRentCar();
	
//	public RentCarBean queryRentCarData(Integer rentId, 
//	String store, 
//	String city, 
//	String district,
//	String street);
}
