package com.web.car98.rentcar.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.rentcar.dao.RentCarDao;
import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.model.RentCarBean2;

@SuppressWarnings("unchecked")
@Repository
public class RentCarDaoImpl implements RentCarDao {

	@Autowired
	SessionFactory factory;
	
	@Override //用價格取得店家資料
	public Collection<RentCarBean2> queryStoreHoliday(String city, String district, boolean isHoliday, boolean isWeekday, Integer min,Integer max,String carBrand,String carType) {

//		String sql = "SELECT A.*,B.city,B.district,B.store,B.street FROM cartype A LEFT JOIN rentcar B ON A.rentId = B.rentId "
//				+ "WHERE B.city LIKE :city AND B.district LIKE :district AND (A.holidayDaily BETWEEN :min AND :max) AND (A.carBrand = :carBrand AND A.carType= :carType);";
		String sql = "SELECT A.*,B.city,B.district,B.store,B.street FROM cartype A LEFT JOIN rentcar B ON A.rentId = B.rentId "
				+ "WHERE B.city LIKE :city AND B.district LIKE :district AND (A.holidayDaily BETWEEN :min AND :max) AND (A.carBrand LIKE :carBrand AND A.carType LIKE :carType);";
		Session session = factory.getCurrentSession();
		Collection<RentCarBean2> carTypeBean = session.createNativeQuery(sql,RentCarBean2.class)
				.setParameter("city", city)
				.setParameter("district", district)
				.setParameter("min", min)
				.setParameter("max", max)
				.setParameter("carBrand", carBrand)
				.setParameter("carType", carType)
				.list();
		return carTypeBean;
	}
	
	public Collection<RentCarBean2> queryStoreWeekday(String city, String district,boolean isHoliday, boolean isWeekday, Integer min,Integer max,String carBrand,String carType) {
		String sql = "SELECT A.*,B.city,B.district,B.store,B.street FROM cartype A LEFT JOIN rentcar B ON A.rentId = B.rentId "
				+ "WHERE B.city LIKE :city AND B.district LIKE :district AND (A.weekdayDaily BETWEEN :min AND :max) AND (A.carBrand LIKE :carBrand AND A.carType LIKE :carType);";
		Session session = factory.getCurrentSession();
		Collection<RentCarBean2> carTypeBean = session.createNativeQuery(sql,RentCarBean2.class)
				.setParameter("city", city)
				.setParameter("district", district)
				.setParameter("min", min)
				.setParameter("max", max)
				.setParameter("carBrand", carBrand)
				.setParameter("carType", carType)
				.list();
//				.getResultList();
		return carTypeBean;
	}
	
	public Collection<RentCarBean2> queryStoreAllday(String city, String district, boolean isHoliday, boolean isWeekday, Integer min, Integer max, String carBrand, String carType) {
		String sql = "SELECT A.*,B.city,B.district,B.store,B.street FROM cartype A LEFT JOIN rentcar B ON A.rentId = B.rentId "
				+ "WHERE B.city LIKE :city AND B.district LIKE :district AND (A.holidayDaily BETWEEN :min AND :max) AND (A.weekdayDaily BETWEEN :min AND :max) AND (A.carBrand LIKE :carBrand AND A.carType LIKE :carType);";
		Session session = factory.getCurrentSession();
		Collection<RentCarBean2> carTypeBean = session.createNativeQuery(sql,RentCarBean2.class)
				.setParameter("city", city)
				.setParameter("district", district)
				.setParameter("min", min)
				.setParameter("max", max)
				.setParameter("carBrand", carBrand)
				.setParameter("carType", carType)
				.list();
		return carTypeBean;
	}

//	@Override //用車型取得店家資料
//	public Collection<CarTypeBean> queryStoreByCar(String carBrand,String carType) {
//		String hql = "FROM CarTypeBean WHERE carBrand = :carBrand AND carType = :carType";
//		Session session = factory.getCurrentSession();
//		Collection<CarTypeBean> carTypeBean = session.createQuery(hql)
//				.setParameter("carBrand", carBrand)
//				.setParameter("carType", carType)
//				.getResultList();
//		return carTypeBean;
//	}	
	
	@Override
	public List<RentCarBean> showArea(){ //取得 城市 區
		String hql = "select city,district FROM RentCarBean GROUP BY city , district";
		Session session = factory.getCurrentSession();
		List<RentCarBean> rentCarBean = session.createQuery(hql).list();
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
