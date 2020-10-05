package com.web.car98.conven.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.car98.conven.dao.impl.FuelDaoImpl;
import com.web.car98.conven.model.Fuel;
import com.web.car98.conven.service.FuelService;

@Service
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

}
