<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.wellbroad.db.beans.member.MemberDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${check}">
	<script>
		alert("성공적으로 수정되었습니다.!")
		location.href = "Mypage.do" 
	</script>

</c:if>
<c:if test="${!check}">
	<script>
		alert("수정 도중에 에러가 발생되었습니다.")
		history.back();
	</script>
</c:if>





