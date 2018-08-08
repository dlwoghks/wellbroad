package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberDAO;

public class DeleteProAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
        String id=request.getParameter("id");
        String password=request.getParameter("password");
        
        System.out.println("deletePro.jsp의 id=>"+id);
        System.out.println("deletePro.jsp의 password=>"+password);
        
        MemberDAO member=new MemberDAO();//탈퇴 메서드를 호출하기위해서
        int check=member.deleteMember(id,password);
        System.out.println("DeletePro.jsp의 회원삭제유무 check=>"+check);
        request.setAttribute("id", id);
        request.setAttribute("password", password);
        request.setAttribute("check", check);
        return "DeletePro.jsp";
    }

    
}
