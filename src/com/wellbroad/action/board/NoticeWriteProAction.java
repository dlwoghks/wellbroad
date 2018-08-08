package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.*;
import java.sql.Timestamp;//시간(추가)

//   /writePro.do?num=?&,,,,,
public class NoticeWriteProAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");// 한글
		NoticeBoardDTO article = new NoticeBoardDTO();
		article.setAdmin_id(request.getParameter("admin_id"));
		article.setIp(request.getRemoteAddr());
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		article.setDay(new Timestamp(System.currentTimeMillis()));
		System.out.println("writerproaction num값==>>" + request.getParameter("num"));
		System.out.println("writerproaction Admin_id값==>>" + request.getParameter("admin_id"));
		System.out.println("writerproaction ip값==>>" + request.getRemoteAddr());
		System.out.println("writerproaction title값==>>" + request.getParameter("title"));
		System.out.println("writerproaction content값==>>" + request.getParameter("content"));

		BoardDAO dbPro = new BoardDAO();
		dbPro.insertArticleNotice(article);// num=0
		// String pageNum=request.setAttribute(article);
		// request.setAttribut1e()왜 사용->페이지 이동=>전달할 매개변수가 필요한 경우
		// 에만 코딩
		return "/board/notice/noticeWritePro.jsp";
	}
}
