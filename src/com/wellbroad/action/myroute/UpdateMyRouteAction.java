package com.wellbroad.action.myroute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.myroute.MyRouteDAO;

public class UpdateMyRouteAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
		request.setCharacterEncoding("utf-8");
		int num=Integer.parseInt(request.getParameter("num"));
		String userid = request.getParameter("userid");
		String coursename = request.getParameter("coursename");
		String course = request.getParameter("course");
		String startdate = request.getParameter("startdate").replaceAll("-","");
		String enddate = request.getParameter("enddate").replaceAll("-","");;
		String nationname = request.getParameter("nationname");
		String cityname = request.getParameter("cityname");
		String iconname = request.getParameter("iconname");
		String tourname = request.getParameter("tourname");
		String icon = request.getParameter("icon");
//		String iconname[] = request.getParameter("iconname").split("\\|");// "aa|bb|cc".split("|") 
//		                                                              //  array[0] -> "aa"
//																     //  array[1] -> "bb"
//																     //  array[2] -> "cc"
//		String icon[] = request.getParameter("icon").split("\\|");
//		String tourname[] = request.getParameter("tourname").split("\\|");
		int days = request.getParameter("tourname").split("\\:").length;
		
		System.out.println("=====MyRoutForm Data");
		System.out.println(num);
		System.out.println(userid);
		System.out.println(coursename);
		System.out.println(course);
		System.out.println(startdate);
		System.out.println(enddate);
		System.out.println(days);
		System.out.println(iconname);
		System.out.println(icon);
		System.out.println(nationname);
		System.out.println(cityname);
		System.out.println(tourname);

		System.out.println("====================");

		

		MyRouteDAO dao = new MyRouteDAO();
		dao.updateMyRoute(num,userid,course,coursename,startdate,enddate,days,iconname,icon, nationname, cityname, tourname);
		
		
		return "/tour/myrouteForm.do?course="+course;
	}

}
