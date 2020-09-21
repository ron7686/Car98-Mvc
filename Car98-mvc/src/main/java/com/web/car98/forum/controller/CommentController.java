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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.service.CommentService;
import com.web.car98.forum.service.impl.CommentServiceImpl;

@Controller
public class CommentController {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	CommentService commentservice;

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String insertCom(Model model ,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int floor = 2;
		Map<String, String> errorMessage = new HashMap<>();
		request.setAttribute("ErrorMsgKey", errorMessage);
		request.setCharacterEncoding("UTF-8");
		Integer comId = null;
		String comText = request.getParameter("comments");
		Integer postId = 1;
		Integer memId = 36;
		Integer comLike = 6;
		if (comText == null || comText.trim().length() == 0) {
			errorMessage.put("TextEmptyError", "請輸入內文");
		}
		if (errorMessage.isEmpty()) {
//			CommentBean cb = new CommentBean(postId, comText, new Date(), comLike);
			try {
				CommentServiceImpl service = new CommentServiceImpl();
//				service.insertCom(cb);
				List<CommentBean> resultList = new ArrayList<>();
				resultList = service.selectCom(postId);
				request.setAttribute("CommentBean", resultList);
				request.setAttribute("floor", floor);
				RequestDispatcher rd = request.getRequestDispatcher("/forum/talktalk.jsp");
				rd.forward(request, response);
//				return;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/forum/talktalk.jsp");
			rd.forward(request, response);
//			return;
		}
		return "/comment";
	}

}
