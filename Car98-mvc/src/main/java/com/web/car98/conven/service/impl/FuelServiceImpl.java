package com.web.car98.conven.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.car98.conven.dao.impl.FuelDaoImpl;
import com.web.car98.conven.model.Fuel;
import com.web.car98.conven.model.FuelPriceBean;
import com.web.car98.conven.service.FuelService;

@Service
@Transactional
public class FuelServiceImpl implements FuelService {

	@Autowired
	FuelDaoImpl dao;
	
	@Override
	public List<Fuel> getByMemId(Integer memId) {
		return dao.getByMemId(memId);
	}

	@Override
	public void insert(Fuel fu) {
		dao.insert(fu);
	}

	@Override
	public void update(Fuel fu) {
		
	}

	@Override
	public void delete(Integer fuelId) {

	}

	@Override
	public List<FuelPriceBean> getTypeList() {
		return dao.getTypeList();
	}

	@Override
	public Fuel getFuelById(int fuelId) {
		return dao.getFuelById(fuelId);
	}

}
