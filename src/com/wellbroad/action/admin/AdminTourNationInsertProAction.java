package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourNationDTO;

// /adminTourCityNationPro.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourNationInsertProAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		//1.
		TourNationDTO nationToInsert = new TourNationDTO();
		nationToInsert.setCode_nation(request.getParameter("code_nation").toUpperCase());
		nationToInsert.setName(request.getParameter("name"));
		System.out.println("******[AdminTourCityNationProAction] ************ "+nationToInsert.getCode_nation());
		

		//2.삽입
		TourDAO tourManager = new TourDAO();
		int insertCheck= tourManager.insertTourNation(nationToInsert);
		
		//3.정해진 view로 이동
		request.setAttribute("check", insertCheck);	
		request.setAttribute("movepage", "adminTourNation.do");
		request.setAttribute("errorpage", "adminTourNationInsert.do");
		return "/admin/adminTourInsertPro.jsp";
	}
}
