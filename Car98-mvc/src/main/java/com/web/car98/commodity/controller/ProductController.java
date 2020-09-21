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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.model.BidItemBean;
import com.web.car98.commodity.service.ProductService;
import com.web.car98.commodity.validators.BidValidator;

@Controller
@RequestMapping("/comm")
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
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		BidBean bb = new BidBean();
		model.addAttribute("bid", bb);
		return "comm/addProduct";
	}

	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("bid") BidBean bb, BindingResult result) {
		bidValidator.validate(bb, result);
		if (result.hasErrors()) {
			return "comm/addProduct";
		}

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

//		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
//		String rootDirectory = context.getRealPath("/");
//		try {
//			File imageFolder = new File(rootDirectory, "images");
//			if (!imageFolder.exists())
//				imageFolder.mkdirs();
//			File file = new File(imageFolder, bb.getBidId() + ext);
//			productImage.transferTo(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
//		}
		return "redirect:comm/products";
	}

	// 當使用者需要修改時，本方法送回含有會員資料的表單，讓使用者進行修改
	// 由這個方法送回修改記錄的表單...
	@GetMapping(value = "/products/add/{bidId}")
	public String showDataForm(@PathVariable("bidId") Integer bidId, Model model) {
		BidBean bid = service.getProductById(bidId);
		model.addAttribute(bid);
		return "/comm/updateProduct";
	}

	// 當將瀏覽器送來修改過的會員資料時，由本方法負責檢核，若無誤則寫入資料庫
	@PostMapping("/products/add/{bidId}")
	// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
	//
	public String modify(@ModelAttribute("bid") BidBean bb, Model model, BindingResult result,
			@PathVariable("bidId") Integer bidId) {

		bidValidator.validate(bb, result);
		if (result.hasErrors()) {
			System.out.println("result hasErrors(), member=" + bb);
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println("有錯誤：" + error);
			}	
			return "comm/updateProduct";
		}
		Timestamp adminTime = new Timestamp(System.currentTimeMillis());
		bb.setBidTime(adminTime);

		MultipartFile picture = bb.getProductImage();
		if (picture.getSize() == 0) {
			// 表示使用者並未挑選圖片
			BidBean original = service.getProductById(bidId);
			bb.setBidPic(original.getBidPic());
		} else {
			String originalFilename = picture.getOriginalFilename();
			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
				bb.setFileName(originalFilename);
			}

			// 建立Blob物件
			if (picture != null && !picture.isEmpty()) {
				try {
					byte[] b = picture.getBytes();
					Blob blob = new SerialBlob(b);
					bb.setBidPic(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
				}
			}
		}

		service.updateProducts(bb);
		return "redirect:comm/showUpdate";
	}

	// 刪除一筆紀錄
	// 由這個方法刪除記錄...
	@DeleteMapping("/products/add/{bidId}")
	public String delete(@PathVariable("bidId") Integer bibId) {
		service.delete(bibId);
		return "redirect:comm/showUpdate";
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
