package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.login.LoginDAO;

public class FindIdProAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        request.setCharacterEncoding("utf-8");
        LoginDAO loginDAO = new LoginDAO();
        String name = request.getParameter("name");
        String birth = request.getParameter("birth");
        String userId="";
        userId = loginDAO.findMyId(name, birth);
        System.out.println(userId); 
        request.setAttribute("userId", userId);
        return "FindIdPro.jsp";
    }

}
