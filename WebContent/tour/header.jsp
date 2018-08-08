<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <html class="noIE" lang="en-US"> -->
   <head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<meta name="viewport" content="width=device-width, initial-scale = 1.0,  maximum-scale=1.0, user-scalable=no" />
<title>MYROUTE</title>
   
 <!--    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/owl.carousel.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/owl.theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/flexslider.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/main.css?ver=7">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myroute.css?ver=31">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/section.css?ver=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker3.css?ver=0">  <!-- 날짜 -->
<!-- 	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css" rel="stylesheet" type="text/css" /> -->
   <style>
body {
	color: black;
}

#sub {
	position: relative;
	width: 150px;
	height: 100%;
}

.header-upper {
	margin-top: 50px;
	float: right;
	margin-right: -30px;
	float: right;
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

h2 .page-header0 {
	margin-bottom: 0px;
	font-size: 60px;
}

.page-header1 {
	color: #fff;
	font-size: 20px;
}

/* ------------------------admin으로 로그인해서 side.jsp 추가되었을 때 ------------------------ */
.admin-main {
	margin-left: 60px;
}

.breadcrumb a {
	color: #fff;
	border: 1px solid transparent;
	text-decoration: none;
	padding: 5px;
}

/* ------ */
.tablecontent1 {
	width: 80%;
	text-align: center;
	border: #4682B4;
	padding: 70px;
	margin-left: 90px;
}

#titlecolor:hover {
	background-color: #F5FFFA;
}

.notice {
	width: 500px;
	margin: 20px;
	color: #000080;
	float: left;
	margin-top: 30px;
	margin-left: 90px;
}

.num-h {
	margin-top: 20px;
	margin-bottom: 40px;
	margin-left: 505px;
}

.article {
	margin-left: 150px;
}

.row1 {
	margin-left: 150px;
	margin-top: 50px !important;
	height: 150px;
}

.write {
	margin-right: -685px;
	magin: 20px;
	margin-left: 84%;
}

#searchform {
	color: black;
	margin-left: 380px;
	margin-bottom: 300px;
}

.notice-h {
	padding-bottom: 9px;
	font-size: 60px;
	margin: 40px 0 10px;
}

td {
	color: black;
	text-decoration: none;
}

.day-count {
	color: black;
}

.icon-group {
	color: black;
}
</style>
  
</head>
<body>
	<div class="main">
		<!-- ADMIN -->
			<div id="side"><!-- /#side -->
		</div>
		<!-- Home -->
		<div id="header">
			<jsp:include page="../main/top.jsp"/>
		
			<section class="section-background">
				<div class="container">
					<h2 class="page-header0">
						<div class="notice-h">MYROUTE<div>
						<!-- <div class="sub-menu"></div> -->
					</h2>
					<p class="page-header1">
		     			맞춤형 여행코스짜기!&nbsp; 나만의 여행지를 만들고 공유해 보아요!  
					</p>
			<ol class="breadcrumb">
				<li><a href="../main/mainpage.do">HOME</a></li>
				<li>&nbsp;MYROUTE</li>
			</ol>
				</div> <!-- /.container -->
			</section> <!-- /.section-background -->
		</div><!-- /#header -->
		
		
			<div class="sub" id="sub">
	     <div class="sub-menu">
	     <ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=1">COURSE1</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=2">COURSE2</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=3">COURSE3</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=4">COURSE4</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=5">COURSE5</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=6">COURSE6</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=7">COURSE7</a></li>									

		</ul>
		</div>
	</div>
  </div>
	  
	<script	src="${pageContext.request.contextPath}/adminpage_template/js/jquery2.0.3.min.js"></script>
	<script	src="${pageContext.request.contextPath}/adminpage_template/js/modernizr.js"></script>
	<script	src="${pageContext.request.contextPath}/adminpage_template/js/jquery.cookie.js"></script>
	<script	src="${pageContext.request.contextPath}/adminpage_template/js/screenfull.js"></script>
<!-- </div> -->
</body>

</html>