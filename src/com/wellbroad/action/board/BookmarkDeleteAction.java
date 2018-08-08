package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberBookmarkDAO;

//  /detelForm.do?num=?&pageNum
public class BookmarkDeleteAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 //deleteForm.do?num=3&pageNum=3
		System.out.println("ASASDASDASDASD__________  ____________");
		System.out.println("BookmarkDeleteAcion의 num->>>>"+request.getParameter("num"));
		System.out.println("BookmarkDeleteAcion의 delete->>>>"+request.getParameter("delete"));
		System.out.println("BookmarkDeleteAcion의 id->>>>"+request.getParameter("id"));
		System.out.println("BookmarkDeleteAcion의 pageNum->>>>"+request.getParameter("pageNum"));
		
	    int num=Integer.parseInt(request.getParameter("num"));//삭제할 게시물번호
	    int delete=Integer.parseInt(request.getParameter("delete"));
	    String id = request.getParameter("id");
	    String pageNum=request.getParameter("pageNum");
	    System.out.println("deleteForm.do의 num=>"+num+",pageNum="+pageNum);
	    MemberBookmarkDAO dbPro=new MemberBookmarkDAO();
	    int check=dbPro.deleteArticleBookmark(num, id);
	     System.out.println("BookmarkDeleteAction의 check=>"+check);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("check", check);
		   request.setAttribute("num", num);
	    if(pageNum!=null) {
	    	return "reviewContent.do?num="+num+"&pageNum="+pageNum+"&id="+id;
	    }
		System.out.println("ASASDASDASDASD__________  ____________");
		return "bookmark.do";
	}
}
