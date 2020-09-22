package com.web.car98.rentcar.dao;

import java.util.Collection;
import java.util.List;

import com.web.car98.rentcar.model.RentCarBean;

public interface RentCarDao {
	
	//  查詢店家
	Collection<RentCarBean> queryStoreData();
	
	//	查詢全部
	List<RentCarBean> getAllRentCars();
	
	//	查詢單筆
	RentCarBean getRentCar(Integer rentId);
	
	RentCarBean getRentCar();
	
//	由RentCar表格中，依照特定城市的區，挑選出該區所有的租車資料
//	List<RentCarBean> getRentCarsByDist(String district);
	
	


}
