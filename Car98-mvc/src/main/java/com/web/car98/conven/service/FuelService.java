package com.web.car98.conven.service;

import java.util.List;

import com.web.car98.conven.model.Fuel;
import com.web.car98.conven.model.FuelPriceBean;

public interface FuelService {
	List<Fuel> getByMemId(Integer memId);

	public void insert(Fuel fu);

	public void update(Fuel fu);

	public void delete(Integer fuelId);
}
