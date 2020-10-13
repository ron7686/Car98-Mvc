package com.web.car98.init.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.conven.model.FuelPriceBean;
import com.web.car98.conven.service.FuelService;
import com.web.car98.member.model.MemberBean;
import com.web.car98.member.service.MemberService;

@Controller
@SessionAttributes({"fuelPriceBean"})
public class InitController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	FuelService fuelService;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<FuelPriceBean> fp = fuelService.getTypeList();
		model.addAttribute("fuelPriceBean",fp);
		return "index";
	}
	
	@RequestMapping("/privacy")
	public String privacy(Model model) {
		return "privacy";
	}
	
	@RequestMapping("/terms")
	public String terms(Model model) {
		return "terms";
	}
	
	@RequestMapping("/car98Member")
	public String car98Member(Model model) {
		return "car98Member";
	}
	
	@GetMapping("/init/getMemberImage")
	public ResponseEntity<byte[]>  getMemberImage(@RequestParam("id") Integer id) {
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
			MemberBean bean = memberService.queryMember(id);
			if (bean != null) {
				blob = bean.getHeadPic();
				if (blob != null) {
					is = blob.getBinaryStream();
				}
				fileName = bean.getFileName();
			}
			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)	
			if (is == null) {
				fileName = "NoImage.png" ; 
				is = servletContext.getResourceAsStream(
						"/image/" + fileName);
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
			responseEntity =  new ResponseEntity<>(media, headers, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("init.getMemberImage 發生Exception: " + e.getMessage());
		} finally{
			try {
				if (is != null) is.close();
			} catch(IOException e) {
				;
			}
			try {
				if (os != null) os.close();
			} catch(IOException e) {
				;
			}
		}
		return responseEntity;
	}
}
