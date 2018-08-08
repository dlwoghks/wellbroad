package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;

// /adminTourCityDeletePro.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourCityDeleteProAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		//1.삭제할 도시코드 배열
		String[] code_cityArr = request.getParameter("code_cityArr").toString().split(",");
		System.out.println("******[AdminTourCityDeleteProAction] ************ "+code_cityArr.length);
		request.setAttribute("movepage", "adminTourCity.do");
	
		
		//2.
		TourDAO tourManager = new TourDAO();
		for (int i=0; i<code_cityArr.length; i++) {
			if(!tourManager.deleteTourCity(code_cityArr[i])) {
				request.setAttribute("check", false);
				request.setAttribute("code", code_cityArr[i]);
				return "/admin/adminTourDeletePro.jsp";
			};
			
		}
		request.setAttribute("check", true);
		//3.정해진 view로 이동
		return "/admin/adminTourDeletePro.jsp";
	}
}
