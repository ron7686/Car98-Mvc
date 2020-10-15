package com.web.car98.member.controller;

import java.io.File;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.car98.init.mail.RegisterSmtp;
import com.web.car98.member.model.MemberBean;
import com.web.car98.member.service.MemberService;
import com.web.car98.validator.ChangePasswordValidator;
import com.web.car98.validator.MemberBeanValidator;

import _00_init.util.GlobalService;

@Controller
@SessionAttributes({ "LoginOK", "memberBean", "loginBean" })
public class RegisterController {
	final static private Integer LEVELS = 2;

	@Autowired
	MemberService memberService;
	@Autowired
	ServletContext context;

	@Autowired
	MemberBeanValidator memberBeanValidator;

	@Autowired
	ChangePasswordValidator changePasswordValidator;
	
	@Autowired
	RegisterSmtp rs;

	String registerForm = "/login/testloginregister";

	@GetMapping("/register")
	public String getAddNewMemberForm(Model model) {
		MemberBean mb = new MemberBean();
		model.addAttribute("memberBean", mb);
		return "/register/register";
	}

	@PostMapping("/register")
	public String processAddNewMemberForm(@ModelAttribute("memberBean") MemberBean mb, 
			BindingResult result,Model model, 
			RedirectAttributes redirectAtt,HttpServletRequest request) {
		memberBeanValidator.validate(mb, result);

		// 有錯誤訊息返回 register.jsp
		if (result.hasErrors()) {
			// 清空密碼
			mb.setPassword("");
			mb.setPassword1("");
			return registerForm;
		}

//		if(memberService.idExists(mb.getEmail())) {
//			memberService.rejectValue("email", "" , "E-mail已存在，請重新輸入");
//			return inputDataForm;
//		}

		// ------------------------- 檔案上傳處理 ------------------------
		MultipartFile memberImage = mb.getMemberMultipartFile();
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

		// 註冊日期
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		mb.setMeCreate(currentDate);

		// 登入時間
		Timestamp loginTime = new Timestamp(System.currentTimeMillis());
		mb.setLoginTime(loginTime);

		// 會員等級設定
		mb.setLevels(LEVELS);

		if (memberService.idExists(mb.getEmail())) {
			result.rejectValue("email", "", "email已存在，請重新輸入");
			// 清空密碼
			mb.setPassword("");
			mb.setPassword1("");
			return registerForm;
		}

		// 處理密碼加密
		mb.setPassword(GlobalService.getSHA512Endocing(GlobalService.encryptString(mb.getPassword())));

		// ------------------- 驗證沒問題，存進去資料庫 -----------------
		try {
			memberService.saveMember(mb);
			redirectAtt.addFlashAttribute("InsertOK", "<Font color='blue'>註冊成功，請開始使用本系統</Font>");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ", ErrorMessage =" + e.getMessage());
			result.rejectValue("email", "", "發生異常，請通知系統人員..." + e.getMessage());
			// 清空密碼
			mb.setPassword("");
			mb.setPassword1("");
			return registerForm;
		}

		if (originalFilename != null && originalFilename.length() > 0) {
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			String rootDirectory = context.getRealPath("/");
			try {
				File imageFolder = new File(rootDirectory, "headPic");
				if (!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				File file = new File(imageFolder, mb.getMemId() + ext);
				memberImage.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		new Thread(()->{
			rs.send("Car98會員註冊成功", "歡迎來到Car98，\n會員"+mb.getName()+"您於"+mb.getMeCreate()+"的時候完成註冊。\n您的註冊帳號為"+mb.getEmail()
			+"\n如有任何問題，請於上班時間與客服聯繫，謝謝!\n可以開始使用本系統:http://localhost:8080/Car98-mvc/", mb.getEmail());
		}).start();
		return "redirect:/";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		return "/login/login";
	}

//	@RequestMapping("/logout")
//	public String logout(Model model) {
//		return "/login/logout";
//	}

	@GetMapping("/management")
	public String management(Model model) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		// 清除密碼 停在表單
		memberBean.setPassword("");
		memberBean.setPassword1("");
		model.addAttribute(memberBean);
		return "/management/user";
	}

	@PostMapping("/management")
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
		return "redirect:/";
	}

	@PostMapping("/changePassword")
	public String changePassword(@ModelAttribute("memberBean") MemberBean memberBean, Model model,
			BindingResult result,
			RedirectAttributes redirectAtt) {
		changePasswordValidator.validate(memberBean, result);
		MemberBean mb = memberService.queryMember(memberBean.getMemId());
		if (result.hasErrors()) {
			model.addAttribute("inputError","inputError");
			return "/management/user";
		}
		if (memberBean.getPassword() != null && memberBean.getPassword().length() > 0) {

			mb.setPassword(GlobalService.getSHA512Endocing(GlobalService.encryptString(memberBean.getPassword())));

			memberService.updateUserData(mb);
		}
		new Thread(()->{
			rs.send("Car98會員修改密碼成功", "您於"+mb.getLoginTime()+"的時候完成密碼修改。"
					+"\n如有任何問題，請於上班時間與客服聯繫，謝謝!", mb.getEmail());
		}).start();
		return "redirect:/PasswordUpdateSuccess_Logout";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), true, 10));
	}
	

}
