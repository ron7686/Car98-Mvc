package com.web.car98.rentcar.dao.impl;

import java.util.Collection;

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
}
