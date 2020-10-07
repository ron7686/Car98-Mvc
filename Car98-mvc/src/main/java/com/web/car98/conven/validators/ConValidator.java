package com.web.car98.conven.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.car98.conven.model.Fuel;

@Component
public class ConValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Fuel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Fuel fuel = (Fuel) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "","請填日期");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gallon", "", "請填加油量");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mileage", "", "請填里程");
		
		if (fuel.getFuelPriceBean().getTypeNo() == -1) {
			errors.rejectValue("fuelPriceBean.typeNo","", "必須挑選油品");
		}
		
		
	}

}
