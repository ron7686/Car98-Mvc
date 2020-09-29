package com.web.car98.rentcar.dao;

import java.util.Collection;
import java.util.List;

import com.web.car98.rentcar.model.CarTypeBean;

public interface CarTypeDao {
//	1. 查詢
	Collection<CarTypeBean> getCarTypeData();
	
//	建立第三個下拉式選單
	List<CarTypeBean> showBrandTypeMenu();
	
//	2. 由 CarType 和  RentCar 表格中，挑選出某價格範圍內所有的租車資料
//	List<String> getRentCarsByPrice();
	
//	3. 由  CarType 表格中，挑選出(SELECT DISTINCT)某價格範圍內所有的車子型號
//	List<String> getCarTypesByPrice();
	
//	4. 由  CarType 表格中，挑選出(SELECT DISTINCT)所有的車子廠牌
//	List<String> getAllCarBrands();
	
//	5. 由  CarType 表格中，挑選出(SELECT DISTINCT)所有的車子型號
//	List<String> getAllCarTypes();
		
//	6. 依照特定車牌，由 CarType 表格挑選出車型
//	List<String> getTypesByBrand();
	
}
