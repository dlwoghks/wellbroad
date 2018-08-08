<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
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
<link rel="stylesheet" href="../Euro-Travels-master/assets/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>
</head>
<script>
	$(function(){
		$('#submit').click(function(){
			var id = $('#id').val()
			var birth = $('#birth').val()
			if(id == ''){
				alert('아이디를 입력하십시오.')
				$('#id').focus();
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
<body onload="document.findPassword.id.focus()">
	<center>
		<div id="content">
			<a title="HOME" href="mainpage.do"><img src="../custom/image/logo2.png" class="logo"></a>
			<form name="findPassword" method="post" action="FindPasswordPro.do" class="form-inline" > 
				<h3>비밀번호 찾기</h3>
					<div class="inputStyle"><input type="text" name="id" id="id" class="input-lg form-control" placeholder="아이디"></div>	
					<div class="inputStyle"><input type="text" name="birth" id="birth" class="input-lg form-control" placeholder="생년월일"></div>
					<div><input type="submit" value="찾기" class="btn btn-link" id="submit"></div> 
			</form>
		</div>
	</center>
</body>
</html>