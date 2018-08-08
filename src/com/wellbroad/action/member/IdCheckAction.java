package com.wellbroad.action.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberDAO;

public class IdCheckAction implements BaseAction {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        String id = request.getParameter("id");
        response.setCharacterEncoding("utf-8");
        
        System.out.println("IdCheckAction 에 전달된 id=>" + id);// null
        // DB접속
        MemberDAO memMgr = new MemberDAO();
        boolean check = memMgr.checkDuplicationId(id);
        System.out.println("IdCheckAction의 check=>" + check);
       
    request.setAttribute("check", check);//${check}

    return "/member/test.jsp";
        
    }
}
