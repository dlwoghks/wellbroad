 package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.BoardDAO;

public class NoticeDeleteProAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//pageNum이 넘어오는지 체크
		 	System.out.println("~~~~~~~~~~~~~~~~~```~~~~~~~");
		   int num=Integer.parseInt(request.getParameter("num")); //삭제할 게시물번호
		   String pageNum=request.getParameter("pageNum");//필드X
		   String admin_id=request.getParameter("admin_id");//필드X
		   System.out.println("NoticeDeleteProAction.do의 num=>"+num+",pageNum=>"+pageNum+"admin_id=>"+admin_id);
		   BoardDAO dbPro=new BoardDAO();
		   int check=dbPro.deleteArticleNotice(num, admin_id);//(passwd,num)
		   System.out.println("NoticeDeleteProAction의 check=>"+check);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("check", check);
		return "/board/notice/noticeDeletePro.jsp";
	}
}

