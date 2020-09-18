package com.web.car98.controller;

import java.io.File;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.web.car98.model.MemberBean;
import com.web.car98.service.MemberService;
import com.web.car98.validator.MemberBeanValidator;

import _00_init.util.GlobalService;

@Controller
public class RegisterController {
	final static private Integer LEVELS = 2;
	
	@Autowired
	MemberService memberService;
	@Autowired
	ServletContext context;
	
	@Autowired
	MemberBeanValidator validator;

	String registerForm = "/register/register";
	
	@GetMapping("/register")
	public String getAddNewMemberForm(Model model) {
		MemberBean mb = new MemberBean();
		model.addAttribute("memberBean",mb);
		return "/register/register";
	}
	
	@PostMapping("/register")
	public String processAddNewMemberForm(@ModelAttribute("memberBean") MemberBean mb,
			BindingResult result,Model model,
			HttpServletRequest request) {
		validator.validate(mb, result);
		
		// 有錯誤訊息返回 register.jsp
		if(result.hasErrors()) {
			return registerForm;
		}
		
		
//		if(memberService.idExists(mb.getEmail())) {
//			memberService.rejectValue("email", "" , "E-mail已存在，請重新輸入");
//			return inputDataForm;
//		}
		
		// ------------------------- 檔案上傳處理 ------------------------
		MultipartFile memberImage = mb.getMemberMultipartFile();
		String originalFilename = memberImage.getOriginalFilename();
		mb.setFileName(originalFilename);
		if(memberImage!=null && !memberImage.isEmpty()) {
			try {
				byte []b = memberImage.getBytes();
				Blob blob = new SerialBlob(b);
				mb.setHeadPic(blob);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		
		// 註冊日期
		Date currentDate = new Date(System.currentTimeMillis());
		mb.setMeCreate(currentDate);
		
		// 登入時間
		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
		mb.setLoginTime(loginTime);
		
		// 會員等級設定
		mb.setLevels(LEVELS);
		
		// 處理密碼加密
		mb.setPassword(GlobalService.getMD5Endocing
				(GlobalService.encryptString(mb.getPassword())));
		
		if(memberService.idExists(mb.getEmail())) {
			result.rejectValue("email", "", "email已存在，請重新輸入");
			return registerForm;
		}
		
		// ------------------- 驗證沒問題，存進去資料庫 -----------------
		try {
			memberService.saveMember(mb);
		}
		catch(Exception e) {
			System.out.println(e.getClass().getName() + ", ErrorMessage =" + e.getMessage());
			result.rejectValue("email", "", "發生異常，請通知系統人員..." + e.getMessage());
			return registerForm;
		}
		
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		String rootDirectory = context.getRealPath("/");
		try {
			File imageFolder = new File(rootDirectory, "images");
			if(!imageFolder.exists()) {
				imageFolder.mkdirs();
			}
			File file = new File(imageFolder,mb.getMemId() + ext);
			memberImage.transferTo(file);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常: "+e.getMessage());
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "/login/BSlogin";
	}
}
