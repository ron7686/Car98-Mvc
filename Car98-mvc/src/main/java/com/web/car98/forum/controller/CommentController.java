package com.web.car98.forum.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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
import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.LikeOrHateBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.CommentService;
import com.web.car98.forum.service.TalkService;
import com.web.car98.member.model.MemberBean;
import com.web.car98.member.service.MemberService;
import com.web.car98.validator.CommentValidator;

@Controller(value ="CommentController")
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
		LikeOrHateBean loh=new LikeOrHateBean();
		model.addAttribute("commentBean", cb);
		model.addAttribute("TalkBean", ts.selectOne(postId));
		// ra.addFlashAttribute("TalkBean",ts.selectOne(postId));
		if (pageNo == null) {
			pageNo = 1;
		}

		MemberBean memberBean=(MemberBean) model.getAttribute("LoginOK");
		if(memberBean==null) {
			return "redirect:/login";
		}
		List<CommentBean> resultList;
		//try {
			resultList = commentservice.getPageCom(pageNo, postId,memberBean.getMemId());
		//} catch (Exception e1) {
			
		//	e1.printStackTrace();
		//}
		model.addAttribute("CommentBean", resultList);
	
		try {
			loh=ts.getOneLoh(postId,memberBean.getMemId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("loh",loh);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("lastPage", commentservice.getLastPage(postId, pageNo));
		return "/forum/talktalk";
	}

	// 新增留言
	@PostMapping("/talktalk")
	public String insertCom(Model model,
			@ModelAttribute("TalkBean") TalkBean tb,
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
	    SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	    String comTime = sdf.format(new Date(System.currentTimeMillis()));
	    cb.setComTime(comTime);			
		commentservice.insertCom(cb);
		model.addAttribute("CommentBean", commentservice.getComsByFk(tb.getPostID()));
	
		
		return "redirect:/talktalk?postID=" + pID;
	}
	
	@RequestMapping("/like")
	public String likeOrHate(Model model,
			@RequestParam(value="tf") Integer tf,
			@RequestParam(value = "postId", required = false) Integer postId,
			@RequestParam(value = "loh",required = false) Integer lohid
			) {
		LikeOrHateBean loh=new LikeOrHateBean();
		MemberBean memberBean=(MemberBean) model.getAttribute("LoginOK");
		TalkBean talkBean=ts.selectOne(postId);
		loh.setAa(lohid);
		loh.setLikeOrHate(tf);
		loh.setMemberBean(memberBean);
		loh.setTalkBean(talkBean);
		commentservice.saveLike(loh);
		
		return "redirect:/talktalk?postID=" + postId;
		
	}
	
	//留言點讚
	@RequestMapping("/comlike")
	public String ComLikeOrHate(Model model,
			@RequestParam(value="tf") Integer tf,
			@RequestParam(value = "postId", required = false) Integer postId,
			@RequestParam(value = "comId", required = false) Integer comId,
			@RequestParam(value = "comLohId",required = false) Integer comLohId
			) {
		ComLikeOrHateBean cloh=new ComLikeOrHateBean();
		MemberBean memberBean=(MemberBean) model.getAttribute("LoginOK");
		CommentBean commentBean=commentservice.selectComByPk(comId);
		System.out.println(comLohId+"==========================");
		cloh.setComLohId(comLohId);
		cloh.setComLikeOrHate(tf);
		cloh.setMemberBean(memberBean);
		cloh.setCommentBean(commentBean);
		commentservice.saveComLike(cloh);
		
		return "redirect:/talktalk?postID=" + postId;
		
	}

//	@PostMapping("/forum/updateCom")
	@PostMapping("/updateCom")
	@ResponseBody
	public String updateCom(Model model, @ModelAttribute("commentBean") CommentBean cb,
			@RequestBody CommentBean commentBean,
			@RequestParam("comId") Integer comId,
			@RequestParam("postID") Integer postID) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
		CommentBean commentbean = commentservice.selectComByPk(comId);
		commentservice.updateComByPk(commentbean);
		return "redirect:/talktalk?postID=" + postID;

	}

	// 刪除留言
	@RequestMapping("/forum/deleteCom")
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
