package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.BoardDAO;
import com.wellbroad.db.beans.board.NoticeBoardDTO;
//  /writeForm.do
public class NoticeUpdateFormAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//System.out.println("NoticeUpdateFormAction=>"+request.getParameter("num"));
	    //updateForm.jsp?num=3&pageNum=1
		System.out.println("NoticeUpdateFormAction의 num=>"+request.getParameter("num"));
		System.out.println("NoticeUpdateFormAction의 pagenum=>"+request.getParameter("pageNum"));
	    int num=Integer.parseInt(request.getParameter("num"));
	    String pageNum=request.getParameter("pageNum");
	    BoardDAO dbPro=new BoardDAO();
	    NoticeBoardDTO article=dbPro.updateGetArticleNotice(num);
	    System.out.println("NoticeUpdateFormAction의 article=>"+article);
	    //서버의 메모리에 저장
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("article", article);
	    
		return "/board/notice/noticeUpdateForm.jsp"; 
	}

}
