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
/* -------내용--------  */
.title1 > td{
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
						<div class="notice-h"><b>NOTICE</b></div>
					</h2>
					<p class="page-header1">공지게시판 관련 정보입니다.</p>
					<ol class="breadcrumb">
						<li><a href="../main/mainpage.do">HOME</a></li>
						<li>&nbsp;notice</li>
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
		</div><!-- /#sub /.sub -->
	</div><!-- .main -->

	<div class="sub-main">
		<form class="form1">
			<div class="panel panel-sea margin-bottom-30">
				<div class="panel-heading">
					<h3 class="panel-title">| 공지사항</h3>
				</div>
				<div class="panel-body">
					<table class="table1">
						<tr class="title1" style="width: 800px !important;">
							<td>${article.title}</td>
						</tr>
						<tr>
							<td class="title2">작성일&nbsp;&nbsp;${article.day}&nbsp;&nbsp;&nbsp;&nbsp;작성자&nbsp;&nbsp;${article.admin_id}
								&nbsp;&nbsp;&nbsp;&nbsp;조회수&nbsp;&nbsp;${article.readcount}</td>

						</tr>
						<tr>
							<td><textarea class="form-control textarea1" disabled
									style="background: white;">${article.content}</textarea></td>
						</tr>
						<tr>
							<td class="buttongroup">
								<c:if test="${adminId!=null}">
									<input type="button" class="btn btn-default" value="글수정"
										onclick="document.location.href='noticeUpdateForm.do?num=${article.num}&pageNum=${pageNum}'">
									<input type="button" class="btn btn-danger" value="글삭제"
										onclick="document.location.href='noticeDeleteForm.do?num=${article.num}&pageNum=${pageNum}'">
								</c:if>
								<input type="button" class="btn btn-info" value="글목록"
								onclick="document.location.href='noticeList.do'">
							</td>
						</tr>
					</table>
				</div>
			</div><!-- .panel -->
		</form>
	</div>
	<!-- /#sub /.sub -->

	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="../Euro-Travels-master/assets/js/jquery-1.11.2.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/bootstrap.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/owl.carousel.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/contact.js"></script>
    <script src="../Euro-Travels-master/assets/js/jquery.flexslider.js"></script>
	<script src="../Euro-Travels-master/assets/js/script.js"></script>
	
<script type="text/javascript">
$(function() {
	if ('<%=session.getAttribute("adminId")%>' != 'null'){
		$.ajax({url:"../admin/side.jsp",
			success:function(result) {
				$('.main #side').html(result).scrollTop(0);
			}
		});
		$('.main #header').addClass('admin-main');
		$('nav.navbar.navbar-default').css({'left':'60px','z-index':'5'});
	
		$('#side').css('z-index','10');
		$('#sub').css('left','60px');

	}  else {
		$('.main #header').removeClass('admin-main');
		$('nav.navbar.navbar-default').css('left','0px');
		$('#sub').css('left','0px');
	}// if 
});
</script>
</body>
</html>