package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.BoardDAO;
import com.wellbroad.db.beans.board.NoticeBoardDTO;
import com.wellbroad.db.beans.board.ReviewBoardDTO;
import com.wellbroad.db.beans.board.QnaBoardDTO;
//  /writeForm.do
public class QnaUpdateFormAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

	    //updateForm.jsp?num=3&pageNum=1
		//System.out.println("QnaUpdateFormAction의 num=>"+request.getParameter("num"));
		//System.out.println("QnaUpdateFormAction의 pagenum=>"+request.getParameter("pageNum"));
	    int num=Integer.parseInt(request.getParameter("num"));
	    System.out.println("QnaUpdateFormAction의 num=>"+request.getParameter("num"));
	    String pageNum=request.getParameter("pageNum");
	    BoardDAO dbPro=new BoardDAO();
	    QnaBoardDTO article=dbPro.updateGetArticleQna(num);
	    System.out.println("QnaUpdateFormAction의 article=>"+article);
	    /*article.setRef(Integer.parseInt(request.getParameter("ref")));
	   article.setRe_step(Integer.parseInt(request.getParameter("content")));
	     article.setRe_level(Integer.parseInt(request.getParameter("re_level")));*/
	    //서버의 메모리에 저장
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("article", article);
	    
		return "../board/qna/qnaUpdateForm.jsp";
	}

}
