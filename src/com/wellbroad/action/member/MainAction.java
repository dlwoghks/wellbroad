package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellbroad.action.controller.BaseAction;

public class MainAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("sUserId");
//        String id = request.getParameter("id");
        System.out.println("MainAction ID => " + id);
//        request.setAttribute("id", id);
        return "/main/Mainpage.jsp";
    }

}
