package com.wellbroad.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.board.BoardDAO;

public class CrawlerAction implements BaseAction {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
       
        return "/board/crawl/crawler.jsp";
    } 
}
 