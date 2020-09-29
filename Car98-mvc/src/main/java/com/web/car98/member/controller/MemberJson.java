package com.web.car98.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;

import com.web.car98.member.model.MemberBean;
import com.web.car98.member.service.MemberService;

@RestController
public class MemberJson {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/json")
	public String json() {
		return "/register/Json";
	}
	
	@GetMapping("/showUser")
	public List<MemberBean> showUser(){
		List<MemberBean> mbl = memberService.findAllMembers();
		return mbl;
	}
	
//	@SuppressWarnings("rawtypes")
//	@GetMapping("showImage")
//	public ResponseEntity getImage() {
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/*").body(getImage());
//	}
}
