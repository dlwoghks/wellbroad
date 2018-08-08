package com.wellbroad.action.myroute;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.myroute.MyRouteDAO;
import com.wellbroad.db.beans.myroute.MyRouteDTO;

public class MyRouteFormAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		MyRouteDAO routedao = new MyRouteDAO();

		String course = request.getParameter("course");

		int num = 0;
		String userid = "";

		boolean isRead = false;
		int days = 1;
		String[] codelist = null;
		String[] iconlist = null;
		String[] namelist = null;
		MyRouteDTO myroute = null;
		
		if (course == null) {
			course = "1";
		}

		userid = (String) request.getSession().getAttribute("userId");

		if (null != userid) {
			 myroute = routedao.getMyRouteCourse(userid, course);

			if (myroute.getDay() > 0) {
				isRead = true;
				days = myroute.getDay();
				num = myroute.getNum();
				codelist = myroute.getIcon_name().split("\\:");
				iconlist = myroute.getIcon().split("\\:");
				namelist = myroute.getTourname().split("\\:");
			} else {
				isRead = false; // 쓰기 모드로 변경
				/* pageContext.forward */
			}
		}else {
			isRead = false; 
		}

		List<String> list = routedao.getNationName();

		request.setAttribute("isRead", isRead);
		request.setAttribute("myroute", myroute);
		request.setAttribute("codelist", codelist);
		request.setAttribute("iconlist", iconlist);
		request.setAttribute("namelist", namelist);
		request.setAttribute("days", days);
		request.setAttribute("course", course);
		request.setAttribute("num", num);

		return "/tour/myrouteForm.jsp";
	}

}
