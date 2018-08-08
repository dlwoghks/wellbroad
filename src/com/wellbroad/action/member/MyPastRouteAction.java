package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;

public class MyPastRouteAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
        // TODO Auto-generated method stub
        return "MyPastRoute.jsp";
    }

}
