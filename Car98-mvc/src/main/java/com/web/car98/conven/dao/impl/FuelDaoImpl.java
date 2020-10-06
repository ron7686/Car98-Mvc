package com.web.car98.conven.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.car98.commodity.model.BidBean;
import com.web.car98.conven.dao.FuelDao;
import com.web.car98.conven.model.Fuel;
import com.web.car98.conven.model.FuelPriceBean;
@Repository
public class FuelDaoImpl implements FuelDao {
	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Fuel> getByMemId(Integer memId) {
		List<Fuel> list = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM Fuel ob WHERE ob.memId = :mid";
		list = session.createQuery(hql).setParameter("mid", memId).getResultList();
		return list;
	}

	@Override
	public void insert(Fuel fu) {
		Session session = factory.getCurrentSession();
		FuelPriceBean fpb = getTypeByNo(fu.getFuelPriceBean().getTypeNo());
		fu.setFuelPriceBean(fpb);
		session.save(fu);
	}

	@Override
	public void update(Fuel fu) {
		
	}

	@Override
	public void delete(Integer fuelId) {
		Session session = factory.getCurrentSession();
		Fuel fu = session.get(Fuel.class, fuelId);
		if (fu != null) {
			fu.setFuelPriceBean(null);
			session.delete(fu);
		}
	}

	@Override
	public FuelPriceBean getTypeByNo(Integer typeNo) {
		Session session = factory.getCurrentSession();
		return session.get(FuelPriceBean.class, typeNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FuelPriceBean> getTypeList() {
		String hql = "FROM FuelPriceBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public Fuel getFuelById(int fuelId) {
		return  factory.getCurrentSession().get(Fuel.class, fuelId);
	}

	@Override
	public FuelPriceBean getFuelByPrice(int typeNo) {
		return factory.getCurrentSession().get(FuelPriceBean.class, typeNo);
	}
	

}
