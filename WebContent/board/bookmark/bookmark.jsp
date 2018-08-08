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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myroute.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/section.css">	
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap1.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css?ver=1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/custom/css/baseCustom.css">
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

.breadcrumb>li{
	color:white;
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




/*--------  */

   .tablecontent1{
    width: 100%;
    text-align: center;
    border: #4682B4;
    padding: 70px;
    margin-left: -70px;
    
  
   }

	
	#titlecolor:hover{
		background-color:#F5FFFA;
	}
	

	
	.notice{
	   	width:500px;
		margin:20px;
		color:#337ab7;
		float:left;
		margin-top: 30px;
		margin-left: -70px;
	}
	
	.num-h{
		margin:0px;
		margin-top:20px;
		margin-bottom:40px;
		margin-right:120px;
		text-align:center;
		
	 	
	}	

	.article{
		width:90%;
		margin-left:150px;
	}
	
	.row1{
	    margin-left: 150px;
   		margin-top: 50px !important;
		height:150px;
	}
    .write{
    		margin-right:-685px;
    		magin:20px;
    		margin-left: 84%;
    }
    
    .pagination-next{
		display:inline-block;
	}
	
	.pagination-next a:hover{
		color:white;
		text-decoration: none;
	}
    
   #searchform{
    		color:black;
    		margin-bottom:300px;
    		text-align:center;
    		margin-left:-50px;
    }
    	
	td{
	color:black;
	text-decoration:none;
	} 
	
	
.btn{
	margin-top:-5px;
}
	

.form-control{
	display:inline-block !important;
	margin-right:10px;
	border-radius:2px;
}

/*--------------------  */

.navbar-brand{
	margin-bottom:50px;  
}



table {
  background: white;
  border-collapse: collapse;
  margin: 1.25em 0 0;
  margin-left:15px;
  width: 98%;
}




table tr,
table th,
table td {
    border: none;
    border-bottom: 1px solid #e8e8e8;
    font-size: 15px;
   
}

table th,
table td {
  padding: 10px 12px;
  text-align: left;
}

table th {
  	background: #70dad5;
    color: #ffffff;
    text-transform: uppercase;
}

table tr td {
    background: #FFFFF;
    color: #424242;
}

table tr:nth-of-type(2n+2) td {
  background: #ffffff;
}

.pagination li{
	align:center;
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
		
			<!-- Section Background -->
			<section class="section-background">
				<div class="container">
					<h2 class="page-header0">
						BOOKMARK 
						<div class="sub-menu"></div>
					</h2>
					<p class="page-header1">
		     			북마크 관련 정보가 담겨있어요!
					</p>
			<ol class="breadcrumb">
				<li><a href="../main/mainpage.do">HOME</a></li>
				<li class="active1">&nbsp;BOOKMARK</li>
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
		
			
			<div class="sub-main">
			<div class="row1">
				<div class="notice">
						<b><h2>북마크</h2>| 북마크 게시판 입니다 &nbsp; </b>
				</div>
		    </div>
				
					<div class="article">
				<%-- 	<b>(전체 글:${pgList.count})</b> --%>
				<!-- 	
					<table class="tablecontent">
						<tr>
						</tr>
					</table> -->
					<c:if test="${pgList.count==0}">
						<table border="1" class="tablecontent1">
							<tr>
								<td align="center">북마크에 저장한 글이 없습니다.</td>
							</tr>
						</table>
					</c:if>
					<c:if test="${pgList.count > 0}">
						<table border="1" class="tablecontent1">
						<tr height="30" style="background:#70dad5;">
								<td align="center" style="color:white;">번 호</td>
								<td align="center" style="color:white;">제목</td>
								<!-- <td align="center" width="">아이디</td> -->
							</tr>
							<c:set var="number" value="${pgList.number}" />
							<c:forEach var="article" items="${articleList}">
								<tr height="30">
									<td align="center">
									<a href="bookmarkDelete.do?num=${article.review_num}&id=${userId}&delete=1">
										<img src="../custom/image/bookmark/bookmark.png" width="20" height="20">
									</a>
									<c:out value="${number}" /> 
									<c:set var="number" value="${number-1}" /></td>
									<td id="titlecolor">
								<center>
								<a href="reviewContent.do?num=${article.review_num}&pageNum=${pgList.currentPage}&check=1">
											 ${article.title}</a></center>
								</tr>
							</c:forEach>
					</table> 
					</c:if>



					<div class="num-h">
						<ul class="pagination">
							<c:if test="${pgList.startPage>pgList.blockSize}">
								<li><div class="pagination-next">
										<a href="bookmark.do?pageNum=${pgList.startPage-pgList.blockSize}
							&search=${search}&searchtext=${searchtext}"><<</a>
									</div></li>
							</c:if>

							<c:forEach var="i" begin="${pgList.startPage}"
								end="${pgList.endPage}">
								<a href="bookmark.do?pageNum=${i}&search=${search}&searchtext=${searchtext}&id=${userId}">

									<c:if test="${pgList.currentPage==i}">
										<li>${i}</li>
									</c:if> <c:if test="${pgList.currentPage!=i}">
										<li>${i}</li>
									</c:if>

								</a>
							</c:forEach>
							<c:if test="${pgList.endPage<pgList.pageCount}">
								<li><div class="pagination-next">
										<a href="bookmark.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}
										&searchtext=${searchtext}">>></a>
									</div></li>
							</c:if>


						</ul>
					</div>



					<p>
					<form name="test" action="bookmark.do" id="searchform">
						<select name="search" class="form-control">
							<option value="title">제목</option>
							<!-- <option value="admin_id">작성자</option> -->
						</select> 
						<input class="form-control" type="text" size="15" name="searchtext">&nbsp; 
					    <input class="btn rounded btn-info" type="submit" value="검색">
						<input type="hidden" name="id" value="${userId}">
					</form>
				
			<!-- 	</table> -->
					</div>
		
		</div><!-- /#sub /.sub -->
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
	if ('<%=session.getAttribute("sUserId")%>'== 'admin') {
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