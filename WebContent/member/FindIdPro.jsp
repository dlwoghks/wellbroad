<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<style>
#content{
	padding-top:100px;
	width:100%;
	text-align:center;
}
.logo{
	margin-bottom:30px;
	width:250px;
}
#loginInput{ 
	margin-bottom:10px;
	width:300px;
}

.inputStyle{
	height:60px;
}
</style>
<body>
<center>
	<c:if test="${userId != ''}">
		<div id="content">
			<div>
				회원님의 아이디는 <span> "${userId}" 입니다. </span>
			</div>
			<div>
				<a href="../main/mainpage.do" title="HOME">메인페이지로</a>
				<a href="Login.do" title="Login">로그인으로</a>
				<a href="FindPassword.do">비밀번호 찾기</a>
			</div>
		</div>
	</c:if>
	<c:if test="${userId == ''}">
		<div id="content">
			<h2>찾는 ID가 없습니다.</h2>
		</div>
		<div>
				<a href="../main/mainpage.do" title="HOME">메인페이지로</a>
				<a href="Login.do" title="Login">로그인으로</a>
		</div>
	</c:if>
</center>
</body>
</html>