package com.web.car98.commodity.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.model.BidItemBean;
import com.web.car98.commodity.service.ProductService;
import com.web.car98.commodity.validators.BidValidator;
import com.web.car98.member.model.MemberBean;

@Controller
@RequestMapping("/comm")
@SessionAttributes("LoginOK")
public class ProductController {
	@Autowired
	ProductService service;

	@Autowired
	ServletContext context;

	@Autowired
	BidValidator bidValidator;

	// 顯示所有商品資料
	@RequestMapping("/products")
	public String list(Model model) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
		List<BidBean> list = service.getAllProducts();
		model.addAttribute("products", list);
		return "comm/products";
	}

	// 顯示所有商品資料新增用
	@GetMapping("/showUpdate")
	public String list1(Model model) {
		model.addAttribute("bids", service.getAllProducts());
		return "comm/bids/bids";
	}

	// 單獨商品分頁
	@RequestMapping("/product")
	public String getProductsById(@RequestParam Integer id, Model model) {
		model.addAttribute("product", service.getProductById(id));
		return "comm/product";
	}

	// 初始化
	@ModelAttribute("categoryList") // 所有modelAttribute傳回直都是model物件
	public Map<Integer, String> getItemList() {
		Map<Integer, String> itemMap = new HashMap<>();
		List<BidItemBean> list = service.getItemList();
		for (BidItemBean cb : list) {
			itemMap.put(cb.getBidItemId(), cb.getBidCategory());
		}
		return itemMap; // model.addAttribute("",)
	}

	@ModelAttribute
	public void getBid(@PathVariable(value = "bidId", required = false) Integer id, Model model) {
		if (id != null) {
			BidBean bb = service.getProductById(id);
			model.addAttribute("bid", bb);
		}else {
			BidBean bb = new BidBean();
			model.addAttribute("bid", bb);
		}	
	}

	// 傳空白新增表單
	@GetMapping("/products/add")
	public String getAddNewProductForm(Model model) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}		
		BidBean bb = new BidBean();
		model.addAttribute("bid", bb);
		return "comm/addProduct";
	}
	
	@PostMapping("/products/add")
	public String processAddNewProductForm(@ModelAttribute("bid") BidBean bb, BindingResult result,Model model) {
		bidValidator.validate(bb, result);
		if (result.hasErrors()) {
			return "comm/addProduct";
		}
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		bb.setMemberBean(memberBean);
		
		if (bb.getBidStock() == null) {
			bb.setBidStock(0);
		}

		MultipartFile productImage = bb.getProductImage();
		String originalFilename = productImage.getOriginalFilename();
		if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
			bb.setFileName(originalFilename);
		}
		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (productImage != null && !productImage.isEmpty()) {
			try {
				byte[] b = productImage.getBytes();
				Blob blob = new SerialBlob(b);
				bb.setBidPic(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		Timestamp adminTime = new Timestamp(System.currentTimeMillis());
		bb.setBidTime(adminTime);

	
		service.addByBidBean(bb);
		return "redirect:/comm/products";
	}

	



	// 取得圖片
	@RequestMapping(value = "/comm/picture/{bidId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer bidId) {
		byte[] body = null;
		ResponseEntity<byte[]> re = null;
		MediaType mediaType = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());

		BidBean bb = service.getProductById(bidId);
		if (bb == null) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
		String filename = bb.getFileName();
		if (filename != null) {
			if (filename.toLowerCase().endsWith("jfif")) {
				mediaType = MediaType.valueOf(context.getMimeType("dummy.jpeg"));
			} else {
				mediaType = MediaType.valueOf(context.getMimeType(filename));
				headers.setContentType(mediaType);
			}
		}
		Blob blob = bb.getBidPic();
		if (blob != null) {
			body = blobToByteArray(blob);
		} else {
			String path = "/image/NoImage.png";
			body = toByteArray(path);
		}
		re = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);

		return re;
	}

	private byte[] toByteArray(String path) {
		byte[] result = null;
		try (InputStream is = context.getResourceAsStream(path);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			byte[] b = new byte[819200];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			result = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public byte[] blobToByteArray(Blob blob) {
		byte[] result = null;
		try (InputStream is = blob.getBinaryStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			byte[] b = new byte[819200];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			result = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
