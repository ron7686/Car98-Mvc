package com.web.car98.forum.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.CommentService;
import com.web.car98.forum.service.TalkService;
import com.web.car98.member.model.MemberBean;
import com.web.car98.member.service.MemberService;
import com.web.car98.validator.CommentValidator;

@Controller
@SessionAttributes({ "LoginOK", "CommentBean", "TalkBean", "pageNo" })
public class CommentController {

	@Autowired
	CommentService commentservice;

	@Autowired
	CommentValidator commentValidator;

	@Autowired
	TalkService ts;

	@Autowired
	MemberService memberService;

	@Autowired
	ServletContext servletContext;

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

	// 新增留言
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
		commentBean.setMemberBean(memberBean);
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

	// 刪除留言
	@RequestMapping("/forum/deleteCom")
	public String deleteCom(Model model, @RequestParam("comId") Integer comId, @RequestParam("postID") Integer postID) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}

//    	String comIdStr = request.getParameter("comId");
//		Integer comId = Integer.valueOf(comIdStr);
//		CommentServiceImpl service = new CommentServiceImpl();
		commentservice.deleteComByPk(comId);
		return "redirect:/talktalk?postID=" + postID;
//		return "/forum/talktalk";
	}

	// 取出此留言的留言者為哪個會員 進而取出該會員頭像
	@GetMapping("/getComMemberImage")
	public ResponseEntity<byte[]> getComMemberImage(@RequestParam("comId") Integer comId) {
		InputStream is = null;
		OutputStream os = null;
		String fileName = null;
		String mimeType = null;
		byte[] media = null;
		ResponseEntity<byte[]> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = null;
		Blob blob = null;
		try {
			// 取出此留言的留言者為哪個會員 進而取出該會員頭像
			MemberBean bean = commentservice.queryMemberByComId(comId);

			if (bean != null) {
				blob = bean.getHeadPic();
				if (blob != null) {
					is = blob.getBinaryStream();
				}
				fileName = bean.getFileName();
			}
			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)
			if (is == null) {
				fileName = "NoImage.png";
				is = servletContext.getResourceAsStream("/images/" + fileName);
			}
			// 由圖片檔的檔名來得到檔案的MIME型態
			mimeType = servletContext.getMimeType(fileName);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 由InputStream讀取位元組，然後由OutputStream寫出
			int len = 0;
			byte[] bytes = new byte[81920];

			while ((len = is.read(bytes)) != -1) {
				baos.write(bytes, 0, len);
			}
			media = baos.toByteArray();
			mediaType = MediaType.valueOf(mimeType);
			// 連線不要Cache在裡面
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			headers.setContentType(mediaType);
			responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getComMemberImage 發生Exception: " + e.getMessage());
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				;
			}
			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				;
			}
		}
		return responseEntity;
	}
}
