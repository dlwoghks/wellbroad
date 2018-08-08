package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;

//  /detelForm.do?num=?&pageNum
public class QnaDeleteFormAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		 //deleteForm.do?num=3&pageNum=3
		request.setCharacterEncoding("utf-8");
		int num=Integer.parseInt(request.getParameter("num"));//삭제할 게시물번호
	    String pageNum=request.getParameter("pageNum");
	    System.out.println("deleteForm.do의 num=>"+num
	    		                                               +",pageNum="+pageNum);
	    request.setAttribute("num", num);
	    request.setAttribute("pageNum", pageNum);
	    
		return "../board/qna/qnaDeleteForm.jsp" ;
	}
}
