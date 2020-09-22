package com.web.car98.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.car98.member.model.LoginBean;

@Component
public class LoginBeanValidator implements Validator{

	
	@Override
	public boolean supports(Class<?> clazz) {
		return LoginBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "", "帳號不能為空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "密碼不能為空");
	}

}
