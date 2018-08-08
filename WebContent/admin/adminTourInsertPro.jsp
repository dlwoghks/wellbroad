<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${check ==1}">
	<script>
		alert("추가되었습니다.");
		location.href='${movepage}';
	</script>
</c:if>
<c:if test="${check !=1}">
	<script>
		alert("오류가발생하였습니다.");
		location.href='${errorpage}';
	</script>
</c:if>
