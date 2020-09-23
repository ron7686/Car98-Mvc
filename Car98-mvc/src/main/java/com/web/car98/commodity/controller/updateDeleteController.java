package com.web.car98.commodity.controller;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import com.web.car98.commodity.model.BidBean;
import com.web.car98.commodity.model.BidItemBean;
import com.web.car98.commodity.service.ProductService;
import com.web.car98.commodity.validators.BidValidator;

@Controller
@RequestMapping("/comm")
public class updateDeleteController {
	@Autowired
	ProductService service;

	@Autowired
	ServletContext context;

	@Autowired
	BidValidator bidValidator;

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
		} else {
			BidBean bb = new BidBean();
			model.addAttribute("bid", bb);
		}
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
		return "redirect:/comm/showUpdate";
	}

	// 刪除一筆紀錄
	// 由這個方法刪除記錄...
	@DeleteMapping("/products/add/{bidId}")
	public String delete(@PathVariable("bidId") Integer bibId) {
		service.delete(bibId);
		return "redirect:/comm/showUpdate";
	}
}
