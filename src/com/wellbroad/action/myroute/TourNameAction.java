package com.wellbroad.action.myroute;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.myroute.MyRouteDAO;
import com.wellbroad.db.beans.tour.TourListDTO;

public class TourNameAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, 
							HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		
		String cityname = request.getParameter("listtour");
		
		MyRouteDAO myroute= new MyRouteDAO();
		
		List<TourListDTO>listtour = myroute.getTourName(cityname);
											//관광지데이터 		//푸켓 
		//request.setAttribute("modal_imgs",listcity);
		System.out.println("---성공---"+ cityname);
	
		
		response.setContentType("application/x-json charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		JSONArray jsonArray = new JSONArray();
		//{[ ~ ]}
		
		for(TourListDTO dto : listtour) {
			System.out.println(dto.getName());
			
			jsonArray.add(dto.toString()); 
//			public String toString() { 
//			return "{\"icon\":\""+icon+"\",\"code\":\"" + code + "\", \"name\":\"" + name + "\", \"description\":\"" + description + "\"}";
//			
		} 
		
		
		JSONObject obj = new JSONObject(); // {}    "{listtour : [a,b,c,d]}"
		obj.put("listtour",jsonArray);
		// "{ listtour : {[{icon:aaa, code:bbb, name:ccc}, {icon:ddd, code:eee, name:fff}, ...{..}]}}"
		
		System.out.println(obj.toString());
		response.getWriter().print(obj);
		

		return "/tour/myrouteForm.jsp";
	}

}
