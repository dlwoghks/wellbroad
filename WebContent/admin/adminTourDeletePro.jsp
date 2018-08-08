<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${check}">
	<script>
		alert("삭제가 완료되었습니다.");
	</script>
</c:if>
<c:if test="${!check}">
	<script>
		alert("오류가발생하였습니다. (에러발생:"+'${code}'+")");
	</script>
</c:if>
<script>
	location.href='${movepage}';
</script>