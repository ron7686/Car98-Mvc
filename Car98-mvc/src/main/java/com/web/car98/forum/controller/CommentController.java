package com.web.car98.forum.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.service.CommentService;
import com.web.car98.forum.service.impl.CommentServiceImpl;
import com.web.car98.member.model.MemberBean;
import com.web.car98.validator.CommentValidator;

@Controller
@SessionAttributes("LoginOK")
public class CommentController {

	@Autowired
	CommentService commentservice;
	
	@Autowired
	CommentValidator commentValidator;
	
	@GetMapping("/forum/comment.do")
	public String spaceCom(Model model) {
		CommentBean commentBean=new CommentBean();
			model.addAttribute("CommentBean",commentBean);
			MemberBean memberbean = (MemberBean)model.getAttribute("LoginOK");
			if(memberbean==null) {
				return "redirect:/login";
			}
		return "/forum/talktalk";
	}
	

	@PostMapping("/forum/comment.do")
	public String insertCom(Model model,
			@ModelAttribute("CommentBean") CommentBean commentBean,
			BindingResult bindingResult)
			 {
	
		    Integer postId = null;
		    
		    String[] suppressedFields=bindingResult.getSuppressedFields();
		    commentValidator.validate(commentBean, bindingResult);
			if(bindingResult.hasErrors()) {
				return "/forum/talktalk";
			}
		
			commentservice.insertCom(commentBean);
			List<CommentBean> resultList = new ArrayList<>();
			resultList = commentservice.selectCom(postId);
			model.addAttribute("CommentBean", resultList);
			return "/forum/talktalk";

	}		

//	@RequestMapping(value = "/forum/updateCom.do", method = RequestMethod.POST)
//	public String updateCom(Model model, HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		Map<String, String> errorMessage = new HashMap<>();
//		model.addAttribute("ErrorMsgKey", errorMessage);
//		request.setCharacterEncoding("UTF-8");
//		String comIdStr = request.getParameter("comId");
//		System.out.println(comIdStr);
//		Integer comId = Integer.valueOf(comIdStr);
//		CommentBean commentBean = null;
//		CommentServiceImpl service = new CommentServiceImpl();
//		
//		int n = 0;
//		n = service.updateComByPk(commentBean);
//		if(n != 0) {
//			return "/forum/talktalk";
//		}else {
//			
//		}
//		return "/forum/talktalk";
//
//	}
//
//	@RequestMapping(value = "/forum/deleteCom.do", method = RequestMethod.POST)
//	public String deleteCom(Model model, HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		Map<String, String> errorMessage = new HashMap<>();
//		model.addAttribute("ErrorMsgKey", errorMessage);
//		request.setCharacterEncoding("UTF-8");
//		String comIdStr = request.getParameter("comId");
//		Integer comId = Integer.valueOf(comIdStr);
//
//		CommentServiceImpl service = new CommentServiceImpl();
//		service.deleteComByPk(comId);
//		return "/forum/talktalk";
//	}
}
