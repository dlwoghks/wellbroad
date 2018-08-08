package com.wellbroad.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourNationDTO;

// /adminTourCityInsert.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourCityInsertAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		System.out.println("******[AdminTourCityInsertAction] ************");
		
		//1.AdminTourCity.jsp가 처리했던 소스코드를 액션클래스에서 처리 -> 메서드 호출 -> DB -> 결과
		TourDAO tourManager = new TourDAO();//<jsp:useBean id="tourManager" class="com.wellbroad.db.beans.tour.TourDAO"></jsp:useBean>
		List<TourNationDTO> nationList=null;
		

		//2-1.국가리스트
		nationList = tourManager.getTourNationList(); 
		request.setAttribute("nationList", nationList);		
		request.setAttribute("tour_type",request.getParameter("tour_type"));	

		
		//3.정해진 view로 이동
		return "/admin/adminTourCityInsert.jsp";
	}
}
