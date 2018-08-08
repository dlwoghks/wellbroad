<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no"/>
<title>WELLBROAD</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myroute.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/section.css">	
<style>

#sub {
	position: relative;
	height: 100%;
}

.header-upper {
	margin-top: 10px;
	float: right;
	margin-right: -30px;
}
 
.header-upper-menu {
	color: gray;
	margin-right: 20px;
}

ul.nav.navbar-nav.navbar-right {
	margin-right: -100px;
}

ul.nav.navbar-nav.navbar-right li {
	margin-top: 65px;
}

ul.nav.navbar-nav.navbar-right li a {
	font-size: 20px;
	font-weight: normal;
	color: #18d5d1;
}

.breadcrumb {
	list-style: none;
	background-color: transparent !important;
	border-radius: 0px;
}

.page-header0 {
	padding-bottom: 9px;
	margin: 40px 0 10px;
	font-size: 60px;
}

	
.notice{
	   	width:500px;
		margin:20px;
		color:#337ab7;
		float:left;
		margin-top: 80px;
		margin-left:8%;
}

.navbar-brand{
	margin-bottom:50px;  
}


.sub-main{
	margin-top: 180px;
}

table {
   background: white;
   border-color:#ececec;
   border-collapse: initial !important;
   margin-bottom:100px;
   margin-left: 20%;
   width: 70%;
   
}


tbody tr td:nth-child(2) {
    width: 75%;
    text-align: left;
}

table th,
table td {
    border-top: 1px solid #e8e8e8;
    border-bottom: none;
    font-size: 15px;
     padding: 10px 12px;
  	text-align: center;
} 



table th {
 	background:#70dad5;
    color: #ffffff;
    text-transform: uppercase;
    width:20%;
}

table tr td {
    background: #FFFFF;
    color: #424242;
}

table tr:nth-of-type(2n+2) td {
  background: #ffffff;
}

input#mail.form-control{
	width:30% !important;
}


</style>  
   
</head>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
var passwordRules =/^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
$(function(){
	$('#password').keyup(function(){
		var password = $('#password').val()
		if(password==''){
			$('#password_signed').html('패스워드를 입력하십시오.').css('color','red');
		}
		else if(!passwordRules.test($.trim($("#password").val()))){
			$('#password_signed').html('패스워드는 8~16자 입니다.').css('color','red');
		}else{
			$('#password_signed').html('사용가능한 패스워드 입니다.').css('color','blue');
		}
	})
	$('#submit').click(function(){
		var password = $('#password').val()
		if(password == ''){
			alert('비밀번호를 입력하십시오.')
			return false
		}else if(!passwordRules.test($.trim($("#password").val()))){
			alert('패스워드는 8~16자 입니다.')
			return false
		}
	})
})

//*************************이메일 유효성 검사
	$(function(){
		var mailRules = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		$('#mail').keyup(function(){
			var mail = $('#mail').val()
			if(mail==''){
				$('#mail_signed').html('메일주소를 입력하십시오.').css('color','red');
			}
			else if(!mailRules.test($.trim($("#mail").val()))){
				$('#mail_signed').html('메일 형식에 맞게 입력하십시오.').css('color','red');
			}else{
				$('#mail_signed').html('사용가능한 메일 입니다.').css('color','blue');
			}
		})
		$('#submit').click(function(){
		var mail = $('#mail').val()
		if(mail == ''){
			alert('메일주소를 입력하십시오.')
			return false
		}else if(!mailRules.test($.trim($("#mail").val()))){
			alert('메일 형식에 맞게 입력하십시오.')
			return false
		}
	})
})
//**************************전화번호 유효성
$(function(){
	var phoneRules = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
	$('#phone').keyup(function(){
		var phone = $('#phone').val()
		if(phone == ''){
			$('#phone_signed').html('휴대번호를 입력하십시오.').css('color','red');
		}
		else if(!phoneRules.test($.trim($("#phone").val()))){
			$('#phone_signed').html('휴대번호 형식에 맞게 입력하십시오.').css('color','red');
		}else{
			$('#phone_signed').html('사용가능한 번호 입니다.').css('color','blue');
		}
	})
	$('#submit').click(function(){
		var phone = $('#phone').val()
		if(phone == ''){
			alert('휴대번호를 입력하십시오.')
			return false
		}else if(!phoneRules.test($.trim($("#phone").val()))){
			alert('휴대번호를  형식에 맞게 입력하십시오.')
			return false
		}
	})
})

</script>
<body>
  <div class="main">
		<!-- ADMIN -->
		<div id="side"></div><!-- /#side -->
		
		<!-- Home -->
		<div id="header">
			<jsp:include page="../main/top.jsp"/>
		
			<!-- Section Background -->
			<section class="section-background">
				<div class="container">
					<h2 class="page-header0">EDIT PROFILE 
						<div class="sub-menu"></div>
					</h2>
					<p class="page-header1">
		     			내정보수정 관련 정보가 담겨있어요!
					</p>
			<ol class="breadcrumb">
				<li><a href="../main/mainpage.do">HOME</a></li>
				<li class="active1">&nbsp;EDIT PROFILE</li>
			</ol>
				</div> <!-- /.container -->
			</section> <!-- /.section-background -->
		</div><!-- /#header -->
		
		<!-- 본격적으로 custom한 페이지구현 -->
		<div class="sub" id="sub">
			<div class="sub-menu"><!-- 왼쪽 메뉴 -->
				<ul class="nav navbar-nav">
					<li><a href="MemberUpdate.do">EDIT PROFILE</a></li>
					<li><a href="bookmark.do?id=${userId}">BOOKMARK</a></li>
				</ul><!-- /.nav -->
			</div><!-- /.sub-menu -->
	
	 
     </div><!-- /#sub /.sub -->
	</div>
		<div class="row1">
				<div class="notice">
						<b><h2>기본정보</h2>| 아이디,이름,생년월일,성별은 변경될 수 없습니다. &nbsp; </b>
				</div>
		</div>
         
         <div class="sub-main">
         <br><br>
         <form name="regForm" method="post" action="MemberUpdatePro.do">
               <table align="center" class="mytable table-hover" style="color:#4682B4;">
             
                   <tr>
                     <th>아이디</th>
                     <td>
                        <!-- input box에 들어가 있는 데이터가 아니기때문에 id값을 전달X -->
                        ${member.id}
                     </td>
                  </tr>
                  <tr>
                     <th>패스워드</th>
                     <td><input class="form-control" type="password" id="password" name="password" 
                      value="${member.password}">
                     <span id="password_signed"></span>
                     </td>
                  </tr>

                  <tr>
                     <th>이름</th>
                     <td>${member.name}</td>
                  </tr>
                  <tr>
                     <th>생년월일</th>
                     <td>${member.birth}</td>
                  </tr>
                  <tr>
                     <th>성별</th>
                     <td>
                     <c:if test="${member.gender == 'M'}">
                     	남자
                     </c:if>
                     <c:if test="${member.gender == 'F'}">
                     	여자
                     </c:if>
                     </td>
                  </tr>
                  <tr>
                     <th>메일</th>
                     <td>
                     	<input class="form-control" type="text" id ="mail"name="mail" size="27" value="${member.mail}" placeholder="ex)xxx@xxxx.xxx">
                     	<span id="mail_signed"></span>
                     </td>
                  </tr>
                  <tr>
                     <th>메일수신동의</th>
                     <td>
                     	<c:if test="${member.mailagree =='O'}">
                     		<input type="checkbox" name="mailagree" id="mailagree"size="20" checked>
                     	</c:if>
                     	<c:if test="${member.mailagree !='O'}">
                     		<input type="checkbox" name="mailagree" id="mailagree"size="20">
                     	</c:if>
                     	메일수신동의</td>
                  </tr>
                  <tr>
                     <th>휴대번호</th>
                     <td><input class="form-control" type="text" name="phone" id="phone" size="20"
                        value="${member.phone}">
                         <span id="phone_signed"></span>
                        </td>
                       
                  </tr>
                  <tr>
                     <td colspan="3" align="center">
                     <input type="submit" class="btn btn-info" value="수정완료" id="submit"> 
                     <input type="reset" class="btn btn-info" value="다시쓰기"> 
                     <input type="button" class="btn btn-info" value="수정취소" onclick="history.back()"></td>
                		</td>
                  </tr>
                  <!-- form태그 내부에 위치에 상관없이 hidden객체를 이용해서 전달  -->
               </table>
                  <input type="hidden" name="id" value="${member.id}">
                  <input type="hidden" name="name" value="${member.name}">
                  <input type="hidden" name="birth" value="${member.birth}">
                  <input type="hidden" name="gender" value="${member.gender}">
            </form>
         </div><!-- /.sub-main -->
      </div><!-- /#sub /.sub -->
   </div><!-- /.main -->
   
   <script src="../Euro-Travels-master/assets/js/jquery-1.11.2.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/bootstrap.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/owl.carousel.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/contact.js"></script>
    <script src="../Euro-Travels-master/assets/js/jquery.flexslider.js"></script>
   <script src="../Euro-Travels-master/assets/js/script.js"></script>
<script type="text/javascript">
$(function() {
   if (<%=session.getAttribute("login")%>!= null) {
      $.ajax({url:"../admin/side.jsp",
         success:function(result) {
            $('.main #side').html(result).scrollTop(0);
         }
      });
      $('.topJSP nav.navbar.navbar-default').css({'left':'60px','z-index':'5'});
      $('.main #side').css('z-index','10');
      $('.main #sub').addClass('admin-main');
   } else {
      $('nav.navbar.navbar-default').css('left','0px');
      $('.topJSP nav.navbar.navbar-default').css({'left':'0px'});
      $('.main #sub').removeClass('admin-main');
   }// if
});

</script>
</body>
</html>