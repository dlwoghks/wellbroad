 package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.BoardDAO;

public class ReviewDeleteProAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//pageNum이 넘어오는지 체크
		 	System.out.println("~~~~~~~~~~~~~~~~~```~~~~~~~");
		   int num=Integer.parseInt(request.getParameter("num")); //삭제할 게시물번호
		   System.out.println(request.getParameter("num"));
		   String pageNum=request.getParameter("pageNum");//필드X
		   System.out.println(request.getParameter("pageNum"));
		   String id=request.getParameter("id");//필드X
		   System.out.println(request.getParameter("id"));
		   System.out.println("ReviewDeleteProAction.do의 num=>"+num+",pageNum=>"+pageNum+"id=>"+id);
		   BoardDAO dbPro=new BoardDAO();
		   int check=dbPro.deleteArticleReview(num, id);//(passwd,num)
		   System.out.println("ReviewDeleteProAction의 check=>"+check);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("check", check);
		return "/board/review/reviewDeletePro.jsp";
	}
}

