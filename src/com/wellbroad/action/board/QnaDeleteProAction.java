 package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.BoardDAO;

public class QnaDeleteProAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//pageNum이 넘어오는지 체크

		   request.setCharacterEncoding("utf-8");
		
		   int num=Integer.parseInt(request.getParameter("num")); //삭제할 게시물번호
		   String pageNum=request.getParameter("pageNum");//필드X
		   String id=request.getParameter("id");//필드X
		   String mail=request.getParameter("mail");//필드X
		   System.out.println("QnaDeleteProAction.do의 num=>"+num+",pageNum=>"+pageNum+"id=>"+id);
		   //System.out.println("QnaDeleteProAction.do의 num=>"+num+",pageNum=>"+pageNum+"mail=>"+mail);
		   BoardDAO dbPro=new BoardDAO();
		   int check=dbPro.deleteArticleQna(num, id,mail);//(passwd,num)
		   System.out.println("QnaDeleteProAction의 check=>"+check);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("check", check);
		return "../board/qna/qnaDeletePro.jsp";
	}
}
