package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourNationDTO;

// /adminTourNationUpdatePro.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourNationUpdateProAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		//1.
		TourNationDTO nationToUpdate = new TourNationDTO();
		nationToUpdate.setCode_nation(request.getParameter("code_nation").toUpperCase());
		nationToUpdate.setName(request.getParameter("name"));
		System.out.println("******[AdminTourNationUpdateProAction] ************ "+nationToUpdate.getCode_nation());
		
		//2.수정
		TourDAO tourManager = new TourDAO();
		int updateCheck= tourManager.updateTourNation(nationToUpdate);
		
		
		//3.정해진 view로 이동
		request.setAttribute("check", updateCheck);	
		request.setAttribute("movepage", "adminTourNation.do");
		request.setAttribute("errorpage", "adminTourNationUpdate.do?code_nation="+nationToUpdate.getCode_nation());
		
		return "/admin/adminTourUpdatePro.jsp";
	}
}
