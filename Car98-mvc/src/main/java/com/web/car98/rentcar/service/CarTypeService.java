package com.web.car98.rentcar.service;

import java.util.Collection;
import java.util.List;

import com.web.car98.rentcar.model.CarTypeBean;

public interface CarTypeService {
	
	public Collection<CarTypeBean> getCarTypeData();
	
	public List<CarTypeBean> showBrandTypeMenu();
	
	public List<CarTypeBean> getRentCarsByPrice(Integer min, Integer max);
	
	public List<CarTypeBean> getRentCarsByType(String carBrand, String carType);

//	public Collection<CarTypeBean> getCarTypeData(
//			Integer typeId, 
//			Integer rentId, 
//			String carBrand, 
//			String carType, 
//			Integer weekdayHourly, 
//			Integer holidayHourly, 
//			Integer weekdayDaily, 
//			Integer holidayDaily);
}
