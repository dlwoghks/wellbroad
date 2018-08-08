<%@page contentType="text/html;charset=UTF-8"%>
<HTML>
 <HEAD>
  <title>로그인 에러</title>
  <link rel="stylesheet" href="../Euro-Travels-master/assets/css/bootstrap.min.css">
  <link href="style.css" rel="stylesheet"  type="text/css">
 </HEAD>
 <style>
 	.loginErrorDiv{
 		margin-top:200px;
 	}
 </style>
 <BODY>
 <jsp:include page="../main/top.jsp"></jsp:include>
 <div id="top"></div>
  <center>
  <div class="loginErrorDiv">
    <p><b>로그인에 실패하셨습니다.<br>
	아이디및 비밀번호가 틀렸습니다.</b></p>
	<input type="button" class="btn btn-link" value="다시쓰기"  onclick="history.back()">
  </div>
  </center>
 </BODY>
</HTML>
