package com.web.car98.conven.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.web.car98.conven.model.Fuel;
import com.web.car98.conven.model.FuelPriceBean;
import com.web.car98.conven.service.FuelService;
import com.web.car98.member.model.MemberBean;

@Controller
@RequestMapping("/config")
@SessionAttributes("LoginOK")
public class FuelController {
	@Autowired
	ServletContext context;

	@Autowired
	FuelService service;

	@ModelAttribute
	public void getFuel(@PathVariable(value = "fuelId", required = false) Integer id, Model model) {
		if (id != null) {
			Fuel fu = service.getFuelById(id);
			model.addAttribute("fuel", fu);
		} else {
			Fuel fu = new Fuel();
			model.addAttribute("fuel", fu);
		}
	}

	@ModelAttribute("typeList")
	public Map<Integer, String> getItemList() {
		Map<Integer, String> typeMap = new HashMap<>();
		List<FuelPriceBean> list = service.getTypeList();
		for (FuelPriceBean fb : list) {
			typeMap.put(fb.getTypeNo(), fb.getType());
		}
		return typeMap; // model.addAttribute("",)
	}

	// 顯示油耗紀錄
	@RequestMapping("/fuels")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
		model.addAttribute("fuels", service.getByMemId(memberBean.getMemId()));

		return "config/showFuels";
	}

	// 傳空白新增表單
	@GetMapping("/fuels/add")
	public String getAddNewFuelsForm(Model model) {
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
		Fuel fu = new Fuel();
		model.addAttribute("fuel", fu);
		return "config/updateFuels";
	}

	@PostMapping("/fuels/add")
	public String processAddNewFuelForm(@ModelAttribute("fuel") Fuel fu, Model model) {

		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		fu.setMemId(memberBean.getMemId());
		FuelPriceBean fuel = service.getFuelByPrice(fu.getFuelPriceBean().getTypeNo());

		double total = fu.getGallon() * fuel.getTypePrice();
		fu.setPrice((int) total);

		double consumption = fu.getMileage() / fu.getGallon();

		fu.setConsumption((int) (consumption * 100) / 100.0);

		service.insert(fu);

		return "redirect:/config/fuels";
	}

	// 刪除一筆紀錄
	// 由這個方法刪除記錄...
	@DeleteMapping("/fuels/add/{fuelId}")
	public String delete(@PathVariable("fuelId") Integer fuelId) {
		service.delete(fuelId);
		return "redirect:/config/fuels";
	}
}
