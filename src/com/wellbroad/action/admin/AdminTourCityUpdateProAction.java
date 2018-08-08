package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourCityDTO;
import com.wellbroad.db.beans.tour.TourDAO;

// /adminTourCityInsertPro.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourCityUpdateProAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		//1.AdminTourCityInsertPro.jsp가 처리했던 소스코드를 액션클래스에서 처리 -> 메서드 호출 -> DB -> 결과
		TourCityDTO cityToUpdate = new TourCityDTO();
		cityToUpdate.setCode_city(request.getParameter("code_city"));
		cityToUpdate.setName(request.getParameter("name"));
		cityToUpdate.setDescription(request.getParameter("description"));
		System.out.println("******[AdminTourCityUpdateProAction] ************ "+cityToUpdate.getCode_city());
		

		//2-1.수정
		TourDAO tourManager = new TourDAO();
		int updateCheck= tourManager.updateTourCity(cityToUpdate);
		
		//3.정해진 view로 이동
		request.setAttribute("check", updateCheck);	
		request.setAttribute("movepage", "adminTourCity.do");
		request.setAttribute("errorpage", "adminTourCityUpdate.do?code_city="+cityToUpdate.getCode_city());
		
		return "/admin/adminTourUpdatePro.jsp";
	}
}
