<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.wellbroad.db.beans.member.MemberDAO"
	import="com.wellbroad.db.beans.login.LoginDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${authentication==1}">
<!-- 일반 회원 -->
	<%
	   		String id = request.getParameter("id");
	 		session.setAttribute("userId", id);
	        System.out.println(session.getAttribute("userId"));
	        response.sendRedirect("../main/mainpage.do");
	%>
</c:if>
<c:if test="${authentication==2}">
<!-- 관리자 -->
	<%
	   		String id = request.getParameter("id");
	 		session.setAttribute("adminId", id);
	        System.out.println(session.getAttribute("adminId"));
	        response.sendRedirect("../main/mainpage.do");
	%>
</c:if>
<c:if test="${authentication==0}">
<!-- 로그인 실패 -->
	<%
	    response.sendRedirect("LogError.do");
	%>
</c:if>

