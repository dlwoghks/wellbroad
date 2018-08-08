<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet" href="${pageContext.request.contextPath}/adminpage_template/css/bootstrap.css">
<style>
button {
    background-color: white;
    color: black;
    border: 2px solid lightblue;
}
font{
	color:hotpink;
	font-weight: bold;
}
img{
	height:130px;
	width:300px;	
}
</style>
<center>
	<img src="../custom/image/DuplicatedWarning.png">
	<div style="margin-top:-80px">
		<b>&nbsp;&nbsp;&nbsp;사용 <font>
		<c:if test="${check }">
			가능
		</c:if>
		<c:if test="${!check }">
			불가
		</c:if>
		</font>
		합니다</b>
	</div>
<BR>
<button type="button" class="btn-sm" onclick="self.close()">닫기</button>
</center>