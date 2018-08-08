package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.login.LoginDAO;

public class FindPasswordProAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        request.setCharacterEncoding("utf-8");
        LoginDAO loginDAO = new LoginDAO();
        String id = request.getParameter("id");
        String birth = request.getParameter("birth");
        String userPassword="";
        userPassword = loginDAO.findMyPassword(id, birth);
        request.setAttribute("userPassword", userPassword);
        request.setAttribute("id", id);
        return "FindPasswordPro.jsp";
    }

}
