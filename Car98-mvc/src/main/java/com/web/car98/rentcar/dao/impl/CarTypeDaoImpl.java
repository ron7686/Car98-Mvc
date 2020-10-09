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
		String sql = "SELECT * FROM"
				+ "(SELECT ROW_NUMBER() OVER (PARTITION BY carBrand,carType ORDER BY carBrand) AS "
				+ "row_num,carBrand,carType FROM cartype) A WHERE A.row_num = 1;";
		Session session = factory.getCurrentSession();
//		NativeQuery<CarTypeBean> query = session.createNativeQuery(sql,CarTypeBean.class);
//		return query.list();
		Collection<CarTypeBean> carTypeBean = session.createSQLQuery(sql).list();
		return carTypeBean;
    }
}
