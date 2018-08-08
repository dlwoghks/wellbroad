package com.wellbroad.action.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.BoardDAO;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;


public class CrawlingAction implements BaseAction{

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
        request.setCharacterEncoding("utf-8");
        BoardDAO crawl = new BoardDAO();
        //List<String> title = new ArrayList();
        String search = request.getParameter("search");
        System.out.println("search" + search);
        String text = crawl.crawlingBoard(search);
        request.setAttribute("text", text);
        return "/board/crawl/crawling.jsp";
    }    
}