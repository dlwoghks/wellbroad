<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no" />
<title>WELLBROAD</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myroute.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/section.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="../custom/css/tourCustom.css">
<style>
/* ---------------google maps----------------- */
.sub-container2{
	margin-left: 130px;
	padding: 30px 90px;
	height: 400px;
	width: 80%;
} 
#map {
	display: inline-block;
	height:100%;
	width:100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
							<script src="assets/js/html5shiv.js"></script>
							<script src="assets/js/respond.js"></script>
						<![endif]-->

<!--[if IE 8]>
					    	<script src="assets/js/selectivizr.js"></script>
					    <![endif]-->
</head>
<body>
	<div class="main">
		<!-- ADMIN -->
		<div id="side"></div><!-- /#side -->

		<!-- Home -->
		<div id="header">
			<jsp:include page="../main/top.jsp" />
			<link rel="stylesheet" href="../custom/css/tourCustom.css">
		</div><!--/#header -->
		<section class="section-background">
			<div class="container">
				<h2 class="page-header0"><b>TOUR</b></h2>
				<p class="page-header1">다양한 여행정보를 확인하세요!</p>
				<ol class="breadcrumb">
					<li><a href="../main/mainpage.do">HOME</a></li>
					<li class="active1">&nbsp;TOUR</li>
				</ol>
			</div><!-- /.container -->
		</section><!-- /.section-background -->
		
		<!-- 본격적으로 custom한 페이지구현 -->
		<div class="sub" id="sub">
			<div class="sub-menu">
				<!-- 왼쪽 메뉴 -->
				<ul class="nav navbar-nav">
					<li><a href="tourCity.do">CITY</a></li>
					<li><a href="tour.do?tour_type=tour_spot">SPOT</a></li>
					<li><a href="tour.do?tour_type=tour_stay">STAY</a></li>
					<li><a href="tour.do?tour_type=tour_food">FOOD</a></li>
				</ul><!-- /.nav -->
			</div><!-- /.sub-menu -->
		</div><!-- /#sub /.sub -->
	</div><!-- /.main -->

	<div class="sub-main"><!-- 오른쪽 내용(왼쪽에서 클릭한 메뉴에 따라 보여지는 내용이 다름) -->
		<div class="sub-container1"><!-- 국가/도시를 select할 영역 -->
			<h4>
				<img src="../custom/image/bar.png" width="7"/>
				<b>&nbsp; 내 위치</b>
			</h4>
		</div><!-- /.sub-container1 -->

		<div class="sub-container2">
			<div id="map"></div>
		</div><!-- /.sub-container2 -->
	</div><!-- /.sub-main -->

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
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
		$('.main #header').addClass('admin-main');
		$('nav.navbar.navbar-default').css({'left':'60px','z-index':'5'});
		$('#side').css('z-index','10');
		$('.main .sub .sub-menu').css('margin-left','50px');
	} else {
		$('.main #header').removeClass('admin-main');
		$('nav.navbar.navbar-default').css('left','0px');
		$('.sub-menu').css('margin-left','0px');
	}// if 
});
/*-------------- google maps -------------- */
function initMap(){
       var map = new google.maps.Map(document.getElementById('map'), {
         center: {lat: 37.572933, lng: 126.989515},
         zoom: 15
       });
       var infoWindow = new google.maps.InfoWindow({map: map});

       // Try HTML5 geolocation.
       if (navigator.geolocation) {
         navigator.geolocation.getCurrentPosition(function(position) {
           var pos = {
             lat: position.coords.latitude,
             lng: position.coords.longitude
           };

           infoWindow.setPosition(pos);
           infoWindow.setContent('현재 위치');
           map.setCenter(pos);
         }, function() {
           handleLocationError(true, infoWindow, map.getCenter());
         });
       } else {
         // Browser doesn't support Geolocation
         handleLocationError(false, infoWindow, map.getCenter());
       }
     }

     function handleLocationError(browserHasGeolocation, infoWindow, pos) {
       infoWindow.setPosition(pos);
       infoWindow.setContent(browserHasGeolocation ?
                             'Error: The Geolocation service failed.' :
                             'Error: Your browser doesn\'t support geolocation.');
     }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDellrqYwBw0FOfUZYn3fNpB-ApSbN0fPo&callback=initMap"
	async defer></script>
</body>
</html>