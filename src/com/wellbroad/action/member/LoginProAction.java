package com.wellbroad.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wellbroad.action.controller.BaseAction;
import com.wellbroad.db.beans.login.LoginDAO;

public class LoginProAction implements BaseAction {

    @Override
    public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        LoginDAO login = new LoginDAO();
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        
        System.out.println("id=>" + id + ",password=>" + password);
        
        int authentication = login.loginWhoever(id, password);
        
        request.setAttribute("authentication", authentication);
        
        /*if(id.equals("admin")) {
            boolean check = login.loginAdmin(id, password);
            request.setAttribute("check", check);
            request.setAttribute("id", id);
        }else {
            boolean check = login.loginWhoever(id, password);
            request.setAttribute("check", check);
            request.setAttribute("id", id);
        }*/
        return "LoginPro.jsp";
    }
}