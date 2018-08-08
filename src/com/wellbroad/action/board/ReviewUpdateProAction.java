package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.*;

//   /updatePro.do?num=?&,,,,,
public class ReviewUpdateProAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 System.out.println("ReviewUpdateProAction실행!!!");
	     request.setCharacterEncoding("utf-8");//한글
	     String pageNum=request.getParameter("pageNum");
	     System.out.println("ReviewUpdateProAction의 pageNum=>"+request.getParameter("pageNum"));

	     ReviewBoardDTO article=new ReviewBoardDTO();
	     System.out.println("ReviewUpdateProAcion!!!!!!!!");
	     article.setNum(Integer.parseInt(request.getParameter("num")));//hidden
	     System.out.println(article.getId());
	     article.setId(request.getParameter("id"));
	     article.setTitle(request.getParameter("title"));
	     article.setContent(request.getParameter("content"));
	   
	     System.out.println("ReviewUpdateProAcion222222222222!!!!!!!");
	     BoardDAO dbPro=new BoardDAO();
		 int check=dbPro.updateArticleReview(article);//num=0
	
	   //updatePro.jsp에서 어느 페이지에서 게시물이 수정됐는지를 보여주기위해서
	   System.out.println("ReviewUpdateProAction의 pageNum=>"+pageNum);
	  System.out.println("ReviewUpdateProAction의 check=>"+check);
	   request.setAttribute("pageNum", pageNum);
	   request.setAttribute("check", check);
	   
		return "/board/review/reviewUpdatePro.jsp";
	}
}
