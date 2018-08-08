<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap1.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/custom/css/boardCustom.css">
<title>WELLBROAD</title>

<style>
/* -------내용--------  */
.title1 > td {
	/* color: #337ab7 !important; */
	font-size: 30px !important;
}

.title2 {
	color: gray;
}

.form1 {
	float:left;
	margin-left:60px;
	margin-top:40px;
}

.form-control {
	margin-top: 20px;
	width: 810px !important;
	height: 500px !important;
}

/* -------버튼--------  */
.buttongroup {
	float: right;
	height: 50px;
	margin-top: 20px;
	border-bottom: white;
}

.btn-info {
	background-color: #5bc0de !important;
}

.btn-danger {
	background-color: #f1433e !important;
	border-color: #f1433e !important;
}

.btn-success {
	background-color: #3379b7 !important;
	border-color: #3379b7 !important;
}
.bookmark1>a>img {
	margin-left: -50px;
} 

/* -------delete--------  */
.sub-main {
	text-align:center;
}

.form1 {
	padding:0px 30%;
	margin-left:0px;
}

</style>

</head>
<body>
	<div class="main">
		<!-- ADMIN -->
		<div id="side"></div><!-- /#side -->
		
		<!-- Home -->
		<div id="header">
			<jsp:include page="../../main/top.jsp"/>
			<section class="section-background">
				<div class="container">
					<h2 class="page-header0">
						<div class="notice-h"><b>REVIEW</b></div>
					</h2><!-- Board클릭시 보이는 타이틀 -->
					<p class="page-header1">
		     			여행리뷰 관련 정보입니다.
					</p>
					<ol class="breadcrumb"><!-- 게시판 내의 메뉴바 같은 느낌  -->
						<li><a href="../main/mainpage.do">Home</a></li>
						<li>&nbsp;review</li>
					</ol>
				</div> <!-- /.container -->
			 </section> <!-- /.section-background -->
		</div><!-- /#header -->
		
		<!-- 본격적으로 custom한 페이지구현 -->
		<div class="sub" id="sub">
			<div class="sub-menu"><!-- 왼쪽 메뉴 -->
				<ul class="nav navbar-nav">
					<li><a href="noticeList.do">NOTICE</a></li>
					<li><a href="reviewList.do">REVIEW</a></li>
					<li><a href="qnaList.do">QnA</a></li>
				</ul><!-- /.nav -->
			</div><!-- /.sub-menu -->
		</div><!-- .sub  -->
	</div><!-- /.main -->
	<div class="sub-main">
		<form method="POST" class="form1" name="delForm" action="reviewDeletePro.do?pageNum=${pageNum}&num=${num}&id=${userId}">
			<div class="panel panel-sea margin-bottom-30">
				<div class="panel-heading">
					<h3 class="panel-title">글삭제</h3>
				</div>
				<div class="panel-body">
					<table class="table1">
						<tr>
							<td><h4>정말로 삭제하시겠습니까?</h4></td>
						</tr>

						<tr height="30">
							<td class="buttongroup"><input type="submit" class="btn btn-danger" value="글삭제">
								<input type="button" class="btn btn-info" value="글목록" onclick="document.location.href='reviewList.do?pageNum=${pageNum}'">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div><!-- /.main -->

	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="../Euro-Travels-master/assets/js/jquery-1.11.2.min.js"></script>
	<script src="../Euro-Travels-master/assets/js/bootstrap.min.js"></script>
	<script src="../Euro-Travels-master/assets/js/owl.carousel.min.js"></script>
	<script src="../Euro-Travels-master/assets/js/contact.js"></script>
	<script src="../Euro-Travels-master/assets/js/jquery.flexslider.js"></script>
	<script src="../Euro-Travels-master/assets/js/script.js"></script>
	<script type="text/javascript">
		$(function() {
			if (
	<%=session.getAttribute("login")%>!= null) {
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