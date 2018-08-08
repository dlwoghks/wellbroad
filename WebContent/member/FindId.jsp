<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="../Euro-Travels-master/assets/css/bootstrap.min.css">
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
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(function(){
		$('#submit').click(function(){
			var name = $('#name').val()
			var birth = $('#birth').val()
			if(name == ''){
				alert('이름을 입력하십시오.')
				$('#name').focus();
				return false
			}
			if(birth == ''){
				alert('생년월일을 입력하십시오.')
				$('#birth').focus();
				return false
			}
		})
	})
</script>
<body onload="document.findId.name.focus()">
	<center>
		<div id="content">
			<a title="HOME" href="mainpage.do"><img src="../custom/image/logo2.png" class="logo"></a>
			<form name="findId" method="post" action="FindIdPro.do" class="form-inline">
				<h3>아이디 찾기</h3>
					<div class="inputStyle"><input type="text" name="name" id="name" class="input-lg form-control" placeholder="이름"></div>	
					<div class="inputStyle"><input type="text" name="birth" id="birth" class="input-lg form-control" placeholder="생년월일"></div>
					<div><input type="submit" value="찾기" class="btn btn-link" id="submit"></div>
			</form>
		</div>
	</center>
</body>
<!-- 
<BODY class="signup-body" onload="document.login.id.focus()">
	<div class="agile-signup">	
			
			<div class="content2">
				<div class="grids-heading gallery-heading signup-heading">
					<a title="HOME" href="mainpage.do"><img src="../view/icon/logowhite.png"><h2>WELLBROAD</h2></a>
				</div>
	
	           <form name="login" method="post" action="LoginPro.do" >
					<input type="text" NAME="id" " value="아이디" placeholder="아이디" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">
					<input type="password"  NAME="password" value="비밀번호" placeholder="비밀번호" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">
					<input type="submit" class="register" value="Login" onclick="loginCheck()">
				</form>
	
		       <!--     <div class="signin-text"> -->
		       <!-- 
					<INPUT TYPE="button" class="btn btn-link" value="아이디 찾기 " onclick="findIdCheck()" style="color:#739bda;text-decoration:none;">
					<INPUT TYPE="button" class="btn btn-link" value="비밀번호 찾기 " onclick="findPasswordCheck()" style="color:#739bda;text-decoration:none;">
					<INPUT TYPE="submit" class="btn btn-link" value="회원가입" onclick="memberRegister()" style="color:#739bda;text-decoration:none;">
					
					
					<div class="clearfix"> </div>
			<!--</div> -->
			
		<!--  	</div>
	<!--  footer -->
		<!-- 	<div class="copyright">
				<p>© 2018 Colored . All Rights Reserved . Design by WELLBROAD</p>
			</div>
			<!--  //footer --><!-- </div>
<!--  	<script src="../custom/js/memberCustom.js"></script>  
 </BODY>
 -->
</html>
