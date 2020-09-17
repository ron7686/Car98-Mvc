package com.web.car98.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController {
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
}
