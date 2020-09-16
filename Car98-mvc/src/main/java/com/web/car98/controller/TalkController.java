package com.web.car98.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.car98.service.TalkService;

@Controller
public class TalkController {
@Autowired
	TalkService talkservice;
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
}
