package com.web.car98.forum.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.car98.forum.model.ComLikeOrHateBean;
import com.web.car98.forum.model.CommentAllBean;
import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.LikeOrHateBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.CommentService;
import com.web.car98.forum.service.TalkService;
import com.web.car98.member.model.MemberBean;
import com.web.car98.member.service.MemberService;
import com.web.car98.validator.CommentValidator;

import _00_init.util.GlobalService;

@Controller(value = "CommentController")
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
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "floorNo", required = false) Integer floorNo) {
		CommentBean cb = new CommentBean();
		LikeOrHateBean loh = new LikeOrHateBean();
		ts.setView(postId);
		TalkBean tb = ts.selectOne(postId);
		model.addAttribute("commentBean", cb);
		model.addAttribute("TalkBean", tb);
		// ra.addFlashAttribute("TalkBean",ts.selectOne(postId));
		if (pageNo == null) {
			pageNo = 1;
		}

		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
		List<CommentBean> resultList;
		// try {

		resultList = commentservice.getPageCom(pageNo, postId, memberBean.getMemId());
		if (resultList.size() == 0 && pageNo != 1) {
			resultList = commentservice.getPageCom(--pageNo, postId, memberBean.getMemId());
		}
		// } catch (Exception e1) {

		// e1.printStackTrace();
		// }
		model.addAttribute("CommentBean", resultList);

		try {
			loh = ts.getOneLoh(postId, memberBean.getMemId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("loh", loh);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("lastPage", commentservice.getLastPage(postId));
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

		// ------------------------- 檔案上傳處理 ------------------------
		MultipartFile commentImage = cb.getCommentMultipartFile();
		String originalFilename = commentImage.getOriginalFilename();
		if (commentImage != null && !commentImage.isEmpty()) {
			try {
				byte[] b = commentImage.getBytes();
				Blob blob = new SerialBlob(b);
				cb.setFileName(originalFilename);
				cb.setComPic(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}

		String pID = String.valueOf(tb.getPostID());
//		cb.setPostId(tb.getPostID());
		cb.setTalkBean(tb);
		cb.setMemberBean(memberBean);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String comTime = sdf.format(new Date(System.currentTimeMillis()));
		cb.setComTime(comTime);
		commentservice.insertCom(cb);
		model.addAttribute("CommentBean", commentservice.getComsByFk(tb.getPostID()));
		String lastPage = String.valueOf(commentservice.getLastPage(tb.getPostID()));

		return "redirect:/talktalk?postID=" + pID + "&pageNo=" + lastPage;
	}

	@RequestMapping("/like")
	public String likeOrHate(Model model, @RequestParam(value = "tf") Integer tf,
			@RequestParam(value = "postId", required = false) Integer postId,
			@RequestParam(value = "loh", required = false) Integer lohid) {
		LikeOrHateBean loh = new LikeOrHateBean();
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		TalkBean talkBean = ts.selectOne(postId);
		loh.setAa(lohid);
		loh.setLikeOrHate(tf);
		loh.setMemberBean(memberBean);
		loh.setTalkBean(talkBean);
		commentservice.saveLike(loh);

		return "redirect:/talktalk?postID=" + postId;

	}

	@RequestMapping("/deletePost")
	public String deletePost(Model model, @RequestParam("postId") Integer postId) {
		ts.deletePost(postId);
		return "redirect:/forum/talktop.do";

	}

	// 留言點讚
	@RequestMapping("/comlike")
	public String ComLikeOrHate(Model model, @RequestParam(value = "tf") Integer tf,
			@RequestParam(value = "postId", required = false) Integer postId,
			@RequestParam(value = "comId", required = false) Integer comId,
			@RequestParam(value = "comLohId", required = false) Integer comLohId) {
		ComLikeOrHateBean cloh = new ComLikeOrHateBean();
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		CommentBean commentBean = commentservice.selectComByPk(comId);
		System.out.println(comLohId + "==========================");
		cloh.setComLohId(comLohId);
		cloh.setComLikeOrHate(tf);
		cloh.setMemberBean(memberBean);
		cloh.setCommentBean(commentBean);
		commentservice.saveComLike(cloh);

		return "redirect:/talktalk?postID=" + postId;

	}

//	@PostMapping("/updateCom")
////	@ResponseBody
//	public String updateCom(
//			Model model,
//			@ModelAttribute("commentBean")CommentBean commentBean) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			return "redirect:/login";
//		}
//		CommentBean commentbean = commentservice.selectComByPk(commentBean.getComId());
//		commentbean.setComText(commentBean.getComText());
//
//		//圖片
//		MultipartFile commentImage = commentbean.getCommentMultipartFile();
//		String originalFilename = commentImage.getOriginalFilename();
//		if (commentImage != null && !commentImage.isEmpty()) {
//			try {
//				byte[] b = commentImage.getBytes();
//				Blob blob = new SerialBlob(b);
//				commentbean.setFileName(originalFilename);
//				commentbean.setComPic(blob);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//			}
//		}
//		commentservice.updateComByPk(commentbean);
//		return "redirect:/talktalk?postID=" + commentBean.getTalkBean().getPostID();
//
//	}

	@PostMapping("/updateCom")
	@ResponseBody
	public String updateCom(Model model, @RequestBody CommentAllBean commentAllBean) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
		CommentBean commentbean = commentservice.selectComByPk(commentAllBean.getComId());
		commentbean.setComText(commentAllBean.getComText());

		commentservice.updateComByPk(commentbean);
		return "redirect:/talktalk?postID=" + commentAllBean.getPostID();

	}

//	@PostMapping("/updateCom")
//	@ResponseBody
//	public String updateCom(Model model, HttpServletRequest request)
//			throws IOException, ServletException {
//
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		TalkBean talkBean = (TalkBean) model.getAttribute("TalkBean");
//
//		if (memberBean == null) {
//			return "redirect:/login";
//		}
//
//		Collection<Part> parts = request.getParts();
//		GlobalService.exploreParts(parts, request);
//		String comText = "";// 留言內容
//		String comPicName = ""; // 圖片檔名
//
//		String comIdStr = request.getParameter("comId");
//		Integer comId = null;
//		
//		if (comIdStr != null) {
//			comId = Integer.parseInt(comIdStr);
//		}
//		
//		comText = request.getParameter("updateText"+comId);
//		
//
//		if (parts != null) { // 如果這是一個上傳資料的表單
//			for (Part p : parts) {
//				String fldName = p.getName();
//				System.out.println("fldName = " + fldName);
//				String value = null;
//				value = request.getParameter(fldName);
//				// 1. 讀取使用者輸入資料
//				if (p.getContentType() == null) {
//					if (fldName.equalsIgnoreCase("updateText" + comId)) {
//						comText = value;
//					}
//				}
//			}
//		}
//		
//		// 圖檔
//		if (parts != null) { // 如果這是一個上傳資料的表單
//			if (!new File("d:\\comPic").exists()) {
//				new File("d:\\comPic").mkdirs();
//			}
//			for (Part p : parts) {
//				if (p.getContentType() != null) {
//					String fileName = GlobalService.getFileName(p);
//					long sizeInBytes = 0;
//					InputStream is = null;
//					if (fileName != null && fileName.trim().length() > 0) {
//						fileName = System.currentTimeMillis()
//								+ GlobalService.getFileName(p).substring(GlobalService.getFileName(p).indexOf("."));
//						comPicName += fileName + ",";
//						sizeInBytes = p.getSize();
//						is = p.getInputStream();
//						int len = 0;
//						byte[] byteArray = new byte[(int) sizeInBytes];
//						File uploadFile = new File("d:\\comPic", fileName);
//						try (FileOutputStream fos = new FileOutputStream(uploadFile);) {
//							while ((len = is.read(byteArray)) != -1) {
//								fos.write(byteArray, 0, len);
//							}
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//
//					} else {
//						comPicName = null;
//					}
//				}
//			}
//			if (comPicName != null && comPicName.trim().length() != 0) {
//				comPicName = comPicName.substring(0, comPicName.length() - 1);
//			}
//		}
//		
//		CommentBean bean = commentservice.selectComByPk(comId);
//
//		if (!comText.equalsIgnoreCase("")) {
//			bean.setComText(comText);
//		}
//
//		if (!comPicName.equalsIgnoreCase("")) {
//			bean.setFileName(comPicName);
//		}
//
//		Integer n = commentservice.updateComByPk(bean);
////
////		if (n == 1) {
////			return "redirect:/talktalk?postID=" + talkBean.getPostID();
////		} else {
////			return "redirect:/";
////		}
//		return "redirect:/talktalk?postID=" + talkBean.getPostID();
//	}

	// 刪除留言
	@RequestMapping("/deleteCom")
	public String deleteCom(Model model, @RequestParam("comId") Integer comId, @RequestParam("postID") Integer postID) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}

		commentservice.deleteComByPk(comId);
		return "redirect:/talktalk?postID=" + postID;

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
				is = servletContext.getResourceAsStream("/image/" + fileName);
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

	// 取出此文章的發文者為哪個會員 進而取出該會員頭像
	@GetMapping("/getPostMemberImage")
	public ResponseEntity<byte[]> getPostMemberImage(@RequestParam("postID") Integer postID) {
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
			// 取出此文章的發文者為哪個會員 進而取出該會員頭像
			MemberBean bean = commentservice.queryMemberByPostId(postID);

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
				is = servletContext.getResourceAsStream("/image/" + fileName);
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
			throw new RuntimeException("getPostMemberImage 發生Exception: " + e.getMessage());
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

	// 取出此留言的圖片
	@GetMapping("/getComImage")
	public ResponseEntity<byte[]> getComImage(@RequestParam("comId") Integer comId) {
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
			// 取出此留言 進而取出該留言的圖片
			CommentBean bean = commentservice.selectComByPk(comId);

			if (bean != null) {
				blob = bean.getComPic();
				if (blob != null) {
					is = blob.getBinaryStream();
				}
				fileName = bean.getFileName();
			}
			// 如果此留言沒有上傳圖片，就傳回null
			if (is == null) {
				return null;
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
			throw new RuntimeException("getComImage 發生Exception: " + e.getMessage());
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
