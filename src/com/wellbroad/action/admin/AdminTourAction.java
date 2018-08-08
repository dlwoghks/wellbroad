package com.wellbroad.action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourCityDTO;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourDTO;
import com.wellbroad.db.beans.tour.TourNationDTO;
import com.wellbroad.util.PageUtil;

// /adminTour.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String tour_type=request.getParameter("tour_type");
		String code_nation=request.getParameter("code_nation");
		String code_city=request.getParameter("code_city");
		String currentPage=request.getParameter("currentPage");
		System.out.println("******[AdminTourAction] ************ "+tour_type);
		if(code_nation==null) code_nation="choose";
		if(code_city==null) code_city="choose";
		if(currentPage==null) currentPage ="1";
		
		//1.
		TourDAO tourManager = new TourDAO();
		List<TourNationDTO> nationList=null;
		List<TourCityDTO> cityList=null;
		List<TourDTO> tourList=null;
		int count=0;
		PageUtil pageUtil = null;
		
		
		//2-1.국가리스트
		nationList = tourManager.getTourNationList(); 	
		
		
		//2-2.국가를 선택했다면 선택한 국가의 도시리스트 가져오기 + 선택한 국가코드 넘겨서 select박스에서 option selected설정
		if(!code_nation.equals("choose")) {
			cityList = tourManager.getTourCityList(code_nation);
			//2-3.도시를 선택했다면 선택한 도시의 숙박리스트 가져오기 + 선택한 도시코드 넘겨서 select박스에서 option selected설정
			if(!code_city.equals("choose")) {
				tourList = tourManager.getTourList(tour_type,code_city);
			}
			//2-3.도시를 선택하지 않았다면 해당 국가의 전체 숙박리스트 가져오기
			else {
				tourList = tourManager.getTourList(tour_type,"",code_nation);
			}
		}
		//2-2.국가를 선택하지 않았다면 전체 숙박리스트 가져오기
		else {
			tourList = tourManager.getTourList(tour_type);
		}
		count = tourList.size(); 

		
		//2-3.페이징처리 (데이터가 없으면 페이징처리안함)
		if(count!=0) {
			pageUtil = new PageUtil(count,Integer.parseInt(currentPage));
			request.setAttribute("tourList", tourList.subList(pageUtil.getStartNum(), pageUtil.getEndNum()+1));
			request.setAttribute("pageUtil", pageUtil);
		}else {
			request.setAttribute("tourList", tourList);
		}
		
		request.setAttribute("tour_type", tour_type);		
		request.setAttribute("nationList", nationList);
		request.setAttribute("cityList", cityList);
		request.setAttribute("code_nation", code_nation);
		request.setAttribute("code_city", code_city);
		request.setAttribute("count", count);
		
		//3.정해진 view로 이동
		return "/admin/adminTour.jsp";
	}
}
