package com.web.car98.forum.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.car98.forum.model.CommentBean;
import com.web.car98.forum.model.TalkBean;
import com.web.car98.forum.service.TalkService;

@Controller
//@SessionAttributes("LoginOK")
public class TalkController {
	@Autowired
	TalkService talkservice;
	@Autowired
	ServletContext servletContext;
	int postID = 1;

	@GetMapping("/forum/talktop.do")
	public String list(Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "type", required = false) String type,
			@ModelAttribute("talkBean") TalkBean talkBean,
			CommentBean cb) {
		int lastpage = 0;
		if (pageNo == null) {
			pageNo = 1;
		}
		List<TalkBean> list = new ArrayList<>();
		model.addAttribute("type", type);
		if (type != null && type.length() != 0) {
			type = talkservice.intToType(type);
		}
		if (cb.getSearch() != null&&cb.getSearch().length()!=0) {
			String search=cb.getSearch();
			lastpage = talkservice.searchlastpage(search);
			list = talkservice.getSearchList(talkservice.getAll(), search);
			list = talkservice.getPage(list, pageNo);
		} else {
			lastpage = talkservice.lastpage(type);
			list = talkservice.getPageByType(pageNo, type);
		}
		model.addAttribute("search", cb.getSearch());
		model.addAttribute("abean", list);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("lastPage", lastpage);

		return "/forum/carTalk";

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
				fileName = "car98logo.png";
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
