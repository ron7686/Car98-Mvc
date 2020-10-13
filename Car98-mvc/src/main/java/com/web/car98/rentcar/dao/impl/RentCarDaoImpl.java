package com.web.car98.rentcar.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.rentcar.dao.RentCarDao;
import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.model.RentCarBean2;

@SuppressWarnings("unchecked")
@Repository
public class RentCarDaoImpl implements RentCarDao {

	@Autowired
	SessionFactory factory;
	
	@Override //用價格取得店家資料
	public Collection<RentCarBean2> queryStoreWeekhour(String city, String district, boolean isHolihour, boolean isWeekhour, 
			boolean isHoliday, boolean isWeekday, Integer min,Integer max,String carBrand,String carType) {
		String sql = "SELECT A.*,B.city,B.district,B.store,B.street FROM cartype A LEFT JOIN rentcar B ON A.rentId = B.rentId "
				+ "WHERE B.city LIKE :city AND B.district LIKE :district AND (A.weekdayHourly BETWEEN :min AND :max) AND (A.carBrand LIKE :carBrand AND A.carType LIKE :carType);";
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
	
	public Collection<RentCarBean2> queryStoreHolihour(String city, String district, boolean isHolihour, boolean isWeekhour, 
			boolean isHoliday, boolean isWeekday, Integer min,Integer max,String carBrand,String carType) {
		String sql = "SELECT A.*,B.city,B.district,B.store,B.street FROM cartype A LEFT JOIN rentcar B ON A.rentId = B.rentId "
				+ "WHERE B.city LIKE :city AND B.district LIKE :district AND (A.holidayHourly BETWEEN :min AND :max) AND (A.carBrand LIKE :carBrand AND A.carType LIKE :carType);";
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
	
	public Collection<RentCarBean2> queryStoreHoliday(String city, String district, boolean isHolihour, boolean isWeekhour, 
			boolean isHoliday, boolean isWeekday, Integer min,Integer max,String carBrand,String carType) {
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
	
	public Collection<RentCarBean2> queryStoreWeekday(String city, String district, boolean isHolihour, boolean isWeekhour, 
			boolean isHoliday, boolean isWeekday, Integer min,Integer max,String carBrand,String carType) {
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
		return carTypeBean;
	}
	
	public Collection<RentCarBean2> queryStoreAllday(String city, String district, boolean isHolihour, boolean isWeekhour, 
			boolean isHoliday, boolean isWeekday, Integer min, Integer max, String carBrand, String carType) {
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
}
