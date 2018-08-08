<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   String id=request.getParameter("id");
%>
<HTML>
 <HEAD>
  <TITLE>회원탈퇴 확인</TITLE>
<link href="style.css" rel="stylesheet"  type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script.js?ver=2">
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
	   function delCheck(){
        if(document.del.password.value==""){
           alert("비밀번호를 입력해주세요!");
		   document.del.password.focus();
		   return;
		}
	   //예정대로 action값이 지정한 페이지로 이동
        document.del.submit();//전송
	  }
</SCRIPT>
 </HEAD>

 <BODY onload="document.del.password.focus()">
  <center>
     <TABLE>
    <form name="del" method="post" 
              action="DeletePro.do?id=${id}">
     <TR>
		<TD align="center" colspan="2">
	<b>${id}님 비밀번호를 입력해주세요</b></TD>
     </TR>

     <TR>
		<TD>비밀번호</TD>
		<TD><INPUT TYPE="password" NAME="password"></TD>
     </TR>
     
     <TR>
	    <TD>
		<INPUT TYPE="button" value="탈퇴" onclick="delCheck()">&nbsp;&nbsp;&nbsp;
		<INPUT TYPE="button" value="취소"
        onclick="document.location.href='Login.do?id=${id}'">
		</TD>
     </TR>
	 
	 </form>
     </TABLE>
  </center>
 </BODY>
</HTML>
