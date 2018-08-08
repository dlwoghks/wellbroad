<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"
	import="java.io.*,com.wellbroad.db.beans.myroute.*,java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="Colored Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<meta name="viewport" content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no" />

<script type="application/x-javascript">
	 addEventListener("load", function() { 
		 setTimeout(hideURLbar, 0);
		 },false
	 );
	 function hideURLbar(){ 
		 window.scrollTo(0,1); 
	 } 
</script>

<!-- bootstrap-css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/adminpage_template/css/bootstrap.css">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/adminpage_template/css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!-- font-awesome icons -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/adminpage_template/css/font.css" type="text/css" />
<link href="${pageContext.request.contextPath}/adminpage_template/css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<style>
/* ------------------------ main ------------------------ */
.main{
	display : inline-block;
	width :100%;
	height:100%;  
}

#top{
  	position: relative;
	width:100%;
	height:139px;	
}

.flexslider{
  	position: relative;
	width:100%;
	height:100%; 
}
/* ------------------------ side.jsp 추가되었을 때 ------------------------ */
.admin-main {
	margin-left: 60px;
}

/*  .flexslider{
	background-image: url(${pageContext.request.contextPath}/custom/image/main_slide_image1.jpg)
   }
 */
</style>
<title>WELLBROAD</title>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/adminpage_template/css/bootstrap.css">
<script type="text/javascript"	src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div class="main">
		<div id="side"></div>
		<!-- Home -->
		<div id="header">
			<div id="top"><jsp:include page="../main/top.jsp"></jsp:include></div>
			<div class="flexslider">
				<ul class="slides">
					<li class="slider-item" style="background-image: url('')">
						<div class="intro container">
							<div class="inner-intro">
								<h1 class="header-title">
									<span>traveling</span> always "WELLBROAD"
								</h1>
								<p class="header-sub-title">it leaves you speecless, then
									turns your into a storyteller.</p>
								<button class="btn custom-btn">Wellcome Abroad</button>
							</div>
						</div>
					</li>
					<!-- /.slider-item -->
					<li class="slider-item" style="background-image: url('')">
						<div class="intro">
							<div class="inner-intro">
								<h1 class="header-title">
									to <span>travel</span> is to <span>live</span>
								</h1>
								<p class="header-sub-title">it leaves you speecless, then
									turns your into a storyteller.</p>
								<button class="btn custom-btn">Wellcome Abroad</button>
							</div>
						</div>
					</li><!-- /.slider-item -->
				</ul><!-- /.slides -->
			</div><!-- /.flexslider -->
		</div><!-- /#header -->
	</div><!-- /main -->


	<script	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/jquery-1.11.2.min.js"></script>
	<script	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/bootstrap.min.js"></script>
	<script	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/owl.carousel.min.js"></script>
	<script	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/contact.js"></script>
	<script	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/jquery.flexslider.js"></script>
	<script	src="${pageContext.request.contextPath}/Euro-Travels-master/assets/js/script.js"></script>

<script>
 $(function() {
	if ('<%=session.getAttribute("adminId")%>' != 'null') {
		$.ajax({url:"../admin/side.jsp",
			success:function(result) {
				$('.main #side').html(result).scrollTop(0);
			}
		});
		
 	     $('.flexslider').flexslider({
	         animation: "fade", controlNav: "thumbnails", directionNav: false,
	         start: function(slider){
	           jQuery('.slider-container').removeClass('loading');
	         }
	       }); 
		
		$('.main #header').addClass('admin-main');
		$('nav.navbar.navbar-default').css({'left':'60px','z-index':'5'});
		$('#side').css('z-index','10');
	} else {
		$('.main #header').removeClass('admin-main');
		$('nav.navbar.navbar-default').css('left','0px');
	}// if 
}); 
</script>
</body>
</html>