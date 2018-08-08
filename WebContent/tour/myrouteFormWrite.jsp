<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"   import="java.io.*,java.util.*,
								   com.wellbroad.db.beans.myroute.*,
								   com.wellbroad.db.beans.tour.TourListDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%

int num=0;			 
String course = "";
boolean isEdit = false;
MyRouteDTO myroute = null;
int days = 0;
String userid= "";
String edit = "";

String oldcoursename = "";
String oldstartdate = "";
String oldenddate="";
String oldnationname="";
String oldcityname="";

List<String> list = null;   //국가 
List<String> citylist = null; //도시 
List<TourListDTO> tourlist = null; //관광지 


course = (String) request.getAttribute("course");
isEdit = (boolean) request.getAttribute("isEdit");

if(session.getAttribute("userId") != null){
	userid = (String)session.getAttribute("userId");

	myroute = (MyRouteDTO)request.getAttribute("myroute");
	days = (int)request.getAttribute("days");
	num = (int) request.getAttribute("num");
	
	
	oldcoursename = (String) request.getAttribute("oldcoursename");
	oldstartdate = (String) request.getAttribute("oldstartdate");
	oldenddate = (String) request.getAttribute("oldenddate");
	oldnationname = (String)request.getAttribute("oldnationname");
	oldcityname = (String)request.getAttribute("oldcityname");
	citylist = (List<String>) request.getAttribute("citylist");
	tourlist = (List<TourListDTO>) request.getAttribute("tourlist");
}


list = (List<String>) request.getAttribute("list"); //쓰기,읽기모드 모두 불러옴 

%>




<c:set var="isEdit" value="<%=isEdit%>" /> <!-- 수정모드데이터 -->
<c:set var="myroute" value="<%=myroute %>" /> <!--myrouteDTO -->
<c:set var="days" value="<%=days %>" /> <!-- 며칠 코스 ..-->





	   <meta http-equiv="X-UA-Compatible" content="IE=edge">
	   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	   <script type="text/javascript" 
		src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
	   <script src="http://code.jquery.com/jquery-latest.js"></script>

	   <meta name="viewport" content="width=device-width, initial-scale = 1.0, 
	   									maximum-scale=1.0, user-scalable=no"/>
	<title>MYROUTE</title>
   
 <!--    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/owl.carousel.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/owl.theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/flexslider.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Euro-Travels-master/assets/css/main.css?ver=7"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/section.css?ver=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myroute.css?ver=31">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker3.css?ver=0"> <!-- 날짜 -->
<!-- 	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css" rel="stylesheet" type="text/css" /> -->

<%-- <jsp:include page="header.jsp" /> --%>


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
<%-- 				<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=4">COURSE4</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=5">COURSE5</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=6">COURSE6</a></li>
					<li><a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=7">COURSE7</a></li>	 --%>							
				 </ul>
			</div><!--/.col-md-2 -->
<div class="col-md-10"><!-- if else  쓰기 모드 ,수정모드 -->
	<c:choose> 
		<c:when test="${isEdit == true}">  <!-- 수정모드  -->
			<form name="myroute" id="myroute" method="post" action="updateMyroute.do">
				<label for="name">코스이름</label> 
				<input class="form-control input-sm" type="text" maxlength="10" name="coursename" id="coursename"/> 
				<input class="form-control input-sm" type="hidden" name="num" id="num" />
				<input class="form-control input-sm" type="hidden" name="course" id="course" /> 
				<input class="form-control input-sm" type="hidden" name="startdate" id="startdate" /> 
				<input class="form-control input-sm" type="hidden" name="enddate" id="enddate" /> 
				<input class="form-control input-sm" type="hidden" name="iconname" id="iconname" /> 
				<input class="form-control input-sm" type="hidden" name="icon" id="icon" />
				<input class="form-control input-sm" type="hidden" name="nationname" id="nationname" />
				<input class="form-control input-sm" type="hidden" name="cityname" id="cityname" />
				<input class="form-control input-sm" type="hidden" name="tourname" id="tourname" />
				<input type="submit" style="display: none" />
			</form>

			<div class="row">
				<div class="col-md-3">
					<form role="form" class="form-inline">
						<div class="form-group">
							<label for="name">국가</label>
							<select class="form-control" id="nation">
								<option>선택</option>
								<c:forEach var="name" items="<%=list%>">
									<option>${name}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div><!-- /.col-md-3 -->

				<div class="col-md-3">
					<form role="form" class="form-inline">
						<div class="form-group">
							<label for="name">도시</label>
							<select class="form-control" id="city">
								<option value="0">선택</option>
								<c:forEach var="cityname" items="<%=citylist%>">
									<option>${cityname}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div><!-- /.col-md-3 -->

				<div class="col-md-3">
					<form role="form" class="form-inline">
						<div class="form-group">
							<label for="name">관광지</label>
							<select class="form-control" id="tour">
								<option>선택</option>
								<c:forEach var="tourname" items="<%=tourlist%>" varStatus="status">
									<option value=${status.index}>${tourname.name}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div><!-- /.col-md-3 -->

				<div class="col-md-3">
					<a href="myrouteForm.do"></a>
				</div>
			</div><!-- row1 -->


			<div class="row">
				<div class="col-xs-7" id="datapickerBox">
					<form role="form" class="form-inline">
						<div class="form-group">
							<label for="name">여행시작일</label>
							<input class="form-control input-sm" type="text" id="datepicker1" name="wrtDt" placeholder="클릭!" readonly="readonly">
							&nbsp;<img src="${pageContext.request.contextPath}/view/icon/calendar1.png">
							&nbsp;&nbsp;
							<label for="name">여행마지막일</label> 
							<input class="form-control input-sm" type="text" id="datepicker2" name="wrtDt" placeholder="클릭!" readonly="readonly">
							&nbsp;<img src="${pageContext.request.contextPath}/view/icon/calendar1.png">
						</div>
					</form>
				</div><!-- /.col-xs-7 -->
				<div class="col-xs-1">
					<div class="col-xs-3">
						<button type="button" id="cancel" class="btn btn-default" style="display: none">취소</button>
					</div>
				</div><!-- /.col-xs-1 -->
				<div class="col-xs-4">
					<button type="button" class="btn btn-danger" id="delete">삭제</button>
					&nbsp;<button type="button" class="btn btn-default" id="reset">초기화</button>
					&nbsp;<button type="button" class="btn btn-success" id="save">수정완료</button> <!-- updateMyRouteAction  -->
				</div><!-- /.col-xs-4 -->
			</div><!-- /.row -->
			<div class="day-group"></div> <!-- 수정완료버튼 아래로 (코스 스크립트에서 처리 (1일차 (dayitem) ,,,)  -->

<!-- 수정 모드 끝  -->	
		</c:when>
		
		
		
		<c:otherwise>
			<form name="myroute" id="myroute" method="post" action="insertMyroute.do">
				<label for="name">코스이름</label> 
				<input class="form-control input-sm" type="text" maxlength="10" name="coursename" id="coursename" /> 
				<input class="form-control input-sm" type="hidden" name="userid" id="userid" />
				<input class="form-control input-sm" type="hidden" name="course" id="course" /> 
				<input class="form-control input-sm" type="hidden" name="startdate" id="startdate" /> 
				<input class="form-control input-sm" type="hidden" name="enddate" id="enddate" /> 
				<input class="form-control input-sm" type="hidden" name="iconname" id="iconname" /> 
				<input class="form-control input-sm" type="hidden" name="icon" id="icon" />
				<input class="form-control input-sm" type="hidden" name="nationname" id="nationname" />
				<input class="form-control input-sm" type="hidden" name="cityname" id="cityname" />
				<input class="form-control input-sm" type="hidden" name="tourname" id="tourname" />
				<input type="submit" style="display: none" />
			</form>

			<div class="row">
				<div class="col-md-3">
					<form role="form" class="form-inline">
						<div class="form-group">
							<label for="name">국가</label>
							<select class="form-control" id="nation">
								<option>선택</option>
								<c:forEach var="name" items="<%=list%>">
									<option>${name}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div><!-- /.col-md-3 -->
				<div class="col-md-3">
					<form role="form" class="form-inline">
						<div class="form-group">
							<label for="name">도시</label>
							<select class="form-control" id="city">
								<option value="0">선택</option>
							</select>
						</div>
					</form>
				</div><!-- /.col-md-3 -->
				<div class="col-md-3">
					<form role="form" class="form-inline">
						<div class="form-group">
							<label for="name">관광지</label>
							<select class="form-control" id="tour">
								<option>선택</option>
							</select>
						</div>
					</form>
				</div><!-- /.col-md-3 -->
			
			</div><!-- row1 -->

			<div class="row">
				<div class="col-xs-7" id="datapickerBox">
					<form role="form" class="form-inline">
						<div class="form-group">
							<label for="name">여행시작일</label>
							<input class="form-control input-sm" type="text" id="datepicker1" name="wrtDt" placeholder="클릭!" readonly="readonly">
							&nbsp;<img src="${pageContext.request.contextPath}/view/icon/calendar1.png">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="name">여행마지막일</label>
							<input class="form-control input-sm" type="text" id="datepicker2" name="wrtDt" placeholder="클릭!" readonly="readonly">
							&nbsp;<img src="${pageContext.request.contextPath}/view/icon/calendar1.png">
						</div>
					</form>
				</div><!-- /.col-xs-7 -->
				<div class="col-xs-1">
					<div class="col-xs-3">
						<button type="button" id="cancel" class="btn btn-default" style="display: none">취소</button>
					</div>
				</div><!-- /.col-xs-1 -->
				<div class="col-xs-4">
					<button type="button" class="btn btn-danger" id="delete">삭제</button>
					&nbsp;
					<button type="button" class="btn btn-default">
						<a href="${pageContext.request.contextPath}/tour/myrouteForm.do?course=<%=course%>">초기화</a>
					</button>
					&nbsp;
					<button type="button" class="btn btn-success" id="save">완료</button><!-- insertMyRouteAction  -->
				</div><!-- /.col-xs-4 -->
			</div><!-- /.row -->
			
			
			<div class="day-group">
			 	<div class="day-item" id="day1">
					<h3 class="day-count">1일차</h3>
					<div class="icon-group">
						<div class="tour-icon-list">
							<!-- 	<div>관광지</div> -->
							<div class="add-icon">
								<button type="button" class="btn plane" data-target="#my80sizeCenterModal">
									<img src="${pageContext.request.contextPath}/view/icon/outline3.png">
								</button>
								<img class="arrowb" src="${pageContext.request.contextPath}/view/icon/arrow.png" />
							</div><!-- add-icon -->
						</div><!-- tour-icon-list --> 
					</div><!--icon-group -->
				</div><!--day-item -->
			</div><!--day-group -->
	</c:otherwise>
</c:choose>	




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


	
	
	
<div class="modal modal-center fade" id="my80sizeCenterModal" tabindex="-1" role="dialog" aria-labelledby="my80sizeCenterModalLabel">
	<div class="modal-dialog modal-80size modal-center" role="document">
		<div class="modal-content modal-80size">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">관광지 정보</h4>
			</div>
			<div class="modal-body" style="text-align: center;">
				<div id="modal-val"></div>
			</div>
			<!-- modal-body -->
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal" id="modaladd">추가</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" id="modalcancel">닫기</button>
			</div><!-- modal-content -->
		</div>
	</div>
</div>





		</div><!-- /.col-md-10 -->
	</div><!-- /.sub -->


</div><!-- /.main -->





<script>
function touritem(icon,code,name,description){
	this.icon =icon;
	this.code =code;
	this.name =name;
	this.description = description;
}

var tourlist = new Map();
var isSecond = false;
var targetDIV; //.tour-icon-list
var iconIndex = 0;
var base = "${pageContext.request.contextPath}";




//모달창안에 관광지 정보 넣어줌 
var planeEvent = function () {
    var index = $('#tour option:selected').val();
  	//temp.code 
  
    if(index != '선택') {
	    //var item = tourlist[index];
	  /*   alert(index); */
	    var item = tourlist.get(index);
	    //temp.code의 value -> new touritem(temp.icon, temp.code, temp.name, temp.description)
	    targetDIV = saveTarget($(this));//.plane
	    var innerText = '<h2>'+item.name+'</h2>' + '<div style="margin:90px;"><p>'+item.description+'</p></div>';
	    
	    for(i=0; i<2; i++){
	    		innerText += '<img src="${pageContext.request.contextPath}/view/images/tour/'+item.code+'_'+i+'.jpg" width="800px" height="700px"/><hr>'
		}
	    
	    $(".modal-body #modal-val").html(innerText);
	    $("#my80sizeCenterModal").modal();
	} else{
		alert("관광지를 선택해주세요.");
 		/* $("#modalcancel").click();  */
	}
};






function saveTarget(target){
	return target.parent('div').parent('div');  //target(.plane 버튼)의 부모 add-icon의 부모 tour-icon-list
}		


$(document).on("click", ".plane", planeEvent); //비행기 클릭시 발생하는 이벤트 
/* 팝업창에 내용넣기  */ 
/* $(".plane").click(planeEvent); */




function addDay(dayid){ //dayid -> inc  날짜 추가시  1일차 2일차 그룹 만들어줌 
	
 var dItem = 
	'<div class="day-item" id="day'+dayid+'">' +  /* day-item 몇일차  */
		'<h3 class="day-count">'+dayid+'일차</h3>' +    
	'<div class ="icon-group">'+ /* icon-group 아이콘 전체  */
	   '<div class="tour-icon-list">'+ /* tour-icon-list 한줄  */
		   '<div class="add-icon">'+ /* add-icon 비행모양 (화살표랑 한쌍)*/
			  	 '<button type="button" class="btn plane" data-target="#my80sizeCenterModal" >'+
		             '<img src="${pageContext.request.contextPath}/view/icon/outline3.png">'+	
		   		 '</button>'+
				    '<img class="arrowb" src="${pageContext.request.contextPath}/view/icon/arrow.png"/>'+
		  '</div>'+
		'</div>'+
	'</div>';

	
	$('.day-group').append(dItem); /* 총 전체에 추가  */
		
}

function removeDay(){ //마지막 자식부터 삭제
	$('.day-item').last().remove();
}



$(function(){   

/* 달력  */
 $('#datepicker1').datepicker({ //startdate input id="datepicker1"
  format: "yyyy-mm-dd",
  startView: 1,
  todayBtn:"linked",
  language: "kr",
  orientation: "top auto",
  keyboardNavigation: false,
  forceParse: false,
  autoclose: true,
  todayHighlight: true
  });
   
 $('#datepicker2').datepicker({ //enddate input id="datepicker2"
	  format: "yyyy-mm-dd",
	  startView: 1,
	  todayBtn:"linked",
	  language: "kr",
	  orientation: "top auto",
	  keyboardNavigation: false,
	  forceParse: false,
	  autoclose: true,
	  todayHighlight: true
      });
       
   $('#datepicker1').change(function(){
	 
	   if(isSecond){ //isSecond는 월이 아닌 일까지 선택했을때  //datepicker는 변화감지 두번 클릭되어야 실행
	   		var startDate = new Date($('#datepicker1').val());
	   		var endDate = new Date($('#datepicker2').val());
	   		
	   		var ONE_DAY = 1000 * 60 * 60 * 24; //하루 이런식으로 계산 

	   	    // Convert both dates to milliseconds 날짜 차이를 구하기 위해서 
	   	    var date1_ms = startDate.getTime() //시작일 시간 
	   	    var date2_ms = endDate.getTime() //마지막날 시간 

	   	    // Calculate the difference in milliseconds
	   	    var difference_ms = Math.abs(date1_ms - date2_ms)

	   	    //-------------------------------------------------------
	   	    
	   	    // 시작날짜 마지막날짜 차이를 days 넣음 
	   	    var days = Math.round(difference_ms/ONE_DAY);
	   		
	   		if(date1_ms > date2_ms){
	   			alert('여행 마지막일이 시작일 보다 빠를 수 없습니다.');
	   			$('#datepicker1').val('');
	   			$('#datepicker2').val('');
	   			isSecond = false;
	   			return;
	   		}
	   		
	   		if(days > 10){
	   			alert('10일을 넘길 수 없습니다.');
	   			$('#datepicker1').val('');
	   			$('#datepicker2').val('');
	   			isSecond = false;
	   			return;
	   		}
		
	   			 			 				   //<div class="day-item" id="day1">
	   		var dayChildSize = $('.day-group').find($('.day-item')).length;
	   			//수정모드에서 화면에 보여지는  day-group ->  day-item 길이
	   		   
	   		   
	   		   
	   		var inc = 0; //1일차 2일차 카운트 
	   							
	   		if(dayChildSize > (days+1)){ //화면에 보여지는 day-item 길이 > 새로 선택한 날짜 길이 
	   			var dif = dayChildSize - (days+1) ; //+1을 한 이유는 1-3일차 까지 선택하면 3일차까지 보여주기 위해 
	   		
			for(i=0; i< dif; i++){  //차이 만큼 삭제    			
    	   			removeDay();	    	   			
    	   		}
		
	   		} else if(dayChildSize < (days+1)){	//이미 추가되어있는 1일차 이후에 추가 
				var dif = (days+1) - dayChildSize;
		   			             
		   			inc = dayChildSize + 1 ;
	    	   		for(i=0; i<dif; i++){		
	    	   			addDay(inc);
	    	   			inc++;
	    	   		}
	    	   		
	   		} 
	   		
	   		isSecond = false;
	   		
   		}else{
   			
   			isSecond = true; //월을 선택했을때 true 빠짐 
   			
   		}
	  		 
	  		 
   });
   
   $('#datepicker2').change(function(){

	   		if(isSecond){ //일까지 선택했을때 
    	   		var startDate = new Date($('#datepicker1').val());
    	   		var endDate = new Date($('#datepicker2').val());
    	   		
    	   		var ONE_DAY = 1000 * 60 * 60 * 24;

    	   	    // Convert both dates to milliseconds
    	   	    var date1_ms = startDate.getTime()
    	   	    var date2_ms = endDate.getTime()

    	   	    // Calculate the difference in milliseconds
    	   	    var difference_ms = Math.abs(date1_ms - date2_ms)

    	   	    // 시작날짜 마지막날짜 차이
    	   	    var days = Math.round(difference_ms/ONE_DAY);
    	   		
    	   		if(date1_ms > date2_ms){
    	   			alert('여행 마지막일이 시작일 보다 빠를 수 없습니다.');
    	   			$('#datepicker1').val('');
    	   			$('#datepicker2').val('');
    	   			isSecond = false;
    	   			return;
    	   		}
    	   		
    	   		if(days > 10){
    	   			alert('10일을 넘길 수 없습니다.');
    	   			$('#datepicker1').val('');
    	   			$('#datepicker2').val('');
    	   			isSecond = false;
    	   			return;
    	   		}

    	   		var dayChildSize = $('.day-group').find($('.day-item')).length;
    	   		var inc = 0;
    	   		
    	   		if(dayChildSize > (days+1)){ 
    	   			
    	   			var dif = dayChildSize - (days+1) ;
    	   		
				for(i=0; i< dif; i++){    	   			
	    	   			removeDay();	   	   			
	    	   		}
    	   		} else if(dayChildSize < (days+1)){
				var dif = (days+1) - dayChildSize;
    	   			
    	   			inc = dayChildSize + 1 ; 
	    	   		for(i=0; i<dif; i++){		
	    	   			addDay(inc);
	    	   			inc++;
	    	   		}
    	   		} 
    	   		
    	   		isSecond = false;
    	   		
	   		}else{
	   			
	   			isSecond = true;
	   			
	   		}
	   		
		});

   
   function makeIconlist(){ /*myrouteDTO를 이용해서 수정모드에서 아이콘포함  1일차 2일차 ... 전체 생성  */
	    		
		var codekeylist= "<%=myroute!=null? myroute.getIcon_name() : null%>"; 
		codekeylist = codekeylist.split(":"); 
		// codekeylist = icon_name (TP0F000, TP1S000 : TP0F000 : TP1S000 ,,,TP0F002,,, )
				//0번째 인덱스는 TP0F000, TP1S000	// 1일차끝	2일차
							//삼항연산자 사용 
		
		var iconkeylist= "<%=myroute!=null? myroute.getIcon() : null%>"; 
		iconkeylist = iconkeylist.split(":"); 
		//Icon	airport, beach ,….     이미지(이름)
		
		
		var namekeylist= "<%=myroute!=null?myroute.getTourname():null%>";
		namekeylist = namekeylist.split(":"); 
		//TOURNAME	공항,카타비치,더 말리카 호텔:,,,,,

		
		var dItem = ''; //day-item
		for(i=0; i< (<%=days%>); i++){ //디비에서 몇일치 여행인지 저장해둔 일차수 
			var codekey = codekeylist[i].split(","); //TP0F000, TP1S000 : 에서 ,를 기준으로 또 잘라냄 
			var iconkey = iconkeylist[i].split(","); 
			var namekey = namekeylist[i].split(","); 
			 
			dItem = 
			'<div class="day-item" id="day'+(i+1)+'">' +   //i가 0부터 시작하니까 +1 
				'<h3 class="day-count">'+(i+1)+'일차</h3>' +   
			'<div class ="icon-group">'+
			   '<div class="tour-icon-list">'+
				   '<div class="add-icon">'+
					  	 '<button type="button" class="btn plane" data-target="#my80sizeCenterModal" >'+
				             '<img src="${pageContext.request.contextPath}/view/icon/outline3.png">'+	
				   		 '</button>'+
						 '<img class="arrowb" src="${pageContext.request.contextPath}/view/icon/arrow.png"/>'+
				   '</div>';
			
			for(x=0; x<codekey.length; x++){ 
				 // var item = tourlist.get(keylist[x]);   x->TP0F000   iconIndex->0부터 1씩 증가 
				  dItem += '<div class="tour-icon '+codekey[x]+'" id="icon'+iconIndex+'">';
					  dItem += '<img src="${pageContext.request.contextPath}/view/icon/'+ iconkey[x] +'.png" class="removeicon" width="180px" height="180px"/>';
					  dItem += '<div class="removename"><b>'+ namekey[x] +'</b></div>';
					  dItem += '<div class="checkdelete"style="display:none;"><input type="checkbox" id="check">삭제</div>';
				  dItem += '</div>';
				  dItem += '<img class="arrow" src="${pageContext.request.contextPath}/view/icon/arrow.png" width="20px" height="20px"/>';
				  iconIndex++; //아이콘 고유 인덱스 0부터 시작 
			}//1일차에 대한 그룹이 완성됌 
			dItem +='</div>'; //tour-icon-list 
			dItem +='</div>'; //icon-group
			dItem +='</div>'; //day-item

			$('.day-group').append(dItem);
		} // days for 문 (날짜 형성) //모든 날짜에 대한 그룹이 완성됌 
	}; //makeIconList 끝  
   
   function dataSetting(){ //수정모드에서 input type=text 데이터들 
		
		$("#coursename").val("<%=oldcoursename%>");
		$("#datepicker1").val("<%=oldstartdate%>");
		$("#datepicker2").val("<%=oldenddate%>");
		$("#nation").val("<%=oldnationname%>").attr("selected",true);
		$("#city").val("<%=oldcityname%>").attr("selected",true);
		$("#city").change(); //select 도시 선택시 ajax로 도시목록을 가져오는 이벤트 실행 
		$("#tour").val("선택").attr("selected",true); 	
		
		
		makeIconlist();
		
		
	};
   
   

/* 삭제버튼 */
$('#delete').click(function(){
	
 	
  
  	if( $('#delete').html() == '삭제' ) {

 		$('#delete').html('확인');
		$('#cancel').show();
 		$('.checkdelete').show();

	} else {
			
			var checkList = $('.tour-icon').find('#check');
			var isChecked = false;
		
			for(i = 0; i<checkList.length; i++){
				if(checkList.eq(i).is(':checked')){
				     var divId = checkList.eq(i).parent('div').parent('div').attr('id'); //div -> tour-icon의 id
				    $('#'+divId).next().remove(); //화살표지움 
					$('#'+divId).remove(); //tour-icon 지움 
					isChecked = true;
				}
			}//for 
			
  			if(isChecked == true){
			
				alert("성공적으로 삭제되었습니다!");
				$('#cancel').click();
  			}else {
  				alert("삭제시키고 싶은 관광지정보를 체크해주세요!")
  			}

	}//전체 else
});



//취소버튼 
$('#cancel').click(function(){
  $('#cancel').hide();
  $('#delete').html('삭제');
  $('.checkdelete').hide();
});
  
  
  
  

//나라 선택시 도시 관련 목록 뽑아내기 
$('#nation').change(function click() {
		

	$('#city').empty(); //초기화 
	$('#city').append('<option>선택</option>'); //선택 추가 
	if($('#nation option:selected').val() == "선택"){ //선택클릭시 
		return; 
	}
	
	
		$.ajax({

			type : "GET", 

			dataType : "json",

			url : "cityname.do?nationname="+ $('#nation option:selected').val() ,
										//CityNameAction에 보낼줄 선택된 국가명 	
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
							//위에 정보 UTF-8로 인코딩 시켜줌  
			success : function(data) { //data -> 디비에서 목록을 가져와 json형태로 받음 

				// data는 서버로부터 전송받은 결과(JSON)이므로 바로 사용한다
				for(i=0; i<data.listcity.length; i++){
					
					$('#city').append('<option>'+data.listcity[i]+'</option>')
					
				}
				
			
			},

			error : function(e) {

				alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');

			}

		});
	});


//관광지 목록 배열에 넣기  
  $('#city').change(function click() {
		
		$('#tour').empty();
		$('#tour').append('<option>선택</option>')
		if($('#city option:selected').val() == "선택"){
			/* alert('a'); */
			return; 
		}
		
		
			$.ajax({

				type : "GET",

				dataType : "json",

				url : "tourname.do?listtour="+ $('#city option:selected').val() ,
						
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",

				success : function(data) {

					// data는 obj       서버로부터 전송받은 결과(JSON)이므로 바로 사용한다
				
						tourlist.set("TP00000",new touritem('airport', 'TP00000', '공항', '도착 및 출발하는 공항 입니다.'));
						
						$('#tour').append('<option value="TP00000">공항</option>')
						
						for(i=0; i< data.listtour.length; i++){
									 //data의 키값(listtour)의 배열  
									 
							temp = jQuery.parseJSON(data.listtour[i].replace(/\n/g, "\\\\n").replace(/\r/g, "\\\\r").replace(/\t/g, "\\\\t"));
								//listtour(키)형태로 {"listtour":["{\"icon\":\"beach\"code\":\"TP0P008\", \"name\":\"까론해변\", \"description\":\"카론에는 푸,,,,},{},{}]}
							    //{}->하나가 [i]
								//jQuery는 parseJSON함수를 가지고 있음 
								//데이터가 문자열이므로 JSON형태는 {}를 찾아서 그안에 :기준으로 왼쪽 값을 키값으로 갖는다.
								//또한 키값은 문자열 형태를 가지므로 "   \"icon\" 
							    
								tourlist.set(temp.code, new touritem(temp.icon, temp.code, temp.name, temp.description));			 
								//tourlist[(i+1)] = new touritem(temp.icon, temp.code, temp.name, temp.description);
							    //tourlist는 Map  tourlist.set(키,값) -> 저장 
							    //temp.code (키) 를 통해서 
							    
								
								
							//$('#tour').append('<option value="'+(i+1)+'">'+temp.name+'</option>')
							$('#tour').append('<option value="'+temp.code+'">'+temp.name+'</option>');
						}
					
				
				},

				error : function(e) {

					alert('서버 연결 도중 에러가 났습니다. 다시 시도해 주십시오.');

				}

			});
			
			
		});

/* 팝업창 추가 버튼  ( 관광지 아이콘 추가 ) */  
$("#modaladd").click(function(){
	
	var index = $('#tour option:selected').val();
    //var item = tourlist[index];
    var item = tourlist.get(index);
    var children = $('.tour-icon-list').children();
                   
                    //class = tour-icon -> 공통 
                    //        icon + index -> tourlist[index]를 위한 마킹
                    //id =    icon + iconIndex (고유ID) -> 아이콘의 아이디 끼리는 서로 중복되지 않는다 PK
 	targetDIV.append('<div class="tour-icon '+item.code+'" id="icon'+iconIndex+'"></div>');

	$('#icon'+iconIndex).append('<img src="${pageContext.request.contextPath}/view/icon/'+item.icon+'.png" class="removeicon" width="180px" height="180px"/>')
    $('#icon'+iconIndex).append('<div class="removename"><b>'+item.name+'</b></div>')
    $('#icon'+iconIndex).append('<div class="checkdelete"><input type="checkbox" id="check">삭제</div>')
    $('.checkdelete').hide()
		targetDIV.append('<img class="arrow" src="${pageContext.request.contextPath}/view/icon/arrow.png" width="20px" height="20px"/>')				
		iconIndex++;
  	
});

$('#save').click(function(){
		
		var id = "<%=userid%>";
		
		if(id == ""){
			alert('로그인 후 이용해주세요!');
			return ;
		}
		
		$('#userid').val("<%=userid%>");
		$('#course').val("<%=course%>");
		$('#startdate').val($('#datepicker1').val());
		$('#enddate').val($('#datepicker2').val());
		$('#nationname').val($('#nation option:selected').val());
		$('#cityname').val($('#city option:selected').val());
		
		

		var iconlist = $('.day-group').find('.tour-icon-list');
		var iconname = '';
		var icon = '';
		var tourname = '';
		for (i = 0; i < iconlist.length; i++) {
			var iconChild = iconlist.eq(i).find('.tour-icon');
									//i는 일차 
			for (x = 0; x < iconChild.length; x++) {

				index = iconChild.eq(x).attr("class").substr(10); //tour-icon TP0FO01   T~1까지 잘라냄  // 0 -> tourlist[0]
			
				/* iconname += tourlist[index].code;
				icon += tourlist[index].icon;
				tourname += tourlist[index].name; */
				iconname += tourlist.get(index).code;
				icon += tourlist.get(index).icon;
				tourname += tourlist.get(index).name; 
				
				iconname += ',';
				icon += ',';
				tourname += ',';
				
			}
			iconname = iconname.slice(0,-1); //위의 for문이 끝날때마다 ,를 붙이므로 날짜가 바뀔때  ,: 콤마가 없어야 하므로 하나씩 지워줌 
			icon = icon.slice(0,-1);         //end가 음수이면 length + end로
			tourname = tourname.slice(0,-1); 
			
			iconname += ':';
			icon += ':';
			tourname += ':';
			
		}
		iconname = iconname.slice(0,-1); //최종적으로 마지막날에 :를 없애줌 
		icon = icon.slice(0,-1);
		tourname = tourname.slice(0,-1); 
		
		$('#iconname').val(iconname); //hidden타입으로 되어있는 input 태그 value설정 
		$('#icon').val(icon); //input 태그
		$('#tourname').val(tourname); //input 태그
		
		if($(this).html() == "수정완료"){	//$(this) -> 버튼  //num 게시물번호 //myroute 폼태그 id 
			 $('#num').val("<%=num%>");   
			 $('#myroute').attr('action', 'updateMyroute.do');
		}
		
		$('#myroute').submit();
	})

		
		var isEdit = "<%=isEdit%>";
	
		if(isEdit){
		
			dataSetting();
		}

	
		
		$('#reset').click(function(){
			var day = $(".day-group").children();
			for(i=0; i<day.length;i++){
				
				if(i == 0){
					$(".tour-icon-list").find(".add-icon").nextAll().remove();
				}else{
					day.eq(i).remove();
				}
			}
		}); 
		
	});
	


/* 	setTimeout(function(){
		
	},1000);	
 */
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