package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;

public class DelCheckFormAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
        // TODO Auto-generated method stub
        String id=request.getParameter("id");
        System.out.println("DelCheckForm.jsp의 id=>"+id);
        request.setAttribute("id",id);
        return "DelCheckForm.jsp";
    }

}
