package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;

// /adminTourNationCheck.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourNationCheckAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String code_nation=request.getParameter("code_nation").toUpperCase();
		System.out.println("******[AdminTourNationCheckAction] ************ " +code_nation);
		
		//1.AdminTourNation.jsp가 처리했던 소스코드를 액션클래스에서 처리 -> 메서드 호출 -> DB -> 결과
		TourDAO tourManager = new TourDAO();
		boolean check = tourManager.checkCode_nation(code_nation); 
		
		//2.결과 넘기기
		request.setAttribute("check", check);
		
		//3.정해진 view로 이동
		return "/admin/adminTourCodeCheck.jsp";
	}
}
