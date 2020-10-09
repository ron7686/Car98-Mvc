package com.web.car98.rentcar.dao;

import java.util.Collection;
import java.util.List;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean;

public interface RentCarDao {
	
//	//  查詢店家 by 地區
//	Collection<RentCarBean> queryStoreByArea(String city, String district);
	
	//  查詢店家 by 地區價格車型
	Collection<CarTypeBean> queryStoreHoliday(String city, String district, boolean isHoliday, Integer min, Integer max, String carBrand, String carType);
	
	Collection<CarTypeBean> queryStoreWeekday(String city, String district, boolean isHoliday, Integer min, Integer max, String carBrand, String carType);
	
	//  查詢店家 by 車型
//	Collection<CarTypeBean> queryStoreByCar(String carBrand,String carType);
	
	//下拉選單地址
	List<RentCarBean> showArea();
	
	//	查詢全部
	List<RentCarBean> getAllRentCars();
	
	//	查詢單筆
	RentCarBean getRentCar(Integer rentId);
	
	RentCarBean getRentCar();
	
//	由RentCar表格中，依照特定城市的區，挑選出該區所有的租車資料
//	List<RentCarBean> getRentCarsByDist(String district);
	
	


}
