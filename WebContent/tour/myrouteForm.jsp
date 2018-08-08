<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.io.*,com.wellbroad.db.beans.myroute.*,java.util.*"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<%
String[] codelist= null;
String[] iconlist= null;
String[] namelist= null;
MyRouteDTO myroute = null;
int days = 1;
int num = 0;
boolean isRead = false;
String course = "1";
String userid ="";

isRead = (boolean) request.getAttribute("isRead");
course = (String) request.getAttribute("course");

if(!isRead){
	response.sendRedirect(
			request.getContextPath() + "/tour/myrouteFormWrite.do?course=" + course + "&edit=write");
	
}

if(session.getAttribute("userId") != null){
	userid = (String)session.getAttribute("userId");	
	myroute = (MyRouteDTO)request.getAttribute("myroute");
	codelist = (String[]) request.getAttribute("codelist");
	iconlist = (String[]) request.getAttribute("iconlist");
	namelist = (String[]) request.getAttribute("namelist");
	days = (int) request.getAttribute("days");
	num = (int) request.getAttribute("num");
}



%>
<c:set value="<%=isRead %>" var="isRead"/>
<c:set value="<%=myroute %>" var="myroute"/>
<c:set value="<%=codelist %>" var="codelist"/>
<c:set value="<%=iconlist %>" var="iconlist"/>
<c:set value="<%=namelist %>" var="namelist"/>
<c:set value="<%=days %>" var="days"/>


<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.2.1.min.js"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myroute.css?ver=31">


<%-- <jsp:include page="header.jsp" /> --%>

<form method="post" action="${pageContext.request.contextPath}/tour/deleteMyroute.do" id="deleteForm">
<input type="hidden" name="num" id="num">
<input type="hidden" name="course" id="course">
</form>






<style>
.modal-content {
	color: black !important;
}

#sub {
	position: relative;
	width: 100%;
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
	/*    padding-bottom: 9px; */
	/*  margin: 40px 0 px; */
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

.btn.plane{
	background-color:white;
}
</style>
  
</head>
<body>
	<div class="main">
		<!-- ADMIN -->
		<div id="side"><!-- /#side --></div>
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
		     <div class="sub-menu col-md-2">
		    		 <ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=1">COURSE1</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=2">COURSE2</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=3">COURSE3</a></li>							
				 </ul>
			</div><!--/.col-md-2 -->






<!-- 읽기모드  -->
<div class="col-md-10">

	<div class="row">
		<div class="col-xs-9">
			<div>
				<form role="form" class="form-inline">
					<div class="form-group">
						<label for="name">코스이름</label>
						<c:out value="${myroute.getCoursename()}" escapeXml="false"/>
					</div>
				</form>
			</div>	
		</div>
		<div class="col-xs-3">	
			<button type="button" class="btn btn-default" id="edit">수정</button>	
			<button type="button" class="btn btn-danger" id="delete">삭제</button>
		</div>
	</div>
	

	<div>
		<form role="form" class="form-inline">
			<div class="form-group">
				<label for="name">국가</label> 
				<c:out value="${myroute.nationname}" escapeXml="false"/>
			</div>
		</form>
	</div>

	<div>
		<form role="form" class="form-inline">
			<div class="form-group">
				<label for="name">도시</label>
				<c:out value="${myroute.cityname}" escapeXml="false"/> 
				

			</div>
		</form>
	</div>
	
	<!-- row1 -->

	<div>
		<form role="form" class="form-inline">
			<div class="form-group">
				<label for="name">여행시작일</label>
				<c:out value="${myroute.startdate}" escapeXml="false"/> 
				</div>
			&nbsp;&nbsp;
			<div class="form-group">
				<label for="name">여행마지막일</label> 
				<c:out value="${myroute.enddate}" escapeXml="false"/> 
			</div>
			&nbsp;&nbsp;
			(<c:out value="${days}" escapeXml="false"/>일 코스)
		</form>
	</div>

<c:forEach begin="0" end="${days-1}" varStatus="status">	


	<div class="day-group">
		<div class="day-item" id="day1">
			<h3 class="day-count">${status.count}일차</h3>
			<div class="icon-group">
				<div class="tour-icon-list">
					<%-- 	<img src = "${pageContext.request.contextPath}/view/icon/bar.png"/>  --%>
					<!-- 	<div>관광지</div> -->
					<div class="add-icon">
						
						<img src="${pageContext.request.contextPath}/view/icon/outline3.png">
						
						<%--  <img src="${pageContext.request.contextPath}/view/icon/airport.png" width="155px" height="155px" /> --%>
						<img class="arrowb"
							src="${pageContext.request.contextPath}/view/icon/arrow.png" />
					</div>
					<!-- add-icon -->
						<c:forTokens items="${iconlist[status.index]}" delims="," var="icon" varStatus="st">
						<c:forTokens items="${namelist[status.index]}" delims="," var="tourname"  begin="${st.index}" end="${st.index}">
						 
						<div class="tour-icon icon4" id="icon0">
							
							<img src="${pageContext.request.contextPath}/view/icon/${icon}.png" class="removeicon" width="180px" height="180px">
							 
							<div class="removename">
							
								<b><c:out value="${tourname}" escapeXml="false"/> </b>
							
							</div>	
						</div>
						<img class="arrow" src="${pageContext.request.contextPath}/view/icon/arrow.png" width="20px" height="20px"/>
					</c:forTokens>
					</c:forTokens>
					
				</div>
				<!-- tour-icon-list -->
			</div>
			<!--icon-group -->
		</div>
		<!--day-item -->
	</div>
	<!--day-group -->	
</c:forEach>	

</div>
	
	
	
	</div><!-- /.sub -->


</div><!-- /.main -->

<script
	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/owl.carousel.min.js"></script>
<script
	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/contact.js"></script>
<script
	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/jquery.flexslider.js"></script>
<script
	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/script.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap-datepicker.kr.js"></script>
<script 
	src="${pageContext.request.contextPath}/js/myroute.js"></script>

	
	
	
<div class="modal modal-center fade" id="my80sizeCenterModal"
	tabindex="-1" role="dialog" aria-labelledby="my80sizeCenterModalLabel">
	<div class="modal-dialog modal-80size modal-center" role="document">
		<div class="modal-content modal-80size">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">관광지 정보</h4>
			</div>
			<div class="modal-body" style="text-align: center;">
				<div id="modal-val"></div>

			</div>
			<!-- modal-body -->
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal"
					id="modaladd">추가</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
		<!-- modal-content -->
	</div>
</div>
</div>

<script>
	$(function(){
		$("#edit").click(function(){
			var course = "<%=course%>";
			$(location).attr('href',"<%=request.getContextPath()%>/tour/myrouteFormWrite.do?course="+course+"&edit=edit");
			

			
		});
		
		$("#delete").click(function(){
			
			
			var num = "<%=num%>";
			var course="<%=course%>";
			$('#num').val(num);
			$('#course').val(course);
			$('#deleteForm').submit();
			

			
		});	
		
	});
	
	 $(function() {
			if ('<%=session.getAttribute("adminId")%>' != 'null') {
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
			}// if 
		}); 
	
</script>

</body>
</html>

