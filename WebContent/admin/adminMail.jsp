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
	text-align:center;
}
.btnlist input{
	font-size:8pt;
	font-weight:bold;
	margin:5px;
}
/* ----------table------------ */
.form-three{
	color:black;
}
.help-block{/* 에러메시지 */
	color:#5eddc1 !important;
}
.form-group{
	height:70px;  
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
							<h2>[ 메일 전송 ]</h2>
						</div>
						<!-- 입력 -->
						<div class="panel panel-widget forms-panel">
							<div class="progressbar-heading general-heading">
								<h4><b><img src="../custom/image/bar.png" width="7"/>&nbsp; 전체 메일 발송 </b></h4>
							</div>
							<div class="forms">
								<h3 class="title1"></h3>
								<div class="form-three widget-shadow">
									<form name="mailForm" class="form-horizontal" action="adminMailSending.do" method="post">
										<div class="form-group">
											<label for="focusedinput" class="col-sm-2 control-label">제목</label>
											<div class="col-sm-5">
												<input type="text" class="form-control1" name="title" id="title">
												<p class="help-block" id="error-title">제목을 입력하세요.</p>
											</div>
										</div>
										<div class="form-group">
											<label for="txtarea1" class="col-sm-2 control-label">내용</label>
											<div class="col-sm-8">
												<textarea name="content" id="content" cols="50" rows="4" class="form-control1" style="height:auto"></textarea>
												<p class="help-block" id="error-content">내용을 입력하세요.</p>												
											</div>
										</div> 
										<P style="height:20px;">
										<div class="btnlist">
									  		<input type="reset" class="btn col-5" onclick="hideErrorMasseage()" value="초기화">
									  		<input type="button" class="btn col-11" onclick="insertCheck()" value="전송">
				  						</div><!-- /.btnlist -->
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
	
	<%-- <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>	
	<script src="${pageContext.request.contextPath}/adminpage_template/js/jquery2.0.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/modernizr.js"></script>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/jquery.cookie.js"></script>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/screenfull.js"></script> --%>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/adminpage_template/js/proton.js"></script>
<script type="text/javascript">
$(function() {
	if ('<%=session.getAttribute("adminId")%>' == 'null') {
		location.href="../errorpage.html";
	}// if 관리자가 아니라면

	$('.topJSP nav.navbar.navbar-default').css({'left':'60px','z-index':'5'});
	$('.main #side').css('z-index','10');
	$('.main #sub').addClass('admin-main');
	hideErrorMasseage();
});

function hideErrorMasseage(){
	$('#error-title').hide();
	$('#error-content').hide();
}

function insertCheck(){
	var isCheck=true;	
	if(document.mailForm.title.value==''){
		$('#error-title').show();
		isCheck=false;
	} else $('#error-title').hide();
	
	if(document.mailForm.content.value==""){
		$('#error-content').show();
		isCheck=false;
	} else $('#error-content').hide();
	
	if(isCheck)	{
		if(confirm("정말로 전송하시겠습니까?")){
			document.tourForm.submit();
		}
	}
}
</script>
</body>
</html>
