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
}
