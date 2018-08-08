package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberDAO;
import com.wellbroad.db.beans.member.MemberDTO;

public class MemberUpdateAction implements BaseAction{

    MemberDAO memberDao = new MemberDAO();
    
    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
       
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        String id = (String) session.getAttribute("userId");
        MemberDTO member = memberDao.getMember(id);
        request.setAttribute("member", member);
        return "MemberUpdate.jsp";
    }
}