package com.web.car98.rentcar.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.dao.CarTypeDao;
import com.web.car98.rentcar.model.RentCarBean;

@SuppressWarnings("unchecked")
@Repository
public class CarTypeDaoImpl implements CarTypeDao {
	
	@Autowired
	SessionFactory factory;
	
	public CarTypeDaoImpl() {
	}
	
	@Override
	public Collection<CarTypeBean> getCarTypeData(){
		String hql = "FROM CarTypeBean";
		Session session = factory.getCurrentSession();
		Collection<CarTypeBean> carTypeBean = session.createQuery(hql).list();
		return carTypeBean;
    }
	
//	建立車牌-車型(第三個下拉式)選單
	@Override
	public List<CarTypeBean> showBrandTypeMenu() {
		String hql = "FROM CarTypeBean GROUP BY carBrand , carType";
		Session session = factory.getCurrentSession();
		List<CarTypeBean> carTypeBean = new ArrayList<>();
		carTypeBean = session.createQuery(hql).getResultList();
		return carTypeBean;
	}
	
	@Override	// 依照指定的車牌和車型，列出所有的租車資料
	public List<CarTypeBean> getRentCarsByType(String carBrand, String carType) {
		String hql = "FROM CarTypeBean WHERE carBrand = :carBrand AND carType = :carType";
		Session session = factory.getCurrentSession();
		List<CarTypeBean> carTypeBean = session.createQuery(hql)
											   .setParameter("carBrand", carBrand)
											   .setParameter("carType", carType)
											   .getResultList();
		return carTypeBean;
	}

	@Override
	public List<CarTypeBean> getRentCarsByPrice(Integer min, Integer max) {
//		String hql1 = "FROM CarTypeBean WHERE weekdayHourly BETWEEN :min AND :max";
//		String hql2 = "FROM CarTypeBean WHERE holidayHourly BETWEEN :min AND :max";
//		String hql3 = "FROM CarTypeBean WHERE weekdayDaily BETWEEN :min AND :max";
		String hql4 = "FROM CarTypeBean WHERE holidayDaily BETWEEN :min AND :max";
		Session session = factory.getCurrentSession();
		List<CarTypeBean> carTypeBean = session.createQuery(hql4)
				                               .setParameter("min", min)
				                               .setParameter("max", max)
				                               .getResultList();
		return carTypeBean;
	}
}
