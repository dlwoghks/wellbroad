package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourNationDTO;

// /adminTourNationDetail.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourNationDetailAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String code_nation=request.getParameter("code_nation");
		System.out.println("******[AdminTourNationDetailAction] ************ "+code_nation);
		
		//1.
		TourDAO tourManager = new TourDAO();		

		//2.도시 정보 가져오기
		TourNationDTO nation = tourManager.getTourNation(code_nation);
		request.setAttribute("nation", nation);
		
		//3.정해진 view로 이동
		return "/admin/adminTourNationDetail.jsp";
	}
}
