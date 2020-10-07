package com.web.car98.forum.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	@Autowired
	ServletContext servletContext;
	int postID = 1;

	@RequestMapping("/forum/talktop.do")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "type", required = false) String type,
			@ModelAttribute("talkBean") TalkBean talkBean) {

		if (pageNo == null) {
			pageNo = 1;
		}
		List<TalkBean> list = new ArrayList<>();
		model.addAttribute("type",type);
		if (type != null) {
			type=talkservice.intToType(type);
		}
		int lastpage=talkservice.lastpage(type);
		list = talkservice.getPageByType(pageNo, type);
		

		
		model.addAttribute("abean", list);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("lastPage", lastpage);

		return "/forum/carTalk";

	}
	
	
	
	
	

}
