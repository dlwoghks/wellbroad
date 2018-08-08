package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourDTO;

// /adminTourUpdatePro.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourUpdateProAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String tour_type=request.getParameter("tour_type");
		String icon=request.getParameter("icon");
		if(icon.equals("choose")) icon=null;
		
		//1.
		TourDTO tourToUpdate = new TourDTO();
		tourToUpdate.setCode_city(request.getParameter("code_city"));
		tourToUpdate.setCode(request.getParameter("code"));
		tourToUpdate.setName(request.getParameter("name"));
		tourToUpdate.setAddress(request.getParameter("address"));
		tourToUpdate.setIcon(icon);
		tourToUpdate.setDescription(request.getParameter("description"));
		System.out.println("******[AdminTourUpdateProAction] ************ "+tourToUpdate.getCode());
		

		//2-1.수정
		TourDAO tourManager = new TourDAO();
		int updateCheck= tourManager.updateTour(tour_type,tourToUpdate);
		
		//3.정해진 view로 이동
		request.setAttribute("check", updateCheck);	
		request.setAttribute("movepage", "adminTour.do?tour_type="+tour_type);
		request.setAttribute("errorpage", "adminTourUpdate.do?tour_type="+tour_type+"&code="+tourToUpdate.getCode());
		
		return "/admin/adminTourUpdatePro.jsp";
	}
}
