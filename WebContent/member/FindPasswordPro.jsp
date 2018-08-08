<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</head>
<body>
<center>
	<c:if test="${userPassword != ''}">
		<div id="content">
			<div>
				<span> ${id}님의 비밀번호는 "${userPassword}" 입니다.</span>
			</div>
			<div>
				<a href="../main/mainpage.do" title="HOME">메인페이지로</a>
				<a href="Login.do" title="Login">로그인으로</a>
			</div>
		</div>
	</c:if>
	<c:if test="${userPassword == ''}">
		<div id="content">
			<div>
				<h2>ID가 없거나 입력이 잘못됐습니다.</h2>
			</div>
			<div>
					<a href="../main/mainpage.do" title="HOME">메인페이지로</a>
					<a href="Login.do" title="Login">로그인으로</a>
			</div>
		</div>
	</c:if>
</center>
</body>
</html>