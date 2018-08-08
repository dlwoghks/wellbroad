package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourDTO;

// /adminTourDetail.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourDetailAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String code=request.getParameter("code");
		String tour_type=request.getParameter("tour_type");
		System.out.println("******[AdminTourDetailAction] ************ "+code);
		
		//1.
		TourDAO tourManager = new TourDAO();		

		//2.도시 정보 가져오기
		TourDTO tour = tourManager.getTour(tour_type, code);
		request.setAttribute("tour", tour);
		request.setAttribute("tour_type", tour_type);
		
		//3.정해진 view로 이동
		return "/admin/adminTourDetail.jsp";
	}
}
