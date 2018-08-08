package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberBookmarkDAO;
import com.wellbroad.db.beans.member.MemberBookmarkDTO;


//   /writePro.do?num=?&,,,,,
public class BookmarkInsertAction implements BaseAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("BookmarkInsertAction!!!!!!!!");
	     request.setCharacterEncoding("utf-8");//한글
	     int review_num=0;
		String id="";
		String title="";
		String pageNum="";
		     //content.do에서 num이하가 넘어오는 경우라면
		    if(request.getParameter("num")!=null){ //1이상
		    	review_num=Integer.parseInt(request.getParameter("num"));
		    	id=request.getParameter("id");
		    	title=request.getParameter("title");
		    	pageNum=request.getParameter("pageNum");
		    	System.out.println("content.jsp에서 넘어온 매개변수값을 확인");
		    	System.out.println("review_num="+review_num+", id=>"+id+", title=>"+title);
		    }
		    MemberBookmarkDTO article=new MemberBookmarkDTO();
	     article.setReview_num(review_num);
	     article.setId(id); 
	     article.setTitle(title);
	     System.out.println("BookmarkInsertAction num값==>>"+review_num);
	     System.out.println("BookmarkInsertAction member ID값==>>"+id);
	     System.out.println("BookmarkInsertAction member title값==>>"+title);
	     MemberBookmarkDAO dbPro=new MemberBookmarkDAO();
	   dbPro.insertArticleBookmark(article);//num=0
		return "reviewContent.do?num="+review_num+"&pageNum="+pageNum+"&select="+0+"&id="+id;
	}
}
