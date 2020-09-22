package com.web.car98.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.car98.member.model.MemberBean;

@Component
public class MemberBeanValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberBean mb = (MemberBean) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", 	"", "email不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", 	"", "密碼不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password1", 	"", "確認密碼不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", 	"", "姓名不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", 	"", "暱稱不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", 	"", "電話不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birth", 	"", "生日不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", 	"", "性別不能空白");
		
		if(!mb.getPassword().equals(mb.getPassword1())) {
			errors.rejectValue("password", "", "密碼不一致");
		}
		
	}

}
