package com.web.car98.rentcar.service;

import java.util.Collection;
import com.web.car98.rentcar.model.CarTypeBean;

public interface CarTypeService {
	
	public Collection<CarTypeBean> getCarTypeData();

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