package com.web.car98.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.car98.forum.model.TalkBean;
@Component
public class TalkContentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return TalkBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		TalkBean talkBean=(TalkBean)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PostTitle","","標題不能空白" );
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PostText","","內文不能空白" );
	}

}
