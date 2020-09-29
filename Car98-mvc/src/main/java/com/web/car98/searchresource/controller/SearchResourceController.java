package com.web.car98.searchresource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchResourceController {

	
	@RequestMapping("/searchresource")
	public String resource(Model model) {
		return "searchresource/searchresource";
	}
	
}
