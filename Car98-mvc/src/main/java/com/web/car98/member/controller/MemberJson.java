package com.web.car98.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.member.model.MemberBean;
import com.web.car98.member.service.MemberService;

@Controller
@SessionAttributes({ "LoginOK", "memberBean", "loginBean" })
public class MemberJson {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/json")
	public String json() {
		return "/register/Json";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public List<MemberBean> showUser(){
		List<MemberBean> mbl = memberService.findAllMembers();
		return mbl;
	}
	
	@GetMapping(value = "/user/{memId}", produces= {"application/json"})
	@ResponseBody
	public MemberBean displayMember(@PathVariable Integer memId) {
		MemberBean mb = memberService.queryMember(memId);
		return mb;
	}
	
	//即將抵達管理頁面
	@GetMapping("/userManager")
	public String showUsers() {
		return "/management/JqAjax";
	}
	
	// 後台修改會員資料
	@GetMapping("/memberAx/{memId}")
	public String memberEdit(Model model,
			@PathVariable("memId") Integer memId) {
		MemberBean memberBean = memberService.queryMember(memId);
		model.addAttribute("memId",memId);
		model.addAttribute("memberBean",memberBean);
		return "/management/managementUser";
	}
	
	@PutMapping(value="/user/{memId}"
			,consumes = {"application/json"} 
			,produces = {"application/json"})
	public @ResponseBody Map<String,String> updateUser(
			@RequestBody MemberBean mb){
		Map<String , String> map = new HashMap<>();
		
		try {
			MemberBean memberBean = memberService.queryMember(mb.getMemId());
//			memberBean.setHeadPic(mb.getHeadPic());
			memberBean.setName(mb.getName());
			memberBean.setId(mb.getId());
			memberBean.setPhone(mb.getPhone());
			memberBean.setEmail(mb.getEmail());
			memberBean.setBirth(mb.getBirth());
			memberBean.setSex(mb.getSex());
			memberService.updateUserData(memberBean);
			map.put("success", "更新成功");
			System.out.println("更新資料成功");
		}catch(Exception e) {
			e.printStackTrace();
			map.put("fail","更新失敗");
			System.out.println("更新資料失敗");
		}
		return map;
	}
	
//	@SuppressWarnings("rawtypes")
//	@GetMapping("showImage")
//	public ResponseEntity getImage() {
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/*").body(getImage());
//	}
}
