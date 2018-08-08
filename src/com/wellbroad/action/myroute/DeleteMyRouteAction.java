package com.wellbroad.action.myroute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.myroute.MyRouteDAO;

public class DeleteMyRouteAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, 
							HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
		int num=Integer.parseInt(request.getParameter("num"));
		int course=Integer.parseInt(request.getParameter("course"));
		
		MyRouteDAO myroutedao=new MyRouteDAO();
		myroutedao.deleteMyroute(num);
		
		return "/tour/myrouteForm.do?course="+course;
	}

}
