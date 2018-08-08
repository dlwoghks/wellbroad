package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.admin.adminDAO;

public class AdminMemberDeleteAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    	String[] idArr =request.getParameter("idArr").toString().split(",");
        adminDAO admin = new adminDAO();
        
        for (int i=0; i<idArr.length; i++) {
        	if(1!=admin.deleteMember(idArr[i])) {
        		System.out.println("AdminMemberDeleteAction 삭제 도중 오류 발생");
        	}
        }
        return "adminMemberList.do";
    }
}
