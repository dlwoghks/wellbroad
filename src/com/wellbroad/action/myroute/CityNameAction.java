package com.wellbroad.action.myroute;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.myroute.MyRouteDAO;

public class CityNameAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, 
							HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String nationName = request.getParameter("nationname");
		MyRouteDAO myroute= new MyRouteDAO();
		List<String>listcity = myroute.getCityName(nationName);
				
		response.setContentType("application/x-json charset=UTF-8");
								//데이터를 json 형태로 보냄 
		response.setCharacterEncoding("utf-8");
			
		//"{}"
		JSONObject obj = new JSONObject();
		obj.put("listcity",listcity);
		
		for(String name : listcity) {
			System.out.println(name);
		}
		
		System.out.println(obj.toString());
//		obj = URLEncoder.encode(obj, "UTF-8");
		response.getWriter().print(obj);
		


		
		return "/tour/myrouteForm.jsp";
	}

}
