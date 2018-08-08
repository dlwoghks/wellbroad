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
<link rel="stylesheet" href="../custom/css/tourCustom.css">
<style>
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
	<c:set var="tour_name" value="식당" />
	<c:if test="${tour_type=='tour_spot'}">
		<c:set var="tour_name" value="관광" />
	</c:if>
	<c:if test="${tour_type=='tour_stay'}">
		<c:set var="tour_name" value="숙소" />
	</c:if>

	<div class="sub-main">
		<!-- 오른쪽 내용(왼쪽에서 클릭한 메뉴에 따라 보여지는 내용이 다름) -->
		<div class="sub-container1">
			<!-- 국가/도시를 select할 영역 -->
			<div style="color: #00CED1">
				<h2>${tour_name} 관련 정보</h2>
			</div>
			<div class="row">
				<!-- grid를 사용하기 위함 -->
				<div class="col-lg-2 col-md-4 col-sm-4 col-xs-12">
					<form class="form-inline">
						<!-- label과 select를 묶기 위한 form태그 -->
						<label for="nation">국가 </label>
						<!-- Action에서 얻어온 nationList를  select의 option으로 추가시키고,
								 이미 nation을 선택한 상황이라면 Action에서 얻어온 code_nation과 일치하는지 확인 후 selected 표시
								 그리고 onchange를 이용하여 페이지 이동 및 Action으로 값(현재 클릭,select한 code_nation)을 넘겨줌 -->
						<select class="form-control" id="nation" onchange="location.href='tour.do?tour_type=${tour_type }&code_nation='+this.value">
							<option value="null">선택</option>
							<c:forEach var="nation" items="${nationList}">
								<c:if test="${code_nation== nation.code_nation}">
									<option value="${nation.code_nation}" selected>
										${nation.name}</option>
								</c:if>
								<c:if test="${code_nation!= nation.code_nation}">
									<option value="${nation.code_nation}">${nation.name}</option>
								</c:if>
							</c:forEach>
						</select><!-- /#nation -->
					</form><!-- /.form-inline -->
				</div><!-- /col-lg-3 col-md-4 col-sm-4 col-xs-12-->

				<div class="col-lg-2 col-md-4 col-sm-4 col-xs-12">
					<form class="form-inline">
						<!-- label과 select를 묶기 위한 form태그 -->
						<label for="city">도시 </label>
						<!-- Action에서 얻어온 cityList를  select의 option으로 추가시키고,
								 이미 City를 선택한 상황이라면 Action에서 얻어온 code_city와 일치하는지 확인 후 selected 표시
								 그리고 onchange를 이용하여 페이지 이동 및 Action으로 값(현재 클릭,select한 code_nation,code_city)을 넘겨줌 -->
						<select class="form-control" id="city" onchange="location.href='tour.do?tour_type=${tour_type }&code_nation=${code_nation }&code_city='+this.value">
							<option value="null">선택</option>
							<c:forEach var="city" items="${cityList}">
								<c:if test="${code_city== city.code_city}">
									<option value="${city.code_city }" selected>
										${city.name}</option>
								</c:if>
								<c:if test="${code_city!= city.code_city}">
									<option value="${city.code_city }">${city.name}</option>
								</c:if>
							</c:forEach>
						</select><!-- /#city -->
					</form><!-- /.form-inline -->
				</div><!-- /col-lg-3 col-md-4 col-sm-4 col-xs-12-->

				<div class="col-lg-2 col-md-4 col-sm-4 col-xs-12">
					<form class="form-inline">
						<!-- label과 select를 묶기 위한 form태그 -->
						<label for="tour">${tour_name} </label>
						<select class="form-control" id="tour" onchange="location.href='tour.do?tour_type=${tour_type }&code_nation=${code_nation }&code_city=${code_city }&code='+this.value">
							<option value="null">선택</option>
							<c:forEach var="tour" items="${tourList}">
								<c:if test="${code== tour.code}">
									<option value="${tour.code }" selected>${tour.name}</option>
								</c:if>
								<c:if test="${code!= tour.code}">
									<option value="${tour.code  }">${tour.name}</option>
								</c:if>
							</c:forEach>
						</select><!-- /#city -->
					</form><!-- /.form-inline -->
				</div><!-- /col-lg-3 col-md-4 col-sm-4 col-xs-12-->
			</div><!-- /.row -->
		</div><!-- /.sub-container1 -->

		<c:if test="${tour.name!=null}">
			<div class="sub-container">
			<!-- 관광을 선택하면 추가되는 것 정보(이름,이미지,주소,설명) -->
				<h4 class="tour_title">
					<img src="../custom/image/bar.png" width="7"/>
					<span class="tour_name">&nbsp; ${tour.name }</span>
					<button type="button" class="btn plane" data-toggle="modal" data-target="#my80sizeCenterModal">
						<img src="../custom/image/location.png"/>
					</button>
				</h4>
				<div class="tour_image" align="center">
					<img src="../custom/image/tour/${tour.code}_0.jpg" width="1000">
				</div>
				<div class="tour_info">
					<div class="tour_address">${tour.address }</div>
					<div class="tour_description">${tour.description }</div>
				</div>
			</div><!-- /.sub-container2 -->
		</c:if>
	</div><!-- /.sub-main -->
<div class="modal modal-center fade" id="my80sizeCenterModal" tabindex="-1" role="dialog" aria-labelledby="my80sizeCenterModalLabel">
	<div class="modal-dialog modal-80size modal-center" role="document">
		<div class="modal-content modal-80size">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">위치 정보</h4>
			</div>
			<div class="modal-body" style="text-align: center;">
				<div id="map-canvas" style="width: 100%; height: 500px"></div>
			</div>
			<!-- modal-body -->
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div><!-- modal-content -->
		</div>
	</div>
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
	 if ('<%=session.getAttribute("adminId")%>' != 'null') {
		$.ajax({
			url : "../admin/side.jsp",
			success : function(result) {
				$('.main #side').html(result).scrollTop(0);
			}
		});
		$('.main #header').addClass('admin-main');
		$('nav.navbar.navbar-default').css({'left':'60px','z-index':'5'});
		$('#side').css('z-index', '10');
		$('.main .sub .sub-menu').css('margin-left','50px');
	} else {
		$('.main #header').removeClass('admin-main');
		$('nav.navbar.navbar-default').css('left', '0px');
		$('.sub-menu').css('margin-left','0px');
	}// if 
});
		
/* ------------------google mpas-------------------------- */
function initMap() {
	var selectcity = $('#tour option:selected').val();
	
 	var mapOptions = {
 		zoom : 16, // 지도를 띄웠을 때의 줌 크기
 		mapTypeId : google.maps.MapTypeId.ROADMAP
 	};
 	var map = new google.maps.Map(
 			document.getElementById("map-canvas"), // div의 id과 값이 같아야 함. "map-canvas"
 			mapOptions);
 	var size_x = 40; // 마커로 사용할 이미지의 가로 크기
 	var size_y = 40; // 마커로 사용할 이미지의 세로 크기

 	// 마커로 사용할 이미지 주소
 	var image = new google.maps.MarkerImage('주소 여기에 기입!',
 			new google.maps.Size(size_x, size_y), '', '',
 			new google.maps.Size(size_x, size_y));

 	// Geocoding *****************************************************
 	var address = 'Araya Beach Hotel Patong 162-162/1-2 Taweewong Rd Patong Sub-District Kathu Phuket 빠통 태국'; //선택하지 않았을 시에 보여질 화면
 	if(selectcity != 'null') {
 		address = $('.tour_address').text(); //관광지를 선택했다면 해당 관광지 address 검색
	 	var marker = null;
	 	var geocoder = new google.maps.Geocoder(); //위도, 경도 가져오기
	 	geocoder.geocode({
	 						'address' : address
	 					},
	 					function(results, status) {
	 						if (status == google.maps.GeocoderStatus.OK) {
	 							map.setCenter(results[0].geometry.location);
	 							marker = new google.maps.Marker({
	 								map : map,
	 								icon : image, // 마커로 사용할 이미지(변수)
	 								title :  $('.tour_name').text(), // 마커에 마우스 포인트를 갖다댔을 때 뜨는 타이틀
	 								position : results[0].geometry.location
	 							});
	 							var content = $('.tour_name').text()+"<br>"+address; // 말풍선 안에 들어갈 내용
	 							// 마커를 클릭했을 때의 이벤트. 말풍선 
	 							var infowindow = new google.maps.InfoWindow({
	 										content : content
	 									});
	 							google.maps.event.addListener(marker,"click", function() {
	 										infowindow.open(map, marker);
	 									});
	 						} else {
	 							alert("Geocode was not successful for the following reason: " + status);
	 						}
		});
 	}
 }
//google.maps.event.addDomListener(window, 'load', initMap); 
	</script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDellrqYwBw0FOfUZYn3fNpB-ApSbN0fPo&callback=initMap"
	async defer></script>
</body>
</html>