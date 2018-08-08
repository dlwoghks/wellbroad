package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourCityDTO;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourNationDTO;

// /adminTourCityDetail.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourCityDetailAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String code_city=request.getParameter("code_city");
		System.out.println("******[AdminTourCityDetailAction] ************ "+code_city);
		
		//1.
		TourDAO tourManager = new TourDAO();		

		//2.도시 정보 가져오기
		TourCityDTO city = tourManager.getTourCity(code_city);
		request.setAttribute("city", city);
		
		//2-2.국가
		TourNationDTO nation = tourManager.getTourNation(city.getCode_nation());
		request.setAttribute("nation", nation);
		
		//3.정해진 view로 이동
		return "/admin/adminTourCityDetail.jsp";
	}
}
