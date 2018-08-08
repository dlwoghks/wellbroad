package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;

public class MypageAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
        request.setCharacterEncoding("utf-8");
        // TODO Auto-generated method stub
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        return "Mypage.jsp";
    }

}
