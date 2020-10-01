package com.web.car98.order.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.web.car98.order.model.OrderBean;
import com.web.car98.order.model.OrderItemBean;
import com.web.car98.order.service.OrderService;

@Controller
@RequestMapping("/comm")
@SessionAttributes("LoginOK")
public class UpdateOrder {
	
	@Autowired
	OrderService service;
	
	@Autowired
	ServletContext context;
	
	@ModelAttribute
	public void getBid(@PathVariable(value = "orderNo", required = false) Integer id, Model model) {
		if (id != null) {
			OrderBean oBean = service.getOrder(id);
			model.addAttribute("memberOrders", oBean);
		} else {
			OrderBean ob = new OrderBean();
			model.addAttribute("memberOrders", ob);
		}
	}

	
	@GetMapping(value = "/ProcessOrder/{orderNo}")
	public String showDataForm(@PathVariable("orderNo") Integer orderNo, Model model) {
		OrderBean oBean = service.getOrder(orderNo);
		List<OrderItemBean> oiBean = service.getAllItems(orderNo);
		model.addAttribute("memberOrder",oBean);
		model.addAttribute("memberOrders",oiBean);
		return "/comm/updateOrder";
	}
	

//	// 當將瀏覽器送來修改過的會員資料時，由本方法負責檢核，若無誤則寫入資料庫
//	@PostMapping("/ProcessOrder/{orderNo}")
//	// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
//	//
//	public String modify(@ModelAttribute("memberOrders") OrderBean ob, Model model,
//			@PathVariable("orderNo") Integer orderNo) {
//		Timestamp adminTime = new Timestamp(System.currentTimeMillis());
//		ob.setBuyDay(adminTime);
//		service.updateOrder(ob);
//		return "redirect:/comm/ProcessOrder";
//	}
	// 刪除一筆紀錄
		// 由這個方法刪除記錄...
		@DeleteMapping("/ProcessOrder/{orderNo}")
		public String delete(@PathVariable("orderNo") Integer orderNo) {
			service.delete(orderNo);
			return "redirect:/comm/orderList";
		}

}
