package com.web.car98.forum.controller;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.TalkService;
import com.web.car98.validator.TalkContentValidator;

@Controller
public class TalkContentController {
	@Autowired	
	TalkService talkservice;
	@Autowired
	TalkContentValidator talkContentValidator;
	
	
	@GetMapping("/talkContent")
	public String talkContent(Model model) {
			TalkBean talkBean=new TalkBean();
			model.addAttribute("talkBean",talkBean);
		
		
		return "/forum/talkContent";
	}
	
	@PostMapping("/talkContent")
	public String addContentForm(@ModelAttribute("talkBean") TalkBean tb,BindingResult bindingResult) {
		String[] suppressedFields=bindingResult.getSuppressedFields();
		talkContentValidator.validate(tb, bindingResult);
		if(bindingResult.hasErrors()) {
			return "forum/talkContent";
		}
		talkservice.persist(tb);
		return "/forum/talktalk";
		
	}
	
}
