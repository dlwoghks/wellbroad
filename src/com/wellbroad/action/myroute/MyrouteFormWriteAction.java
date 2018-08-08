package com.wellbroad.action.myroute;

import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.myroute.MyRouteDAO;
import com.wellbroad.db.beans.myroute.MyRouteDTO;
import com.wellbroad.db.beans.tour.TourListDTO;

public class MyrouteFormWriteAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		MyRouteDAO routedao = new MyRouteDAO();

		String course = request.getParameter("course");
		int num = 0;
		String userid = "";

		if (course == null) { //매개변수 메뉴코스에 번호가 없을때 1을 넣어줌 
			course = "1";
		}

	
		String edit = request.getParameter("edit"); //myrouteForm에서 보내오는 매개변수 edit

		List<String> list = null;
		List<String> citylist = null;
		List<TourListDTO> tourlist = null;
		MyRouteDTO myroute = null;
		
		int days = 0;

		String oldcoursename = "";
		String oldstartdate = "";
		String oldenddate = "";
		String oldnationname = "";
		String oldcityname = "";

		boolean isEdit = false; 

		userid = (String) request.getSession().getAttribute("userId"); //세션에 저장된 아이디 가져오기 
		myroute = routedao.getMyRouteCourse(userid, course); //myroute테이블 조회 id,course 조회 
		//DTO
		list = routedao.getNationName(); //selectbox 국가 
		
		if (null != userid) {
			if ((edit != null && edit.equals("edit")) && !userid.isEmpty()) {
				isEdit = true; // 수정 모드로 변경 (쓰기모드)
			}
			
			days = myroute.getDay(); //저장되어 있는 ,,며칠 코스 인지 가져옴 (DTO(getDay()) //1일차 2일차 3일차 
			if (days > 0) {
				citylist = routedao.getCityName(myroute.getNationname()); //selectbox 국가에 해당하는 도시 
				tourlist = routedao.getTourName(myroute.getCityname()); //selectbox 도시에 해당하는 관광지 

				oldcoursename = myroute.getCoursename(); //코스이름 
				oldstartdate = myroute.getStartdate(); 
				oldenddate = myroute.getEnddate(); 
				oldnationname = myroute.getNationname(); //국가이름 
				oldcityname = myroute.getCityname(); //도시이름 

				num = myroute.getNum(); //해당 게시물 번호 

			}else {
				isEdit = false;
			}
		} else {
			isEdit = false;
		}
		

		request.setAttribute("num", num); //게시물번호 
		request.setAttribute("course", course); //코스 
		request.setAttribute("isEdit", isEdit); //수정모드 
		request.setAttribute("myroute", myroute); //myrouteDTO
		request.setAttribute("days", days); //코스 며칠 코스 인지 (마지막 코스로)
		request.setAttribute("list", list); // selectbox 국가 
		request.setAttribute("citylist", citylist);  // selectbox 도시  
		request.setAttribute("tourlist", tourlist); // selectbox 관광지 

		request.setAttribute("oldcoursename", oldcoursename); //저장된 코스이름 
		request.setAttribute("oldstartdate", oldstartdate); //저장된 여행시작날짜  
		request.setAttribute("oldenddate", oldenddate); //저장된 여행마지막날짜
		request.setAttribute("oldnationname", oldnationname); //저장된 국가이름  
		request.setAttribute("oldcityname", oldcityname); //저장된 도시이름 
		
		return "/tour/myrouteFormWrite.jsp";
	}

}
