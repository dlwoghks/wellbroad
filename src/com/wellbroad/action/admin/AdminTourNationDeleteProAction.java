package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;

// /adminTourNationDeletetPro.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourNationDeleteProAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		//1.삭제할 국가코드 배열
		String[] code_nationArr = request.getParameter("code_nationArr").toString().split(",");
		System.out.println("******[AdminTourNationDeleteProAction] ************ "+code_nationArr.length);
		request.setAttribute("movepage", "adminTourNation.do");
		
		//2.
		TourDAO tourManager = new TourDAO();
		for (int i=0; i<code_nationArr.length; i++) {
			if(!tourManager.deleteTourNation(code_nationArr[i])) {
				request.setAttribute("check", false);
				request.setAttribute("code", code_nationArr[i]);
				return "/admin/adminTourDeletePro.jsp";
			}			
		}
		request.setAttribute("check", true);
		
		
		//3.정해진 view로 이동
		return "/admin/adminTourDeletePro.jsp";
	}
}
