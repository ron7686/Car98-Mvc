package com.web.car98.forum.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.CommentService;
import com.web.car98.forum.service.TalkService;
import com.web.car98.member.model.MemberBean;
import com.web.car98.validator.CommentValidator;

@Controller
@SessionAttributes("LoginOK")
public class CommentController {

	@Autowired
	CommentService commentservice;
	
	@Autowired
	CommentValidator commentValidator;
	@Autowired
	TalkService ts;
	
	@SuppressWarnings("unused")
	@GetMapping("/talktalk")
	public String spaceCom(Model model,
		@RequestParam("postID") Integer postId) {
		CommentBean cb= new CommentBean();
		model.addAttribute("commentBean",cb);
		model.addAttribute("TalkBean",ts.selectOne(postId));
		try {
			model.addAttribute("CommentBean",commentservice.selectCom(postId));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "/forum/talktalk";
	}

	@PostMapping("/talktalk")
	public String insertCom(Model model,
			@ModelAttribute("commentBean") CommentBean cb)
			{
		
			commentservice.insertCom(cb);
			List<CommentBean> resultList = new ArrayList<>();
			try {
				resultList = commentservice.selectCom(cb.getPostId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("TalkBean",ts.selectOne(cb.getPostId()));
			model.addAttribute("CommentBean", resultList);
			//model.addAttribute("floor",floor);
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
