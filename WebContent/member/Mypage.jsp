<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
/* ------------------------ main ------------------------ */


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

.admin-main {
	margin-left: 60px;
}

.breadcrumb>.active1 {
	color: white;
}

.breadcrumb a {
	text-decoration: none;
} 
</style>

</head>
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
					<h2 class="page-header0">MYPAGE 
						<div class="sub-menu"></div>
					</h2>
					<p class="page-header1">
		     			내정보수정 및 북마크 관련 정보가 담겨있어요!
					</p>
			<ol class="breadcrumb">
				<li><a href="../main/mainpage.do">HOME</a></li>
				<li class="active1">&nbsp;MYPAGE</li>
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
	
	 <div class="sub-main scroll">[ MYPAGE 메인화면입니다! ]</div>
     </div><!-- /#sub /.sub -->
	</div>
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
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