<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,com.wellbroad.db.beans.myroute.*,java.util.*" %>
       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="Colored Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no"/>

<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="${pageContext.request.contextPath}/adminpage_template/css/style.css?ver=1" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!-- font-awesome icons -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/adminpage_template/css/font.css" type="text/css"/>
<link href="${pageContext.request.contextPath}/adminpage_template/css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<style>
nav.navbar.navbar-default {
	background-color: white !important;
}

.header {
	margin-left: 80px;
}
</style>
<title>WELLBROAD</title>
</head>
<body>
     <nav class="main-menu">
      <ul>
         <!-- MAIN -->
         <li>
            <a href="../main/mainpage.do?id='admin'">
               <i class="fa fa-home nav_icon"></i>
               <span class="nav-text">
               ADMIN
               </span>
            </a>
         </li>
         
         <!-- 회원목록 -->
         <li class="memberList">
               <a href="adminMemberList.do">
                  <i class="fa fa-list-ul" aria-hidden="true"></i>
                  <span class="nav-text">회원목록</span>
               </a>
         </li>
         <!-- 메일 전송 -->
         <li class="has-subnav">
     		<a href="adminMail.jsp">
        		<i class="icon-font nav-icon" aria-hidden="true"></i>
            	<span class="nav-text">메일 전송</span>
       		</a>
		 </li>
         <!-- 공지게시판 -->
         <li class="has-subnav">
     		<a href="noticeWriteForm.do">
        		<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
            	<span class="nav-text">공지작성</span>
       		</a>
		 </li>
         <!-- 여행정보 -->
         <li class="has-subnav">
            <a href="javascript:;">
               <i class="icon-table nav-icon" aria-hidden="true"></i>
               <span class="nav-text">여행정보</span>
               <i class="icon-angle-right"></i><i class="icon-angle-down"></i>
            </a>
            <ul>
               <li><a class="subnav-text" href="../admin/adminTourNation.do">국가</a></li>
               <li><a class="subnav-text" href="../admin/adminTourCity.do">도시</a></li>
               <li><a class="subnav-text" href="../admin/adminTour.do?tour_type=tour_spot">관광</a></li>
               <li><a class="subnav-text" href="../admin/adminTour.do?tour_type=tour_stay">숙소</a></li>
               <li><a class="subnav-text" href="../admin/adminTour.do?tour_type=tour_food">음식</a></li>
            </ul>
         </li>
      </ul>
   </nav>
   
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script> -->
<%-- <script src="${pageContext.request.contextPath}/adminpage_template/js/jquery2.0.3.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/adminpage_template/js/modernizr.js"></script>
<script src="${pageContext.request.contextPath}/adminpage_template/js/jquery.cookie.js"></script>
<%-- <script src="${pageContext.request.contextPath}/adminpage_template/js/screenfull.js"></script> --%>
   
<script>
$(function() {
	if ('<%=session.getAttribute("adminId")%>' == 'null') {
		location.href="../errorpage.html";
	}// if 관리자가 아니라면
});

</script>
   
</body>
</html>