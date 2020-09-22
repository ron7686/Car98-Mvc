package com.web.car98.rentcar.dao.impl;

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
	public Collection<RentCarBean> queryStoreData() { //取得店家資料
		String hql = "FROM RentCarBean";
		Session session = factory.getCurrentSession();
		Collection<RentCarBean> rentCarBean = session.createQuery(hql).getResultList();
		return rentCarBean;
	}
	
	@Override
	public List<RentCarBean> getAllRentCars() {		//取得所有租車資料
		String hql = "FROM RentCarBean";
		Session session = factory.getCurrentSession();
		List<RentCarBean> rentCarBean = session.createQuery(hql).list();
		return rentCarBean;	
	}
	
	@Override
	public RentCarBean getRentCar() {				//取得單筆租車資料(第?筆)
		String hql = "FROM RentCarBean";
		Session session = factory.getCurrentSession();
		RentCarBean rentcarbean = (RentCarBean) session.createQuery(hql).list().get(0);
		return rentcarbean;
	}

//	@Override
//	public List<RentCarBean> getRentCarByDistrict(String district) {	//取得符合地區的資料(經過使用者篩選)
//		String hql = "FROM RentCarBean WHERE district = :district";
//		Session session = factory.getCurrentSession();
//		List<RentCarBean> rentcarbean = session.createQuery(hql).list();
//		return rentcarbean;
//	}

	@Override
	public RentCarBean getRentCar(Integer rentId) {
		return null;
	}	
	
}
