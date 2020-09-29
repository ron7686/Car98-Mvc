package com.web.car98.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.TalkService;
import com.web.car98.member.model.MemberBean;
import com.web.car98.validator.TalkContentValidator;

@Controller
@SessionAttributes("LoginOK")
public class TalkContentController {
	@Autowired	
	TalkService talkservice;
	@Autowired
	TalkContentValidator talkContentValidator;
	
	
	@GetMapping("/talkContent")
	public String talkContent(Model model) {
			TalkBean talkBean=new TalkBean();
			model.addAttribute("talkBean",talkBean);
			MemberBean memberBean=(MemberBean) model.getAttribute("LoginOK");
			if(memberBean==null) {
				return "redirect:/login";
			}
		
		return "forum/talkContent";
	}
	
	@PostMapping("/talkContent")
	public String addContentForm(Model model,
			@ModelAttribute("talkBean") TalkBean tb,
			BindingResult bindingResult) {
		String[] suppressedFields=bindingResult.getSuppressedFields();
		talkContentValidator.validate(tb, bindingResult);
		if(bindingResult.hasErrors()) {
			return "forum/talkContent";
		}
		talkservice.persist(tb);
		model.addAttribute("TalkBean", tb);
		return "redirect:/forum/talktop.do";
		
	}
	
}
