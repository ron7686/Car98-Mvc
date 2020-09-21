package com.web.car98.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.car98.forum.dao.TalkDao;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.TalkService;

@Controller
public class TalkController {
	@Autowired
	TalkService talkservice;
	int page=1;
	
@RequestMapping("/carTalk")
	public String list(Model model) {
		
		List<TalkBean> list = talkservice.select(page);
		model.addAttribute("abean", list);
		
		return "/forum/carTalk";
		
	}

//@RequestMapping("/carTalk")
//	public int lastpage(){
//	
//		return 0;
//	}
}
