package com.web.car98.rentcar.dao;

import java.util.Collection;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean2;

public interface CarTypeDao {
//	1. 查詢
	Collection<CarTypeBean> getCarTypeData();
	
}
