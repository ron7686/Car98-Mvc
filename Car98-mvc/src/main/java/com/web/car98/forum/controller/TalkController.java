package com.web.car98.forum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.TalkService;

@Controller
//@SessionAttributes("LoginOK")
public class TalkController {
	@Autowired
	TalkService talkservice;

	int postID = 1;

	@RequestMapping("/forum/talktop.do")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "lastPage", required = false) Integer lastpage,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "type", required = false) String type,
			@ModelAttribute("talkBean") TalkBean talkBean) {

		if (pageNo == null) {
			pageNo = 1;
		}
//		List<TalkBean> list = talkservice.select(pageNo);
//		
//		
//		
//		List<TalkBean> list = new ArrayList<>();

		if (type != null) {
			type=talkservice.intToType(type);
		}
//		list = talkservice.getPageByType(pageNo, type);
//
//		model.addAttribute("abean", list);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("lastPage", talkservice.lastpage());

		return "/forum/carTalk";

	}

}
