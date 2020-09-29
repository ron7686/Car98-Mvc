package com.web.car98.forum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.CommentService;
import com.web.car98.forum.service.TalkService;
import com.web.car98.member.model.MemberBean;
import com.web.car98.validator.CommentValidator;

@Controller
@SessionAttributes({"LoginOK","CommentBean","TalkBean","pageNo","lastPage"})
public class CommentController {

	@Autowired
	CommentService commentservice;

	@Autowired
	CommentValidator commentValidator;

	@Autowired
	TalkService ts;

	@SuppressWarnings("unused")
	@GetMapping("/talktalk")
	public String spaceCom(Model model, RedirectAttributes ra, @RequestParam("postID") Integer postId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		CommentBean cb = new CommentBean();
		model.addAttribute("commentBean", cb);
		model.addAttribute("TalkBean", ts.selectOne(postId));
		// ra.addFlashAttribute("TalkBean",ts.selectOne(postId));
		if (pageNo == null) {
			pageNo = 1;
		}
		List<CommentBean> resultList = commentservice.getPageCom(pageNo, postId);
		model.addAttribute("CommentBean", resultList);
//		for(CommentBean com :resultList) {			
//			System.out.println("postId = " + com.getPostId());
//		}		
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("lastPage", commentservice.getLastpage(postId));
		return "/forum/talktalk";
	}

	@PostMapping("/talktalk")
	public String insertCom(Model model, @ModelAttribute("TalkBean") TalkBean tb,
			@ModelAttribute("commentBean") CommentBean cb) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
	
		String pID = String.valueOf(tb.getPostID());
//		cb.setPostId(tb.getPostID());
		CommentBean commentBean = cb;
		commentBean.setTalkBean(tb);
		System.out.println("bean = " + commentBean.toString());
		commentservice.insertCom(commentBean);
		model.addAttribute("CommentBean", commentservice.selectCom(tb.getPostID()));
		// model.addAttribute("floor",floor);
		return "redirect:/talktalk?postID=" + pID;
	}

//	@RequestMapping(value = "/forum/updateCom.do", method = RequestMethod.POST)
//	@PostMapping("/talktalk")
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
//    	n = service.updateComByPk(commentBean);
//		if(n != 0) {
//			return "/forum/talktalk";
//		}else {
//			
//		}
//		return "/forum/talktalk";
//
//	}


	@GetMapping("/forum/deleteCom")
	public String deleteCom(Model model,
			@RequestParam("comId") Integer comId, 
			@RequestParam("postID") Integer postID) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
		
//    	String comIdStr = request.getParameter("comId");
//		Integer comId = Integer.valueOf(comIdStr);
//		CommentServiceImpl service = new CommentServiceImpl();
		commentservice.deleteComByPk(comId);
		return "redirect:/talktalk";
//		return "/forum/talktalk";
	}
}
