package com.web.car98.forum.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.TalkService;
import com.web.car98.member.model.MemberBean;

@Controller
//@SessionAttributes("LoginOK")
public class TalkController {
	@Autowired
	TalkService talkservice;
	int page = 1;
	int postID = 1;
	

	@RequestMapping("/forum/talktop.do")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="lastPage",required=false) Integer lastpage,
			@RequestParam(value = "pageNo", required = false) Integer pageNo
			) {
		
		if(pageNo==null) {
			pageNo=1;
		}
		List<TalkBean> list = talkservice.select(pageNo);
		
		
		//talkservice.selectOne(lastpage);
		model.addAttribute("abean", list);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("lastPage", talkservice.lastpage());

		return "/forum/carTalk";

	}
	
	@PostMapping("/talk.do")
	public String talkContent() {
		
		
		return "/forum/talkContent";
	}
	

//	public int  getpage(Model model) {
//		int lastpage=talkservice.lastpage();
//		model.addAttribute("pageNo", lastpage);
//	
//		return lastpage;
//	}
//
////@RequestMapping("carTalk")
//public TalkBean selectone(Model model) {
//	   TalkBean tb=talkservice.selectOne(postID);
//	   
//	model.addAttribute("aBean",tb);
//	return tb;
//}

//@RequestMapping("/carTalk")
//	public int lastpage(){
//	
//		return 0;
//	}
}
