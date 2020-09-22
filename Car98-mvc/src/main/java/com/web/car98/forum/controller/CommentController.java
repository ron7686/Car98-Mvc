package com.web.car98.forum.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.service.CommentService;
import com.web.car98.forum.service.impl.CommentServiceImpl;
import com.web.car98.member.model.LoginBean;

@Controller
public class CommentController {

	private static final long serialVersionUID = 1L;

	@Autowired
	CommentService commentservice;

	@RequestMapping(value = "/forum/comment.do", method = RequestMethod.POST)
	public String insertCom(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> errorMessage = new HashMap<>();
		model.addAttribute("ErrorMsgKey", errorMessage);
		request.setCharacterEncoding("UTF-8");
		LoginBean loginBean = new LoginBean();
		loginBean = (LoginBean) model.getAttribute("loginBean");
		Integer comId = null;
		String comText = request.getParameter("comments");
		Integer postId = Integer.parseInt(request.getParameter("postId"));
		Integer memId = Integer.parseInt(request.getParameter("memId"));
		Integer comLike = 0;
		if (comText == null || comText.trim().length() == 0) {
			errorMessage.put("TextEmptyError", "請輸入內文");
		}
		if (errorMessage.isEmpty()) {
			CommentBean cb = new CommentBean(postId, memId, comText, new Date(), comLike);

			CommentServiceImpl service = new CommentServiceImpl();
			service.insertCom(cb);
			List<CommentBean> resultList = new ArrayList<>();
			resultList = service.selectCom(postId);
			model.addAttribute("CommentBean", resultList);
			return "/forum/talktalk";

		} else {

			return "/forum/talktalk";
		}
	}

	@RequestMapping(value = "/forum/updateCom.do", method = RequestMethod.POST)
	public String updateCom(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, String> errorMessage = new HashMap<>();
		model.addAttribute("ErrorMsgKey", errorMessage);
		request.setCharacterEncoding("UTF-8");
		String comIdStr = request.getParameter("comId");
		System.out.println(comIdStr);
		Integer comId = Integer.valueOf(comIdStr);
		CommentBean commentBean = null;
		CommentServiceImpl service = new CommentServiceImpl();
		
		int n = 0;
		n = service.updateComByPk(commentBean);
		if(n != 0) {
			return "/forum/talktalk";
		}else {
			
		}
		return "/forum/talktalk";

	}

	@RequestMapping(value = "/forum/deleteCom.do", method = RequestMethod.POST)
	public String deleteCom(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, String> errorMessage = new HashMap<>();
		model.addAttribute("ErrorMsgKey", errorMessage);
		request.setCharacterEncoding("UTF-8");
		String comIdStr = request.getParameter("comId");
		Integer comId = Integer.valueOf(comIdStr);

		CommentServiceImpl service = new CommentServiceImpl();
		service.deleteComByPk(comId);
		return "/forum/talktalk.jsp";
	}
}
