package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.*;
import java.sql.Timestamp;//시간(추가)

//   /writePro.do?num=?&,,,,,
public class ReviewWriteProAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
	     request.setCharacterEncoding("utf-8");//한글
	     //BoardDTO->Setter Method호출(5개)+hidden(4개)->9개
	     //BoardDTO article=new BoardDTO();
	     //property="*" =>매개변수로 전달받은 경우에만 적용
	     ReviewBoardDTO article=new ReviewBoardDTO();
	     article.setNum(Integer.parseInt(request.getParameter("num")));
	     article.setId(request.getParameter("id"));
	     article.setIp(request.getRemoteAddr());
	     article.setTitle(request.getParameter("title"));
	     article.setContent(request.getParameter("content"));
	     article.setDay(new Timestamp(System.currentTimeMillis()));
	     article.setRef(Integer.parseInt(request.getParameter("ref")));
	     article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
	     article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
	     System.out.println(Integer.parseInt(request.getParameter("num")));
	     System.out.println("writerproaction num값==>>"+request.getParameter("num"));
	     System.out.println("writerproaction member ID값==>>"+request.getParameter("id"));
	     System.out.println("writerproaction ip값==>>"+request.getRemoteAddr());
	     System.out.println("writerproaction title값==>>"+request.getParameter("title"));
	     System.out.println("writerproaction content값==>>"+request.getParameter("content"));
	     System.out.println(Integer.parseInt(request.getParameter("ref")));
	    
	   BoardDAO dbPro=new BoardDAO();
	   dbPro.insertArticleReview(article);//num=0
	   //String pageNum=request.setAttribute(article);
		//request.setAttribut1e()왜 사용->페이지 이동=>전달할 매개변수가 필요한 경우
	    //에만 코딩
		return "/board/review/reviewWritePro.jsp";
	}
}
