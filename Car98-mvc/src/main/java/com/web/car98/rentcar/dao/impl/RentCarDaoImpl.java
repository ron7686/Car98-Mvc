package com.web.car98.rentcar.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.rentcar.dao.RentCarDao;
import com.web.car98.rentcar.model.RentCarBean;

@SuppressWarnings("unchecked")
@Repository
public class RentCarDaoImpl implements RentCarDao {

	@Autowired
	SessionFactory factory;
	
	@Override
//	建立城市-區(第一個下拉式)選單
	public List<RentCarBean> showCityDistMenu() {
		String hql = "SELECT city, district FROM RentCarBean GROUP BY city , district ORDER BY city";
		Session session = factory.getCurrentSession();
		List<RentCarBean> rentCarBean = new ArrayList<>();
		rentCarBean = session.createQuery(hql).getResultList();
		return rentCarBean;
	}
	
	@Override	//取得符合地區的租車資料(經過使用者篩選)
	public List<RentCarBean> getRentCarsByDist(String city , String district) {
		String hql = "FROM RentCarBean WHERE city = :city AND district = :district";
		Session session = factory.getCurrentSession();
		List<RentCarBean> rentCarBean = session.createQuery(hql)
				.setParameter("city", city)
				.setParameter("district", district)
				.getResultList();
		return rentCarBean;
	}
	
	@Override
	public Collection<RentCarBean> getAllRentCars() {		//取得所有租車資料
		String hql = "FROM RentCarBean";
		Session session = factory.getCurrentSession();
		Collection<RentCarBean> rentCarBean = session.createQuery(hql).getResultList();
		return rentCarBean;
	}
	
	@Override
	public RentCarBean getRentCar() {				//取得單筆租車資料(第?筆)
		String hql = "FROM RentCarBean";
		Session session = factory.getCurrentSession();
		RentCarBean rentCarBean = (RentCarBean) session.createQuery(hql).list().get(0);
		return rentCarBean;
	}

	
	@Override
	public RentCarBean getRentCar(Integer rentId) {
		return null;
	}
}
