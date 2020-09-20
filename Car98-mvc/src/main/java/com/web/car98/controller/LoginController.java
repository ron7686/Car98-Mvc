package com.web.car98.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.model.LoginBean;
import com.web.car98.model.MemberBean;
import com.web.car98.service.MemberService;
import com.web.car98.validator.LoginBeanValidator;

import _00_init.util.GlobalService;

@Controller
@SessionAttributes("LoginOK")
public class LoginController {
	String loginForm = "/login/login";
	String loginOut = "/login/logout";
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public String login(
			HttpServletRequest request,Model model,
			@CookieValue(value="user", required = false) String user,
			@CookieValue(value="password", required = false) String password,
			@CookieValue(value="rm", required = false) Boolean rm
			) {
		if(user == null) {
			user = "";
		}
		if(password == null) {
			password = "";
		}else {
			password = GlobalService.decryptString(GlobalService.KEY,password);
		}
		if(rm!=null) {
			rm = Boolean.valueOf(rm);
		}else {
			rm = false;
		}
		LoginBean loginBean = new LoginBean(user,password,rm);
		model.addAttribute(loginBean);
		
		return loginForm;
	}
	
	@PostMapping("/login")
	public String loginAccountCheck(
			@ModelAttribute("loginBean") LoginBean loginBean,
			BindingResult result,Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
			LoginBeanValidator validator = new LoginBeanValidator();
			validator.validate(loginBean, result);
			if(result.hasErrors()) {
				return loginForm;
			}
			MemberBean mb = null;
			try {
				mb = memberService.checkIdPassword(
						loginBean.getUser(), 
						GlobalService.getMD5Endocing(GlobalService.encryptString(loginBean.getPassword())));
				if(mb!=null) {
					memberService.updateLoginTime(mb.getEmail());
					model.addAttribute("LoginOK",mb);
				}else {
					result.rejectValue("invalidCredentials", "", "該帳號不存在或密碼錯誤");
					return loginForm;
				}
			} catch (Exception e) {
				result.rejectValue("invalidCredentials", "", e.getMessage());
				e.printStackTrace();
				return loginForm;
			}
			HttpSession session = request.getSession();
			processCookies(loginBean,request,response);
			String nextPath = (String)session.getAttribute("requestURI");
			if(nextPath==null) {
				nextPath = request.getContextPath();
			}
			return "redirect: "+nextPath;
	}

	private void processCookies(LoginBean loginBean, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		
		if(loginBean.isRememberMe()) {
			cookieUser = new Cookie("user",loginBean.getUser());
			cookieUser.setMaxAge(7 * 24 * 60 * 60);
			cookieUser.setPath(request.getContextPath());
			
			String encodePassword = GlobalService.encryptString(loginBean.getPassword());
			cookiePassword = new Cookie("password",encodePassword);
			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());
			
			cookieRememberMe = new Cookie("rm","true");
			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
		}else {
			cookieUser = new Cookie("user",loginBean.getUser());
			cookieUser.setMaxAge(0);
			cookieUser.setPath(request.getContextPath());
			
			String encodePassword = GlobalService.encryptString(loginBean.getPassword());
			cookiePassword = new Cookie("password",encodePassword);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());
			
			cookieRememberMe = new Cookie("rm","true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
	}
}
