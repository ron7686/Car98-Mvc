package com.web.car98.rentcar.dao;

import java.util.Collection;
import java.util.List;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.model.RentCarBean2;

public interface RentCarDao {
	
	//  查詢店家 by 地區價格車型
	Collection<RentCarBean2> queryStoreHoliday(String city, String district, boolean isHoliday, boolean isWeekday, Integer min, Integer max, String carBrand, String carType);
	
	Collection<RentCarBean2> queryStoreWeekday(String city, String district, boolean isHoliday, boolean isWeekday, Integer min, Integer max, String carBrand, String carType);

	Collection<RentCarBean2> queryStoreAllday(String city, String district, boolean isHoliday, boolean isWeekday, Integer min, Integer max, String carBrand, String carType);

	//下拉選單地址
	List<RentCarBean> showArea();
	
	//	查詢全部
	List<RentCarBean> getAllRentCars();
	
	//	查詢單筆
	RentCarBean getRentCar(Integer rentId);
	
	RentCarBean getRentCar();

}
