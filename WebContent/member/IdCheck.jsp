<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Id중복 체크</title>
<link href="style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="../Euro-Travels-master/assets/css/bootstrap.min.css">
<SCRIPT LANGUAGE="JavaScript" src="script.js">
	
</SCRIPT>
<style>
	.logo{
		margin-bottom:15px;
	}
</style>
</head>
<body>
	<br>
	<center>
		<div>	    
     		<i><img src="../custom/image/logo2.png" width="200" class="logo"></i>
     	</div>
		<b>${id}</b>
		<c:if test="${check}">
			<%
			    out.println(" 는 이미 존재하는 아이디 입니다.<p>");
			%>
		</c:if>
		<c:if test="${!check}">
			<%
			    out.println("는 사용 가능한 아이디 입니다.<p>");
			%>
		</c:if>
		<a href="#" onclick="self.close()">닫기</a>
	</center>
</body>
</html>






