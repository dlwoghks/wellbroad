package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;

// /adminTourDeletePro.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourDeleteProAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String tour_type=request.getParameter("tour_type");
		
		//1.삭제할 도시코드 배열
		String[] codeArr = request.getParameter("codeArr").toString().split(",");
		System.out.println("******[AdminTourDeleteProAction] ************ "+codeArr.length);
		request.setAttribute("movepage", "adminTour.do?tour_type="+tour_type);
		
		//2.
		TourDAO tourManager = new TourDAO();
		for (int i=0; i<codeArr.length; i++) {
			if(!tourManager.deleteTour(tour_type,codeArr[i])) {
				request.setAttribute("check", false);
				request.setAttribute("code", codeArr[i]);
				System.out.println("check");
				System.out.println("code:"+ codeArr[i]);
				return "/admin/adminTourDeletePro.jsp";
			};
			
		}
		request.setAttribute("check", true);
		
		
		//3.정해진 view로 이동
		return "/admin/adminTourDeletePro.jsp";
	}
}
