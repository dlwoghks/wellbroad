package com.wellbroad.action.tour;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourCityDTO;
import com.wellbroad.db.beans.tour.TourDAO;
import com.wellbroad.db.beans.tour.TourDTO;
import com.wellbroad.db.beans.tour.TourNationDTO;

// /tourSpot.do 요청이 들어왔을 때 처리해주는 액션클래스
public class TourAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String tour_type=request.getParameter("tour_type");
		String code_nation=request.getParameter("code_nation");
		String code_city=request.getParameter("code_city");
		String code=request.getParameter("code");
		System.out.println("******[TourAction] ************ "+code_nation+" "+code_city+" "+code);
		
		//1.TourFood.jsp가 처리했던 소스코드를 액션클래스에서 처리 -> 메서드 호출 -> DB -> 결과
		TourDAO tourManager = new TourDAO();//<jsp:useBean id="tourManager" class="com.wellbroad.db.beans.tour.TourDAO"></jsp:useBean>
		List<TourNationDTO> nationList=null;
		List<TourCityDTO> cityList=null;
		List<TourDTO> tourList=null;
		
		//2-1.국가리스트
		nationList = tourManager.getTourNationList();
		request.setAttribute("nationList", nationList);		
		
		
		//2-2.국가를 선택했다면 도시리스트 가져오기 + 선택한 국가코드 넘겨서 select박스에서 option selected설정
		if(code_nation!=null) {
			cityList = tourManager.getTourCityList(code_nation);
			request.setAttribute("cityList", cityList);
			request.setAttribute("code_nation", code_nation);
		}
		
		//2-3.도시를 선택했다면 관광리스트 가져오기 + 선택한 도시코드 넘겨서 select박스에서 option selected설정
		if(code_city!=null) { 
			tourList = tourManager.getTourList(tour_type, code_city);
			request.setAttribute("tourList", tourList);
			request.setAttribute("code_city", code_city);
		}
		
		//2-4.관광을 선택했다면, 해당관광지 상세정보 가져오기+ 선택한 관광코드 넘기겨서 select박스에서 option selected설정
		if(code!=null) {
			TourDTO tour = (TourDTO)tourManager.getTour(tour_type, code);
			request.setAttribute("tour", tour);
			request.setAttribute("code", code);			
		}
		request.setAttribute("tour_type", tour_type);
		
		//3.정해진 view로 이동
		return "/tour/tour.jsp";
	}
}
