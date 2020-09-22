package com.web.car98.commodity.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.car98.commodity.model.BidBean;


@Component
public class BidValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return BidBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BidBean bidBean = (BidBean)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bidItemName", "","品名不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bidPrice", "", "價格不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bidStock", "", "數量不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bidScore", "", "麻煩給評價");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bidFormat", "", "請描述商品");
		
		if (bidBean.getBidItemBean().getBidItemId() == -1) {
			errors.rejectValue("bidItemBean.bidItemId","", "必須挑選類型");
		}
		
		
	}

}
