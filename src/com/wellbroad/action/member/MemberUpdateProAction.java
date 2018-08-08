package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberDAO;
import com.wellbroad.db.beans.member.MemberDTO;

public class MemberUpdateProAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse resonpse) throws Throwable {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
      //추가 hidden객체 확인
        
        MemberDTO member=new MemberDTO();
        MemberDAO memMgr=new MemberDAO();//수정 메서드를 호출하기위해서
        
        member.setId(request.getParameter("id"));
        member.setPassword(request.getParameter("password"));
        member.setName(request.getParameter("name"));
        member.setBirth(request.getParameter("birth"));
        member.setGender(request.getParameter("gender"));
        member.setMail(request.getParameter("mail"));
        member.setPhone(request.getParameter("phone"));
        if(request.getParameter("mailagree")!=null) {
            member.setMailagree("O");
        }else {
            member.setMailagree("X");
        }
        
        boolean check=memMgr.updateMember(member);
        System.out.println("memberUpdate.jsp의 회원수정유무 check=>"+check);

        request.setAttribute("member", member);
        request.setAttribute("check", check);
        return "MemberUpdatePro.jsp";    
    }
}
