package com.wellbroad.action.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberDAO;
import com.wellbroad.db.beans.member.MemberDTO;

public class MemberListAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
     // 회원정보를 가져온다.
        request.setCharacterEncoding("utf-8");
        MemberDAO member = new MemberDAO();
        ArrayList<MemberDTO> memberList = member.getMemberList();
        int count=0;
        count=memberList.size();
        // MemberListForm.jsp에 회원정보를 전달하기 위해 request에 MemberDTO을 세팅한다.
        request.setAttribute("count", count);
        request.setAttribute("memberList", memberList);

        return "../admin/adminMemberList.jsp";
    }

}
