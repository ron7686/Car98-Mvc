package com.web.car98.controller;

import java.io.File;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.web.car98.model.MemberBean;
import com.web.car98.service.MemberService;

import _00_init.util.GlobalService;

@Controller
public class RegisterController {
	final static private Integer LEVELS = 2;
	
	@Autowired
	MemberService memberService;
	@Autowired
	ServletContext context;

	
	@GetMapping("/register")
	public String getAddNewMemberForm(Model model) {
		MemberBean mb = new MemberBean();
		model.addAttribute("memberBean",mb);
		return "/register/register";
	}
	
	@PostMapping("/register")
	public String processAddNewMemberForm(@ModelAttribute("memberBean") MemberBean mb) {
		Date currentDate = new Date(System.currentTimeMillis());
		mb.setMeCreate(currentDate);

		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
		mb.setLoginTime(loginTime);
		
		mb.setPassword(GlobalService.getMD5Endocing
				(GlobalService.encryptString(mb.getPassword())));
		mb.setLevels(LEVELS);
		
//		if(memberService.idExists(mb.getEmail())) {
//			memberService.rejectValue("email", "" , "E-mail已存在，請重新輸入");
//			return inputDataForm;
//		}
		
		// ------------------------- 檔案上傳處理 ------------------------
		MultipartFile memberImage = mb.getMemberMultipartFile();
		String originalFilename = memberImage.getOriginalFilename();
		mb.setFileName(originalFilename);
		if(memberImage!=null && memberImage.isEmpty()) {
			try {
				byte []b = memberImage.getBytes();
				Blob blob = new SerialBlob(b);
				mb.setHeadPic(blob);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		// ------------------- 驗證沒問題，存進去資料庫 -----------------
		memberService.saveMember(mb);
		
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
