package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.BoardDAO;
import com.wellbroad.db.beans.board.NoticeBoardDTO;
import com.wellbroad.db.beans.board.ReviewBoardDTO;
//  /writeForm.do
public class ReviewUpdateFormAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

	    //updateForm.jsp?num=3&pageNum=1
		//System.out.println("ReviewUpdateFormAction의 num=>"+request.getParameter("num"));
		//System.out.println("ReviewUpdateFormAction의 pagenum=>"+request.getParameter("pageNum"));
	    int num=Integer.parseInt(request.getParameter("num"));
	    System.out.println("ReviewUpdateFormAction의 num=>"+request.getParameter("num"));
	    String pageNum=request.getParameter("pageNum");
	    BoardDAO dbPro=new BoardDAO();
	    ReviewBoardDTO article=dbPro.updateGetArticleReview(num);
	    System.out.println("ReviewUpdateFormAction의 article=>"+article);
	    /*article.setRef(Integer.parseInt(request.getParameter("ref")));
	   article.setRe_step(Integer.parseInt(request.getParameter("content")));
	     article.setRe_level(Integer.parseInt(request.getParameter("re_level")));*/
	    //서버의 메모리에 저장
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("article", article);
	    
		return "/board/review/reviewUpdateForm.jsp";
	}

}
