<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>WELLBROAD</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Colored Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />

<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/adminpage_template/css/bootstrap.css">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/adminpage_template/css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!-- font-awesome icons -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/adminpage_template/css/font.css" type="text/css"/>
<link href="${pageContext.request.contextPath}/adminpage_template/css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<style>
/* ------------------------ main ------------------------ */
.main{ 
	display : inline-block;
	width :100%;
	height:100%;  
	overflow: scroll;
}
#header{
	display : inline-block;
	position : relative;
	width:100%;
	height:148px;	
}

#sub{
	display : inline-block;
	position : relative;
	width:100%;
	height:100%;
	background-color:#eee;
}
.footer{
	position:relative;
}

/* ----------button------------ */
.form-inline{
	color:black !important;
}
.btnlist { /* 초기화,추가,취소버튼 정렬을 위한 btnlist */
	margin-top:20px;
	margin-bottom:10px;
	height:40px;
}
.btnlist button{
	font-size:8pt;
	font-weight:bold;
}
#back{
	display:inline-block;
	float:left;
	margin:10px;
	text-align:center;
}
#update{
	display:inline-block;
	float:right;
	margin:10px;
	text-align:center;
}
/* ----------table------------ */
.form-three{
	color:black;
}
/* ------------------------admin으로 로그인해서 side.jsp 추가되었을 때 ------------------------ */
.admin-main {
	padding-left:90px;
	padding-right:60px;
}
</style>
</head>
<body class="dashboard-page">
	<div class="main">
		<!-- ADMIN -->
		<div id="side"><jsp:include page="../admin/side.jsp"></jsp:include></div><!-- /#side -->
		
		<!-- Home -->
		<div id="header">
			<div class="top"><jsp:include page="../main/top.jsp"></jsp:include></div>
		</div><!-- /#header -->
		
		<!-- 본격적으로 custom한 페이지구현 -->
		<div class="sub" id="sub">
			<div class="main-grid">
				<div class="agile-grids">
					<!-- input-forms -->
					<div class="grids">
						<div class="progressbar-heading grids-heading">
							<h2>[ 도시 정보 ]</h2>
						</div>
						<div class="btnlist">
					  		<button type="button" class="btn col-5" id="back" onclick="location.href='adminTourCity.do'">뒤로가기</button>
					  		<button type="button" class="btn col-11" id="update" onclick="location.href='adminTourCityUpdate.do?code_city=${city.code_city }'">수정</button>
  						</div><!-- /.btnlist -->
						<!-- 입력 -->
						<div class="panel panel-widget forms-panel">
							<div class="progressbar-heading general-heading">
								<h4><b><img src="../custom/image/bar.png" width="7"/>&nbsp; 도시 상세 </b></h4>
							</div>
							<div class="forms">
								<h3 class="title1"></h3>
								<div class="form-three widget-shadow">
									<form name="tourForm" class="form-horizontal">
										<div class="form-group">
											<div class="col-sm-2" align="right"><b>국가</b></div>
											<div class="col-sm-5">${nation.name }</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2" align="right"><b>도시코드</b></div>
											<div class="col-sm-5">${city.code_city }</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2" align="right"><b>도시</b></div>
											<div class="col-sm-5">${city.name }</div>
										</div> 
										<div class="form-group">
											<div class="col-sm-2" align="right"><b>설명</b></div>
											<div class="col-sm-8">${city.description }</div>
										</div>
									</form><!-- /.form-horizontal -->
								</div><!-- /.form-three widget-shadow -->
							</div><!-- /.forms -->
						</div><!-- /.panel panel-widget forms-panel :입력 -->
					</div><!-- /.grids : input-forms -->
				</div><!-- /.agile-grids -->
			</div><!-- /.main-grid -->

		</div><!-- /#sub -->
		<div class="footer">
			<jsp:include page="../admin/footer.jsp"></jsp:include>
		</div>
	</div><!-- /.main -->
	
	<script src="${pageContext.request.contextPath}/adminpage_template/js/jquery2.0.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/modernizr.js"></script>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/jquery.cookie.js"></script>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/screenfull.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/proton.js"></script>
	<script src="${pageContext.request.contextPath}/custom/js/adminTourCustom.js"></script>
<script type="text/javascript">
$(function() {
	if ('<%=session.getAttribute("adminId")%>' == 'null') {
		location.href="../errorpage.html";
	}// if 관리자가 아니라면

	$('.topJSP nav.navbar.navbar-default').css({'left':'60px','z-index':'5'});
	$('.main #side').css('z-index','10');
	$('.main #sub').addClass('admin-main');
});
</script>
</body>
</html>
