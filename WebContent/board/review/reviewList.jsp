<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no"/>
<title>WELLBROAD</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap1.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/custom/css/boardCustom.css">
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
		<div class="row1">
			<div class="notice">
				<b><h2>후기게시판</h2>| 후기 게시판 입니다 &nbsp; </b>
			</div>
		</div>
					
   		<div class="article">
   			<table>
				<tr>
					<div class="write">
						<c:if test="${userId!=null}">
							<a href="reviewWriteForm.do" style="float:left;">글쓰기</a>
						</c:if>
					</div>
				</tr>
			</table>
			<c:if test="${pgList.count==0}">
				<table border="1" class="tablecontent1"> 
					<tr>
						<td align="center">게시판에 저장된 글이 없습니다.</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${pgList.count > 0}">
				<table border="1" class="tablecontent1">
					<tr height="30" style="background:#85ddff;">
						<th align="center">번 호</th>
						<th align="center" width="60%">제 목</th>
						<th align="center">작성일</th>
						<th align="center">작성자</th>
						<th align="center">조회</th>
					</tr>
				
					<c:set var="number" value="${pgList.number}" />
					<c:forEach var="article" items="${articleList}">
						<tr height="30">
							<td style="text-align:center">
								<c:out value="${number}"/> 
								<c:set var="number" value="${number-1}"/>
							</td>
							<td id="titlecolor" align="left">
								<c:if test="${article.re_level !=0 }">
									<c:forEach var="i" begin="1" end="${article.re_level}" step="1">&nbsp;&nbsp;</c:forEach>
									└
								</c:if>
								<a href="reviewContent.do?num=${article.num}&pageNum=${pgList.currentPage}&id=${userId}">${article.title}</a>
							</td>
							<td style="text-align:center">
								<fmt:formatDate value="${article.day}" timeStyle="medium" pattern="yy.MM.dd(hh:mm)" />
							</td>
							<td style="text-align:center">${article.id}</td>
							<td style="text-align:center">${article.readcount}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>

			<div class="num-h">
				<ul class="pagination">
					<c:if test="${pgList.startPage>pgList.blockSize}">
						<li><div class="pagination-next">
								<a href="reviewList.do?pageNum=${pgList.startPage-pgList.blockSize}
							&search=${search}&searchtext=${searchtext}"><<</a>
							</div></li>
					</c:if>

					<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
						<a href="reviewList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
							<c:if test="${pgList.currentPage==i}">
								<li>${i}</li>
							</c:if> <c:if test="${pgList.currentPage!=i}">
								<li>${i}</li>
							</c:if>
						</a>
					</c:forEach>

					<c:if test="${pgList.endPage<pgList.pageCount}">
						<li><div class="pagination-next">
								<a href="reviewList.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}
						&searchtext=${searchtext}">>></a>
							</div></li>
					</c:if>

				</ul>
			</div>
		</div> <!--  article-->
		<form name="test" action="reviewList.do" id="searchform">
			<select name="search" class="form-control">
				<option value="title">제목</option>
				<option value="title_content">제목+본문
				<option value="id">작성자</option>
			</select> 
			<input class="form-control" type="text" size="15" name="searchtext">&nbsp; 
			<input class="btn rounded btn-info" type="submit" value="검색">
		</form>
	</div><!-- .#sub-main -->
	
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
	} else {
		$('.main #header').removeClass('admin-main');
		$('nav.navbar.navbar-default').css('left','0px');
		$('#sub').css('left','0px');
	}// if 
});
</script>
</body>
</html>