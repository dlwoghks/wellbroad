package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.*;

//   /updatePro.do?num=?&,,,,,
public class NoticeUpdateProAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 System.out.println("NoticeUpdateProAction실행!!!");
	     request.setCharacterEncoding("utf-8");//한글
	     String pageNum=request.getParameter("pageNum");
	     System.out.println("NoticeUpdateProAction의 pageNum=>"+request.getParameter("pageNum"));

         NoticeBoardDTO article=new NoticeBoardDTO();
	     article.setNum(Integer.parseInt(request.getParameter("num")));//hidden
	     article.setAdmin_id(request.getParameter("admin_id"));
	     //article.setIp(request.getParameter("ip"));
	     article.setTitle(request.getParameter("title"));
	     article.setContent(request.getParameter("content"));
	     //article.setReadcount(Integer.parseInt(request.getParameter("readcount")));
	     
	     BoardDAO dbPro=new BoardDAO();
		 int check=dbPro.updateArticleNotice(article);//num=0
	
	   //updatePro.jsp에서 어느 페이지에서 게시물이 수정됐는지를 보여주기위해서
	   System.out.println("NoticeUpdateProAction의 pageNum=>"+pageNum);
	  System.out.println("NoticeUpdateProAction의 check=>"+check);
	   request.setAttribute("pageNum", pageNum);
	   request.setAttribute("check", check);
	   
		return "/board/notice/noticeUpdatePro.jsp";
	}
}
