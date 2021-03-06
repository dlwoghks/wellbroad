<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap1.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/custom/css/boardCustom.css">
<title>WELLBROAD</title>
<style>
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

button, html input[type=submit]:hover{
   background-color:#337ab7 !important;
   border-color:#337ab7 !important;
   color:white;
}
</style>

</head>
<body>
	<div class="main">
		<!-- ADMIN -->
		<div id="side"></div> <!-- /#side -->

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
			<div class="sub-menu">
				<!-- 왼쪽 메뉴 -->
				<ul class="nav navbar-nav">
					<li><a href="noticeList.do">NOTICE</a></li>
					<li><a href="reviewList.do">REVIEW</a></li>
					<li><a href="qnaList.do">QnA</a></li>
				</ul> <!-- /.nav -->
			</div> <!-- /.sub-menu -->
		</div><!-- .sub  -->
	</div><!-- /.main -->
	<div class="sub-main">
		<form method="post" class="form1" name="writeform" action="reviewUpdatePro.do?num=${article.num}&pageNum=${pageNum}">
			<div class="panel panel-sea margin-bottom-30">
				<div class="panel-heading">
					<h3 class="panel-title">글수정</h3>
				</div>
				<div class="panel-body">
					<table class="table1">
						<tr>
							<td>이 름&nbsp;&nbsp; ${article.id}
								<input type="hidden" size="10" maxlength="10" name="id" value="${article.id}">
								<input type="hidden" name="num" value="${article.num}">
							</td>
						</tr>
						<tr>
							<td>제 목&nbsp;&nbsp;<input type="text" size="40"
								maxlength="50" name="title" value="${article.title}"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" name="content" rows="13" cols="40">${article.content}</textarea></td>
						</tr>
						<tr>
							<td class="buttongroup">
								<input type="submit" class="btn btn-success" value="글수정">
								<input type="reset" class="btn btn-default" value="다시작성">
								<input type="button" class="btn btn-info" value="목록보기"
								onclick="document.location.href= 'reviewList.do?pageNum=${pageNum}'">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div> <!-- /#sub /.sub -->

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