<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="com.wellbroad.db.beans.board.BoardDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${check==1}">
	<meta http-equiv="Refresh" content="0;url=reviewList.do?pageNum=${pageNum}">
		<%
	System.out.println("ReviewUpdatePro.jsp 실행~~~~~!!");
%>
</c:if>
<c:if test="${check!=1}">
<script>
	alert("오류가 발생하였습니다. \n다시 확인요망!!")
	history.back();
</script>
</c:if>
