package com.wellbroad.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourCityDTO;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourNationDTO;
import com.wellbroad.util.PageUtil;

// /adminTourCity.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourCityAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String code_nation=request.getParameter("code_nation");
		String currentPage=request.getParameter("currentPage");
		System.out.println("******[AdminTourCityAction] ************ "+code_nation);
		if(code_nation==null) code_nation="choose";
		if(currentPage==null) currentPage ="1";
		
		//1.AdminTourCity.jsp가 처리했던 소스코드를 액션클래스에서 처리 -> 메서드 호출 -> DB -> 결과
		TourDAO tourManager = new TourDAO();
		List<TourNationDTO> nationList=null;
		List<TourCityDTO> cityList=null;
		int count=0;
		PageUtil pageUtil = null;

		//2-1.국가리스트
		nationList = tourManager.getTourNationList(); 
		request.setAttribute("nationList", nationList);			
		
		//2-2.국가를 선택했다면 선택한 국가의 도시리스트 가져오기 + 선택한 국가코드 넘겨서 select박스에서 option selected설정
		if(!code_nation.equals("choose")) {
			cityList = tourManager.getTourCityList(code_nation);
		}
		//2-2. 국가를 선택하지 않았다면 전체 도시리스트 가져오기
		else {
			cityList = tourManager.getTourCityList("");
		}
		count = cityList.size();
		
		
		//2-3.페이징처리 (데이터가 없으면 페이징처리안함)
		if(count!=0) {
			pageUtil = new PageUtil(count,Integer.parseInt(currentPage));
			request.setAttribute("cityList", cityList.subList(pageUtil.getStartNum(), pageUtil.getEndNum()+1));
			request.setAttribute("pageUtil", pageUtil);
		}else {
			request.setAttribute("cityList", cityList);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("code_nation", code_nation);
		
		//3.정해진 view로 이동
		return "/admin/adminTourCity.jsp";
	}
}
