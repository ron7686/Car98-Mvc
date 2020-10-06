package com.web.car98.rentcar.dao;

import java.util.Collection;
import java.util.Dictionary;
import java.util.List;

import com.web.car98.rentcar.model.RentCarBean;

public interface RentCarDao {
	
//	查詢全部
	Collection<RentCarBean> getAllRentCars();
	
//	查詢單筆
	RentCarBean getRentCar(Integer rentId);
	
	RentCarBean getRentCar();
	
//	建立第一個下拉式選單
	List<RentCarBean> showCityDistMenu();
	
//	由RentCar表格中，依照特定城市的區，挑選出該區所有的租車資料
	List<RentCarBean> getRentCarsByDist(String city,String district);
	
	


}
