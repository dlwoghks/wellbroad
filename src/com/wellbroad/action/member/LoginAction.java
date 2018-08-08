package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberDTO;

public class LoginAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
	   /* String id = request.getParameter("id");
	    String password = request.getParameter("password");
	    
	    MemberDTO member = new MemberDTO();
	    
	    if(member.getId().equals(id)) {
	        session.setAttribute();
	    }*/
	    
	    
	    return "Login.jsp";
	}

}
