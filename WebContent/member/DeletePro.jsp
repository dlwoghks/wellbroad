<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${check==1}">
	<%
	    session.invalidate();//세션해제->더이상 로그인상태를 유지X
	%>
	<script>
		alert('${id}님 성공적으로 탈퇴처리 되었습니다.')
		location.href = "Login.do" //로그인 창으로 전환
	</script>
</c:if>
<c:if test="${check!=1}">
	<script>
		alert("비밀번호가 틀립니다.\n 다시 한번 확인하시기 바랍니다.")
		history.back();//history.go(-1)
	</script>
</c:if>





<%-- <%
       if(check==1){  //탈퇴에 성공했다면
    	   session.invalidate();//세션해제->더이상 로그인상태를 유지X
    %>
    	 <script>
    	      alert('<%=id%>님 성공적으로 탈퇴처리 되었습니다.!')
    	      location.href="Login.jsp" //로그인 창으로 전환
    	 </script>
     <%}else { %>
          <script>
             alert("비밀번호가 틀립니다.\n 다시 한번 확인하시기 바랍니다.")
             history.back();//history.go(-1)
          </script>
     <% } %>
  --%>




