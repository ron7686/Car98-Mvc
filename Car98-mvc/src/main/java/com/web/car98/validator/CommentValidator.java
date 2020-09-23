package com.web.car98.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.car98.forum.model.CommentBean;

@Component
public class CommentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CommentBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CommentBean commentBean = (CommentBean) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comText", "", "請輸入內文");
	 }
  }
