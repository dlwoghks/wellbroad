package com.wellbroad.action.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.tour.TourCityDTO;
import com.wellbroad.db.beans.tour.TourDAO;

// /adminTourInsert.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourInsertAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String tour_type=request.getParameter("tour_type");
		System.out.println("******[AdminTourInsertAction] ************");
		
		//1.
		TourDAO tourManager = new TourDAO();
		List<TourCityDTO> cityList=null;
		

		//2-1.국가리스트
		cityList = tourManager.getTourCityList(""); 
		request.setAttribute("cityList", cityList);			
		request.setAttribute("tour_type", tour_type);
		
		//2-2.icon리스트
		List iconList = new ArrayList();
		String path="C:/WelcomeAbroad/WELLBROAD/WebContent/custom/image/icon";
		File dirFile = new File(path);
		File []iconTemptList = dirFile.listFiles();
		for(File tempFile : iconTemptList) {
			if(tempFile.isFile()) {
				iconList.add(tempFile.getName());
			}
		}
		request.setAttribute("iconList", iconList);
		
		//3.정해진 view로 이동
		return "/admin/adminTourInsert.jsp";
	}
}
