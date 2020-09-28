package com.web.car98.rentcar.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.car98.rentcar.model.CarTypeBean;
import com.web.car98.rentcar.model.RentCarBean;
import com.web.car98.rentcar.service.CarTypeService;
import com.web.car98.rentcar.service.RentCarService;

@Controller
//@RequestMapping(value = "/rentcar", method = RequestMethod.GET)
public class CarTypeServlet{

	@Autowired 
	CarTypeService carTypeService;
	
//	第三個下拉式選單
//	@RequestMapping(value = "/cartype", method = RequestMethod.GET) 舊的寫法
	@GetMapping("/cartype")
	protected @ResponseBody List<CarTypeBean> doGet(){
		List<CarTypeBean> list = this.carTypeService.showBrandTypeMenu();
		return list;
	

//		CarTypeServiceImpl cts = new CarTypeServiceImpl();
//		ServletContext sc = getServletContext();
//		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
//		CarTypeService cts = ctx.getBean(CarTypeService.class);
//		Collection<CarTypeBean> ctb = cts.getCarTypeData();
//
//		Gson gson = new Gson();
//		String carJsonString = gson.toJson(ctb);
//		PrintWriter out = response.getWriter();
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		out.print(carJsonString);
//		System.out.println(carJsonString);
//		out.flush();
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		//先取出session物件
//		HttpSession session = request.getSession(false); 
//		
//		// 如果舊的session物件不存在， 不要建立新的Session物件，直接傳回 null，請使用者登入
//		if (session == null) {
//			response.sendRedirect(response.encodeRedirectURL(
//					request.getContextPath() + "/login/BSlogin.jsp"));
//			return;
//		}
//		
//		// 取出存放在session物件內的RentCarBean物件
//		RentCarBean rcb = (RentCarBean)session.getAttribute("RentCarBean");
//		
//		// 如果找不到RentCarBean物件，就新建一個
//		if (rcb == null) {
//			rcb = new RentCarBean();
//			// 並將此新建RentCarBean的物件放到session物件內，成為它的屬性物件
//			session.setAttribute("queryRent", rcb);
//			}
//		
//		// 1. 讀取使用者輸入查詢的資料
//		String city = request.getParameter("city");
//		String district = request.getParameter("district") + "區";
//		
//		// 2. 進行必要的資料轉換
//		
//		// 3. 檢查資料
//		
//		// 4. 進行商業邏輯運算
////		RentCarService rentCarservice = new RentCarServiceImpl();
////		Spring的寫法		
////		RentCarService rentCarservice = ctx.getBean(RentCarService.class);
//		
//		// 5.依照商業邏輯運算的結果，來挑選適當的
//		RequestDispatcher rd = request.getRequestDispatcher("carRent.jsp");
//		rd.forward(request, response);
//		return;
//	}
//	
	
}
