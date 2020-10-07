package com.web.car98.member.controller;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.web.car98.member.model.MemberBean;
import com.web.car98.member.service.MemberService;
import com.web.car98.validator.ChangePasswordValidator;

import _00_init.util.GlobalService;

@Controller
@SessionAttributes({ "LoginOK", "memberBean", "loginBean" })
public class MemberJson {

	@Autowired
	MemberService memberService;

	@Autowired
	ChangePasswordValidator changePasswordValidator;

	@GetMapping("/json")
	public String json() {
		return "/register/Json";
	}

	@GetMapping("/user")
	@ResponseBody
	public List<MemberBean> showUser() {
		List<MemberBean> mbl = memberService.findAllMembers();
		return mbl;
	}

	@GetMapping(value = "/user/{memId}", produces = { "application/json" })
	@ResponseBody
	public MemberBean displayMember(@PathVariable Integer memId) {
		MemberBean mb = memberService.queryMember(memId);
		return mb;
	}

	// 即將抵達管理頁面
	@GetMapping("/userManager")
	public String showUsers() {
		return "/management/JqAjax";
	}

	// 後台修改會員資料
	@GetMapping("/memberAx/{memId}")
	public String memberEdit(Model model, @PathVariable("memId") Integer memId) {
		MemberBean memberBean = memberService.queryMember(memId);
		model.addAttribute("memId", memId);
		model.addAttribute("memberBean", memberBean);
		return "/management/managementUser";
	}

	@PutMapping(value = "/user/{memId}", 
			consumes = { "application/json" }, 
			produces = { "application/json" })
	public @ResponseBody Map<String, String> updateUser(@RequestBody MemberBean mb) {
		Map<String, String> map = new HashMap<>();

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
		} catch (Exception e) {
			e.printStackTrace();
			map.put("fail", "更新失敗");
			System.out.println("更新資料失敗");
		}
		return map;
	}

	@PostMapping("/managementUser")
	public String updateUserData(Model model, @ModelAttribute("memberBean") MemberBean memberBean) {
		MemberBean mb = memberService.queryMember(memberBean.getMemId());

		mb.setId(memberBean.getId());
		mb.setName(memberBean.getName());
		mb.setPhone(memberBean.getPhone());
		mb.setSex(memberBean.getSex());

		MultipartFile memberImage = memberBean.getMemberMultipartFile();
		String originalFilename = memberImage.getOriginalFilename();
		if (memberImage != null && !memberImage.isEmpty()) {
			try {
				byte[] b = memberImage.getBytes();
				Blob blob = new SerialBlob(b);
				mb.setFileName(originalFilename);
				mb.setHeadPic(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}

		memberService.updateUserData(mb);
		return "redirect:/userManager";
	}

	// 依鍵值刪除會員
	@DeleteMapping(value = "/user/{memId}")
	public @ResponseBody Map<String, String> deletePortfolio(
			@PathVariable(required = true) Integer memId) {

		Map<String, String> map = new HashMap<>();
		try {
			memberService.deleteMemberById(memId);
			map.put("success", "刪除成功");
			System.out.println("刪除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("fail", "刪除失敗");
			System.out.println("刪除失敗");
		}
		return map;
	}

//	@SuppressWarnings("rawtypes")
//	@GetMapping("showImage")
//	public ResponseEntity getImage() {
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/*").body(getImage());
//	}
}
