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
	height:139px;	
}
#sub{
	display : inline-block;
	position : relative;
	width:100%;
	height:100%;
	background-color:#eee;
	text-align:center;
}
#sub-menu{
	display : inline-block;
	position : relative;
	width:500px;
}
#bottom{
	display : inline-block;
	position : relative;
	width:500px;
}
.footer{
	position:relative;
}
/* --------상단 list와 button------------ */
.form-inline{
	color:black !important;
	text-align:left;
}
#list{  
	float: left;
}
#btnlist .row{ 
	float:right;
	margin:0px;
}
#btnrow div .btn{ 
	font-size:8pt;
	font-weight:bold;
	background-color:#fdba4b; 
}
#delete{
	background-color:#47d178 !important;
}
/* ----------table------------ */
#tableAlign{
	text-align:center !important;
	margin-top:10px;
}
#table{
	margin:0 auto;
	width:500px
}
.table-heading{
	color:black !important;
}
#table thead tr th {
	background: #5fd7ba;
}
.hoverTR{
	background-color: #cefff3 !important;
}
/* --------하단 paging------------ */
.paging{
	margin-top: 10px;
}
/* --------하단 button------------ */
div.buttomConfirm{  
	margin-top:30px;
}
#confirm{
	font-size:8pt;
	font-weight:bold; 
}
/* ------------------------admin으로 로그인해서 side.jsp 추가되었을 때 ------------------------ */
.admin-main {
	padding-left:90px;
	padding-right:60px;
}

</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/adminpage_template/css/table-style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/adminpage_template/css/basictable.css" />

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
					<div class="table-heading"><h2>[ 국가 정보 ]</h2></div>
					<div id="sub-menu">
					<div class="row"><!-- 버튼들을 정렬하기 위한 row -->
						<div class="col-sm-5 col-sm-offset-7 col-md-5 col-md-offset-7">  
				  			<div class="row" id="btnrow"><!-- : 추가,삭제,수정버튼 정렬을 위한 row -->
						  		<div class="col-sm-4 col-sm-offset-4"><button type="button" class="btn btn-block" onclick="location.href='adminTourNationInsert.do'">추가</button></div>
						  		<div class="col-sm-4"><button type="button" class="btn btn-block" onclick="deleteCheck()" id="delete">삭제</button></div>
				  			</div><!-- /.row -->
				  		</div><!-- /.col-sm-3 col-sm-offset-6 -->
				  	</div><!-- /.row --> 
					</div>
					<div class="agile-tables" id="tableAlign">
						<div class="w3l-table-info">
						    <table id="table">
								<thead>
								  <tr height="42">
								  	<th width="75"></th>
									<th>국가코드</th>
									<th>국가명</th>
								  </tr>
								</thead>
								<tbody>
								  <c:forEach var="nation" items="${nationList}">
								 <tr height="42">
								  	<td width="75"></td>
									<td>${nation.code_nation }</td>
									<td>${nation.name }</td>
								  </tr>
								  </c:forEach>
								</tbody>
						    </table>
						     <!-- 페이징 처리를 위한 구역 -->
							<c:if test="${count!=0 }">
								<div class="row paging">
									<c:if test ="${pageUtil.startPage>pageUtil.blockUnit }">
										<a href="../admin/adminTourNation.do?currentPage=${pageUtil.startPage-1 }">[이전]</a>
									</c:if>
									<c:forEach var="num" begin="${pageUtil.startPage }" end="${pageUtil.endPage }">
										<c:if test="${pageUtil.currentPage ==num}">
											<font color="#5fd7ba"><b>${num }</b></font>
										</c:if>
										<c:if test="${pageUtil.currentPage !=num}">
											<a href="../admin/adminTourNation.do?currentPage=${num }">${num }</a>
										</c:if>
									</c:forEach>
									<c:if test ="${pageUtil.endPage<pageUtil.totalPage }">
										<a href="../admin/adminTourNation.do?currentPage=${pageUtil.endPage+1 }">[다음]</a>
									</c:if>
								</div>
							</c:if>
						</div><!-- /.w3l-table-info -->
					</div><!-- /.agile-tables -->
					<!-- 삭제를 눌렀을 때 확인이라는 버튼이 뜰 구역 -->
					<div id="bottom">
					    <div class="row">
					    	<div class="buttomConfirm col-sm-2 col-md-2 col-sm-offset-5"></div>
					    </div>
					</div>
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
<script type="text/javascript">
$(function() {
	if ('<%=session.getAttribute("adminId")%>' == 'null') {
		location.href="../errorpage.html";
	}// if 관리자가 아니라면

	$('.topJSP nav.navbar.navbar-default').css({'left':'60px','z-index':'5'});
	$('.main #side').css('z-index','10');
	$('.main #sub').css({'padding-left':'110px','padding-right':'60px'});
	goDetail();
});

function goDetail(){
	$('#table tbody tr').bind({
		mouseover:function(){
			$(this).find('td').addClass('hoverTR');
			$(this).css('cursor','pointer');
		},
		mouseout:function(){
			$(this).find('td').removeClass('hoverTR');
		},
		click:function(){
			location.href="adminTourNationDetail.do?code_nation="+$(this).find('td:eq(1)').html();
		}
	})
} 
var isDeleteCheck=false;

/* 체크박스 생성, 삭제 */
function deleteCheck(){
	isDeleteCheck=!isDeleteCheck;
	if(isDeleteCheck) {
		//삭제 -> 취소으로 바꾸고, 체크박스 생성
		$('#delete').html('취소');
		$('#table thead tr th:eq(0)').html('<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAll();"/>');
		for(i=0;i<'${count}';i++) $('#table tbody tr:eq('+i+') td:eq(0)').html('<input type="checkbox" name="checkRow" id="checkRow" value="'+i+'"/>');
		$('div.buttomConfirm').html('<button type="button" class="btn btn-sm btn-block col-1" id="confirm" onclick="deleteAction()">확인</button>');
		$('#table tbody tr').unbind();
	}
	else {
		//취소-> 삭제로 바꾸고, 체크박스 삭제
		$('#delete').html('삭제');
		$('#table thead tr th:eq(0)').html('');
		for(i=0;i<'${count}';i++) $('#table tbody tr:eq('+i+') td:eq(0)').html('');
		$('#confirm').remove();
		goDetail();
	}
}

/* 체크박스 전체선택, 전체해제 */
function checkAll(){
      if( $("#checkAll").is(':checked') ){
        $("input[name=checkRow]").prop("checked", true);
      }else{
        $("input[name=checkRow]").prop("checked", false);
      }
}

/* 삭제(체크박스된 것 전부) */
function deleteAction(){  
	var checkRow = "";
	$( "input[name='checkRow']:checked" ).each (function (){
		checkRow = checkRow + $('#table tbody tr:eq('+$(this).val()+') td:eq(1)').text()+"," ;
	}); 
	checkRow = checkRow.substring(0,checkRow.lastIndexOf(",")); //맨끝 콤마 지우기
	
	if(checkRow == ''){
	  alert("삭제할 대상을 선택하세요.");
	  return false;
	}
	console.log("### checkRow => {}"+checkRow);
	
	if(confirm("관련 여행정보와 도시정보가 전부 삭제됩니다.\n정보를 삭제 하시겠습니까?("+checkRow+")")){
	    location.href="adminTourNationDeletePro.do?code_nationArr="+checkRow;      
	}
}
</script>
</body>
</html>
