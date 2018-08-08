package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourCityDTO;
import com.wellbroad.db.beans.tour.TourDAO;

// /adminTourCityInsertPro.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourCityInsertProAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		//1.AdminTourCityInsertPro.jsp가 처리했던 소스코드를 액션클래스에서 처리 -> 메서드 호출 -> DB -> 결과
		TourCityDTO cityToInsert = new TourCityDTO();
		cityToInsert.setCode_nation(request.getParameter("code_nation"));
		cityToInsert.setCode_city(request.getParameter("code_city").toLowerCase());
		cityToInsert.setName(request.getParameter("name"));
		cityToInsert.setDescription(request.getParameter("description"));
		System.out.println("******[AdminTourCityInsertProAction] ************ "+cityToInsert.getCode_city());
		

		//2-1.삽입
		TourDAO tourManager = new TourDAO();
		int insertCityCheck= tourManager.insertTourCity(cityToInsert);
		
		//3.정해진 view로 이동
		request.setAttribute("check", insertCityCheck);	
		request.setAttribute("movepage", "adminTourCity.do");
		request.setAttribute("errorpage", "adminTourCityInsert.do");
		
		return "/admin/adminTourInsertPro.jsp";
	}
}
