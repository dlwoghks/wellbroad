package com.wellbroad.action.board;

// /list.do요청이 들어왔을때 처리해주는 액션클래스
//List,ArrayList,Vector,,,
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.member.MemberBookmarkDTO;
import com.wellbroad.db.beans.member.MemberBookmarkDAO;

public class BookmarkAction implements BaseAction {
   //view=com.requestPro(request, response);// /list.do->/list.jsp
   //  System.out.println("view->"+view);
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//추가 pageNum,search(검색분야),searchtext(검색어)
		 String pageNum=request.getParameter("pageNum");
		 System.out.println("BookmarkAction의 페이지넘==>>>"+request.getParameter("pageNum"));
		 //추가
		 String search=request.getParameter("search"); 
		 String searchtext=request.getParameter("searchtext"); 
		 String id =request.getParameter("id");
		/* MemberDAO member = new MemberDAO();
		 boolean check = member.checkDuplicationId(id);
		 if(check) {*/
	       int count=0;//총레코드수
		   List<MemberBookmarkDTO> articleList=null;//화면에 실제로 출력할 레코드객체
		   //총레코드수 구하기
		   MemberBookmarkDAO dbPro=new MemberBookmarkDAO();
		   //----------------------------------------------------------
		   count=dbPro.getArticleSearchCountBookmark(search, searchtext, id);
		   //-----------------------------------------------------------
		   System.out.println("현재 검색된수(count=>)"+count);
		   //추가2
		   Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
		   //6.검색된 레코드가 존재->articleList에 담기
		   if(count > 0){ //한개이상이라면    ------>endRow(X)
			   System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			   articleList=dbPro.getBookmarkArticles(pgList.get("startRow"), 
					                                              pgList.get("endRow"), //10
					                                              search,searchtext, id);
		   }else {
			   articleList=Collections.EMPTY_LIST;//아무것도 없다는 표시(정적상수)
		   }
		   
		  //처리결과->request객체에 저장
		   request.setAttribute("search", search);
		   request.setAttribute("searchtext", searchtext);
		   request.setAttribute("pgList", pgList);//페이징 처리 매개변수(10개)
		   request.setAttribute("articleList", articleList);
		   request.setAttribute("pageNum", pageNum);
		   request.setAttribute("id", id);
		 /*}*/
		//3.정해진 view로 이동	
		return "../board/bookmark/bookmark.jsp";  //  /board/list.jsp
	}
}


