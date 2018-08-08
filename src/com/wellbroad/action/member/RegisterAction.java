package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberDAO;
import com.wellbroad.db.beans.member.MemberDTO;

public class RegisterAction implements BaseAction {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        return "Register.jsp";
    }
}
