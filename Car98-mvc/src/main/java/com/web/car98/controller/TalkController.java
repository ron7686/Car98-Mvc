package com.web.car98.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.car98.model.TalkBean;
import com.web.car98.service.TalkService;

@Controller
public class TalkController {
	@Autowired
	TalkService talkservice;
	
	
//@RequestMapping("/carTalk")
//	public String list(Model model) {
//		int page = 0;
//		List<TalkBean> list = talkservice.select(page);
//		model.addAttribute("abean", list);
//		
//		return "carTalk";
//		
//	}
//@RequestMapping("/carTalk")
//	public int lastpage(){
//	
//		return 0;
//	}
}
