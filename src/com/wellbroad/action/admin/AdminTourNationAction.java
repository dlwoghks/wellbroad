package com.wellbroad.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourNationDTO;
import com.wellbroad.util.PageUtil;

// /adminTourNation.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourNationAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String currentPage=request.getParameter("currentPage");
		System.out.println("******[AdminTourNationAction] ************ ");
		if(currentPage==null) currentPage ="1";
		
		//1.
		TourDAO tourManager = new TourDAO();
		List<TourNationDTO> nationList=null;
		int count=0;
		PageUtil pageUtil = null;

		//2-1.국가리스트
		nationList = tourManager.getTourNationList(); 
		request.setAttribute("nationList", nationList);			
		count = nationList.size();
		
		//2-2.페이징처리 (데이터가 없으면 페이징처리안함)
		if(count!=0) {
			pageUtil = new PageUtil(count,Integer.parseInt(currentPage));
			request.setAttribute("nationList", nationList.subList(pageUtil.getStartNum(), pageUtil.getEndNum()+1));
			request.setAttribute("pageUtil", pageUtil);
		}else {
			request.setAttribute("cityList", nationList);
		}
		
		request.setAttribute("count", count);
		
		//3.정해진 view로 이동
		return "/admin/adminTourNation.jsp";
	}
}
