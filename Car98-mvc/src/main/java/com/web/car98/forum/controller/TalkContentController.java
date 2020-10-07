package com.web.car98.forum.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.TalkService;
import com.web.car98.member.model.MemberBean;
import com.web.car98.validator.TalkContentValidator;

@Controller
@SessionAttributes("LoginOK")
public class TalkContentController {
	@Autowired	
	TalkService talkservice;
	@Autowired
	TalkContentValidator talkContentValidator;
	@Autowired
	ServletContext servletContext;
	
	@GetMapping("/talkContent")
	public String talkContent(Model model) {
			TalkBean talkBean=new TalkBean();
			model.addAttribute("talkBean",talkBean);
			MemberBean memberBean=(MemberBean) model.getAttribute("LoginOK");
			if(memberBean==null) {
				return "redirect:/login";
			}
		
		return "forum/talkContent";
	}
	
	@PostMapping("/talkContent")
	public String addContentForm(Model model,
			@ModelAttribute("talkBean") TalkBean tb,
			BindingResult bindingResult) {
		String[] suppressedFields=bindingResult.getSuppressedFields();
		talkContentValidator.validate(tb, bindingResult);
		if(bindingResult.hasErrors()) {
			return "forum/talkContent";
		}
		MemberBean mb=(MemberBean) model.getAttribute("LoginOK");
		
		tb.setMemberBean(mb);
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	    String PostTime = sdf.format(new Date(System.currentTimeMillis()));
	    tb.setPostTime(PostTime);				
	    
	    //檔案上傳
	    MultipartFile postPic = tb.getPostMultipartFile();
		String originalFilename = postPic.getOriginalFilename();
		if (postPic != null && !postPic.isEmpty()) {
			try {
				byte[] b = postPic.getBytes();
				Blob blob = new SerialBlob(b);
				tb.setPostFileName(originalFilename);
				tb.setPostPic(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}		
		
		talkservice.persist(tb);
		model.addAttribute("TalkBean", tb);
		return "redirect:/forum/talktop.do";
		
	}
	
	@GetMapping("/getpostPic")
	public ResponseEntity<byte[]> getPostPic(@RequestParam("postID") Integer postID) {
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
			TalkBean talkbean = talkservice.selectOne(postID);

			if (talkbean != null) {
				blob = talkbean.getPostPic();
				if (blob != null) {
					is = blob.getBinaryStream();
				}
				fileName = talkbean.getPostFileName();
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
			throw new RuntimeException("getPostPic 發生Exception: " + e.getMessage());
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
