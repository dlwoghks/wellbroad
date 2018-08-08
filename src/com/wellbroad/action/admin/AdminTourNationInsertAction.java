package com.wellbroad.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;

// /adminTourNationInsert.do 요청이 들어왔을 때 처리해주는 액션클래스
public class AdminTourNationInsertAction implements BaseAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		System.out.println("******[AdminTourNationInsertAction] ************");
		
		
		//3.정해진 view로 이동
		return "/admin/adminTourNationInsert.jsp";
	}
}
