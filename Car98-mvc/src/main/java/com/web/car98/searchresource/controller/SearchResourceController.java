package com.web.car98.searchresource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchResourceController {

	
//	@RequestMapping("/searchresource")
//	public String resource(Model model) {
//		return "searchresource/searchresource";
//	}
	
	@GetMapping("/searchresource")
	public ModelAndView getsearchresource(Model model) {
		return new ModelAndView("/searchresource/searchresource");
	}
	
}
