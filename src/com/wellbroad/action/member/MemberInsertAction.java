package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.*;

public class MemberInsertAction implements BaseAction {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
        
        request.setCharacterEncoding("UTF-8");
        MemberDTO member = new MemberDTO();
        MemberDAO memberDAO = new MemberDAO();
        boolean flag = false;
        member.setId(request.getParameter("id"));
        member.setPassword(request.getParameter("password"));
        member.setName(request.getParameter("name"));
        member.setBirth(request.getParameter("birth"));
        member.setGender(request.getParameter("gender").toUpperCase());
        member.setMail(request.getParameter("mail"));        
        member.setMailagree(request.getParameter("mailagree"));
        member.setPhone(request.getParameter("phone"));
        
        System.out.println("MemberInsertAction getId : "+member.getId());
        MemberDAO memberInsert = new MemberDAO();
        boolean insertCheck=memberInsert.registerMember(member);
        System.out.println("insertCheck=>"+insertCheck);
        request.setAttribute("insertCheck",insertCheck);
        
        return "MemberInsert.jsp";
    
    }

}
