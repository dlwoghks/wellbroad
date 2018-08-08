package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourDTO;

// /adminTourInsertPro.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourInsertProAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String tour_type=request.getParameter("tour_type");
		String icon=request.getParameter("icon");
		if(icon.equals("choose")) icon=null;
		
		//1.
		TourDTO tourToInsert = new TourDTO();
		tourToInsert.setName(request.getParameter("name"));
		tourToInsert.setAddress(request.getParameter("address"));
		tourToInsert.setCode_city(request.getParameter("code_city").toLowerCase());
		tourToInsert.setIcon(icon);
		tourToInsert.setDescription(request.getParameter("description"));
		System.out.println("******[AdminTourInsertProAction] ************ "+tourToInsert.getCode());
		

		//2-1.삽입
		TourDAO tourManager = new TourDAO();
		int insertTourCheck= tourManager.insertTour(tour_type,tourToInsert);
		
		//3.정해진 view로 이동
		request.setAttribute("check", insertTourCheck);
		request.setAttribute("movepage", "adminTour.do?tour_type="+tour_type);
		request.setAttribute("errorpage", "adminTourInsert.do?tour_type="+tour_type);
		return "/admin/adminTourInsertPro.jsp";
	}
}
