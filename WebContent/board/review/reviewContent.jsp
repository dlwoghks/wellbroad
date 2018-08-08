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
		 <form class="form1">
			<div class="panel panel-sea margin-bottom-30">
				<div class="panel-heading">
					<h3 class="panel-title"> | 여행후기 </h3>
				</div>
			</div>
			<div class="panel-body">
				<table class="table1">
					<tr class="title1">
					    <td>${article.title}</td>
						<c:if test="${userId!=null}"><!-- 로그인되어있을때 -->
		    				<td class="bookmark1">
				      	    	<c:if test="${check==1}">					    			       		 		
				       		 		<a onclick="deleteBookmarkAction()">
				        				 <img src="../custom/image/bookmark/bookmark.png"  width="20" height="20">
			        				</a>
			    				</c:if>
			    				<c:if test="${check==0}">
									<a>
		        						<img src="../custom/image/bookmark/unbookmark.png"   width="20" height="20" onclick="addBookmarkAction()" "cursor:pointer">
			        				</a>
		        				</c:if>
		        			</td>
					   	</c:if><!-- ${userId!=null} -->
					 </tr>
					 <tr>
					 	<td class="title2">작성일&nbsp;&nbsp;${article.day}&nbsp;&nbsp;&nbsp;&nbsp;작성자&nbsp;&nbsp;${article.id}
						&nbsp;&nbsp;&nbsp;&nbsp;조회수&nbsp;&nbsp;${article.readcount}</td>
					 </tr>
					<tr>
						<td> 
							<textarea class="form-control textarea1" disabled style="background:white;">${article.content}</textarea>
						</td>
				    </tr>
					<tr>      
						<td class="buttongroup">
						    <c:if test="${userId!=null}"><!-- 로그인되어있을때 -->
							   <input type="button" class="btn btn-success" value="답글쓰기" 
      								 onclick="document.location.href='reviewWriteForm.do?num=${article.num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}'">
       								&nbsp;&nbsp;&nbsp;&nbsp;
       						</c:if><!-- ${sUserId!=null} -->
      							<c:if test="${userId==article.id}"><!-- 작성자일때 -->
								  <input type="button" class="btn btn-default" value="글수정" class="btn btn-default" onclick="document.location.href='reviewUpdateForm.do?num=${article.num}&pageNum=${pageNum}'">
								   &nbsp;&nbsp;&nbsp;&nbsp;
								  <input type="button" class="btn btn-danger" value="글삭제" 
							       onclick="document.location.href='reviewDeleteForm.do?num=${article.num}&pageNum=${pageNum}&id=${userId}'">
								   &nbsp;&nbsp;&nbsp;&nbsp;
						   </c:if><!-- sUserId==article.id -->
					       <input type="button" class="btn btn-info" value="글목록" onclick="document.location.href='reviewList.do'">
					  	</td>
					</tr>
				</table> 
			</div><!-- .panel-body --> 
		</form>   
	</div><!-- .sub-main -->
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="../Euro-Travels-master/assets/js/jquery-1.11.2.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/bootstrap.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/owl.carousel.min.js"></script>
    <script src="../Euro-Travels-master/assets/js/contact.js"></script>
    <script src="../Euro-Travels-master/assets/js/jquery.flexslider.js"></script>
	<script src="../Euro-Travels-master/assets/js/script.js"></script>
<script type="text/javascript">
$(function() {
	 if ('<%=session.getAttribute("adminId")%>' != 'null') {
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
function addBookmarkAction(){
   if(confirm("북마크에 추가하시겠습니까?")){
      location.href="bookmarkInsrt.do?num=${article.num}&id=${userId}&title=${article.title}&pageNum=${pageNum}";
   }
}
function deleteBookmarkAction(){
if(confirm("북마크를해제하시겠습니까?")){
      location.href="bookmarkDelete.do?num=${article.num}&id=${userId}&pageNum=${pageNum}&delete=1";
   }
}
</script>
</body>
</html>