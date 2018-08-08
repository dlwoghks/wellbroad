<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no"/>
	<title>WELLBROAD</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" href="../Euro-Travels-master/assets/css/ionicons.min.css">
	<link rel="stylesheet" href="../Euro-Travels-master/assets/css/owl.carousel.css">
	<link rel="stylesheet" href="../Euro-Travels-master/assets/css/owl.theme.css">
	<link rel="stylesheet" href="../Euro-Travels-master/assets/css/flexslider.css" type="text/css">
    <link rel="stylesheet" href="../Euro-Travels-master/assets/css/main.css?ver=1">
	<link rel="stylesheet" href="../custom/css/topCustom.css">

<style>
.section-background {
    background-repeat: no-repeat;
    background-position: bottom;
    background-attachment: fixed;
    text-transform: uppercase;
    background: url(../view/images/section-header.png);
    text-align: center;
    background-size: cover;
}
	
   
</style>		    
		    
		    
</head>
<body>
<!-- Home -->
<div class="topJSP">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="mainpage.do" title="HOME">
					<i><img src="../custom/image/logo(color).png" width="100"></i>
				</a>
			</div> <!-- /.navbar-header -->

	    <!-- Collect the nav links, forms, and other content for toggling -->
	    	<div class="header-upper">
				<c:if test="${not empty userId}"><!-- session 키 userId가 로그인 되어있다면 -->
		       		<span class="header-upper-menu"><a href="../member/Logout.do">Logout</a></span>
	        		<span class="header-upper-menu"><a href="../member/Mypage.do">Mypage</a></span>
	        	</c:if>
	        
	        	<c:if test="${empty userId && empty adminId}"><!-- userID로 로그인 되지 않은 상태라면 -->
					<span class="header-upper-menu"><a href="../member/Login.do">Login</a></span>
        			<span class="header-upper-menu"><a href="../member/Register.do">Join</a></span>
        		</c:if>
        		
        		<c:if test="${not empty adminId && empty userId}"><!-- session 키 adminId가 로그인 되어있다면 -->
		       		<span class="header-upper-menu"><a href="../member/Logout.do">Logout</a></span>
	        	</c:if>
	    	</div>
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty userId && empty adminId}"><!-- userID로 로그인 되지 않은 상태라면 -->
						<li><a href="../member/Login.do" onclick="alert('로그인 후 이용 가능한 서비스입니다.')">MYROUTE</a></li>
					</c:if>
					<c:if test="${not empty userId || not empty adminId}">
						<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=1">MYROUTE</a></li>
					</c:if>
					<li><a href="../tour/tourMain.do">TOUR</a></li>
					<li><a href="../board/noticeList.do" style="margin-right:50px;">BROAD</a></li>
					<!-- <li><a href="../admin/adminpage.do">ADMIN</a></li> -->
				</ul> <!-- /.nav -->
		    </div><!-- /.navbar-collapse -->
	  	</div><!-- /.container -->
	</nav>
</div><!-- /Home -->
	<script	src="${pageContext.request.contextPath}/adminpage_template/js/jquery2.0.3.min.js"></script>
	<script	src="${pageContext.request.contextPath}/adminpage_template/js/modernizr.js"></script>
	<script	src="${pageContext.request.contextPath}/adminpage_template/js/jquery.cookie.js"></script>
	<script	src="${pageContext.request.contextPath}/adminpage_template/js/screenfull.js"></script>
	<script src="../Euro-Travels-master/assets/js/jquery-1.11.2.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/bootstrap.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/owl.carousel.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/contact.js"></script>
    <script src="../Euro-Travels-master/assets/js/jquery.flexslider.js"></script>
	<script src="../Euro-Travels-master/assets/js/script.js"></script>
	<SCRIPT>
$(function() {
	
});
	</SCRIPT>
</body>
</html>