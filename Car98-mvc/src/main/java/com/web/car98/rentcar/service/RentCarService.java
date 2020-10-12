package com.web.car98.rentcar.service;

import java.util.Collection;
import java.util.List;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.model.RentCarBean2;

public interface RentCarService {

//	Collection<RentCarBean> queryStoreByArea(String city, String district);
	
	Collection<RentCarBean2> queryStoreHoliday(String city, String district, boolean isHoliday, boolean isWeekday, Integer min, Integer max, String carBrand, String carType);
	
	Collection<RentCarBean2> queryStoreWeekday(String city, String district, boolean isHoliday, boolean isWeekday, Integer min, Integer max, String carBrand, String carType);

	Collection<RentCarBean2> queryStoreAllday(String city, String district, boolean isHoliday, boolean isWeekday, Integer min, Integer max, String carBrand, String carType);
	
	List<RentCarBean> showArea();
	
	public Collection<RentCarBean> getAllRentCars();

	public RentCarBean getRentCar();
	
//	public RentCarBean queryRentCarData(Integer rentId, 
//	String store, 
//	String city, 
//	String district,
//	String street);
}
