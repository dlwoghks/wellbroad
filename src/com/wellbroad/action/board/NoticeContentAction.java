package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.*;

public class NoticeContentAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
			System.out.println("NoticeContentAction=>"+request.getParameter("num"));
			System.out.println("NoticeContenAction 실행");
		   //list.jsp에서 링크->content.do?num=3&pageNum=1
		   int num=Integer.parseInt(request.getParameter("num"));//메서드의 매개변수때문에
		   String pageNum=request.getParameter("pageNum");
		   
		   BoardDAO dbPro=new BoardDAO();
		   //조회수 증가,num에 해당되는 레코드한개
		   NoticeBoardDTO article=dbPro.getArticleNotice(num);
		   request.setAttribute("num", num);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("article", article);//ref,re_step,re_level이 들어있기때문에
		   System.out.println("[NoticeContentAction] num"+num+" pageNum"+pageNum);
		    //${article.ref}
		   //request.setAttribute("ref","ref");// ${ref} 
		return "/board/notice/noticeContent.jsp";
	}
}




