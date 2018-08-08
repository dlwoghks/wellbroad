package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.*;
import com.wellbroad.db.beans.member.MemberBookmarkDAO;

public class ReviewContentAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
			System.out.println("ReviewContentAction=>"+request.getParameter("num"));
			System.out.println("ReviewContenAction 실행");
		   //list.jsp에서 링크->content.do?num=3&pageNum=1
		   int num=Integer.parseInt(request.getParameter("num"));//메서드의 매개변수때문에
		   String id = request.getParameter("id");
		   String pageNum=request.getParameter("pageNum");
		   
		   BoardDAO dbPro=new BoardDAO();
		   MemberBookmarkDAO dbPro2 = new MemberBookmarkDAO();
		   //조회수 증가,num에 해당되는 레코드한개
		   ReviewBoardDTO article=dbPro.getArticleReview(num);
		   
		   
		   //링크문자열의 길이를 줄이기위해서(댓글때문에)
		   int ref=article.getRef();
		   int re_step=article.getRe_step();
		   int re_level=article.getRe_level();
		   System.out.println("content.do의 매개변수 값");
		   System.out.println("ref->"+ref+",re_step->"+re_step+",re_level=>"+re_level);
		   
		   String checked=request.getParameter("check");
		   int check =0;
		   if(checked==null) check=dbPro2.checkBookmark(num, id);
		   else check = Integer.parseInt(checked);
		   System.out.println("reviewContentAcion의 check=>>>>"+check);
		   
		   //request에 저장
		   request.setAttribute("num", num);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("article", article);//ref,re_step,re_level이 들어있기때문에
		   request.setAttribute("check", check);
		 
		    //${article.ref}
		   //request.setAttribute("ref","ref");// ${ref} 
		return "/board/review/reviewContent.jsp";
	}
}




