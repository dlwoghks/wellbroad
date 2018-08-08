<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 확인</title>
<link href="style.css" rel="stylesheet"  type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script.js?ver=2">
</SCRIPT>
</head>
<body>
<br>
<center>

<c:if test="${insertCheck}">
	<script>
		alert("회원가입을 축하드립니다.")
		location.href="../index.jsp"
	</script>
</c:if>
 <c:if test="$!{insertCheck}">
	<script>
	alert("입력정보가 잘못됐습니다. 다시 확인해주세요.")
	history.back()
	</script>
 </c:if>
</center>
</body>
</html>






