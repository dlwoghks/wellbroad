<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   request.setCharacterEncoding("utf-8");//한글처리
%>
<html>
<head>
<title>회원가입 확인</title>
<link rel="stylesheet"
	href="../Euro-Travels-master/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../Euro-Travels-master/assets/css/ionicons.min.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale = 1.0, maximum-scale=1.0, user-scalable=no" />
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js?ver=2"></script>





<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Colored Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link rel="stylesheet" href="../css/bootstrap.css">
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="../css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!-- font-awesome icons -->
<link rel="stylesheet" href="../css/font.css" type="text/css"/>
<link href="../css/font-awesome.css" rel="stylesheet"> 





</head>
<style>
.signup-heading {
    padding: 1.5em 0;
    background: rgb(111, 222, 209);
    margin: 5 5;
}

/* td{
	width:50%;
	height:5px;
}  */

img{
	width:80px;
	height:60px;
}

.subject{
	margin-top:30px;
	margin-bottom:60px;
}

.form-group{
	margin:0 auto;
}

.idbutton{
	margin-left:20px;
    margin-top:33px;
   
}

/* table{
margin-left:20px;

} */

table{
width:100%;
text-align:center;
}


.content2 input{
	margin:0px !important;
	width:75% !important;
	/* margin-left:140px; */
}

.content2 .text{
	text-align:right;
	height:49px;
	width:25% !important;
	/* margin-left:140px; */
}




.gendergrou{
	margin-left:145px;
    margin-top:25px;

}


input#check.register{
	margin-top:5px;
}


#agreeCheck{
	 margin-left:190px;
	 margin-top:40px;
	 margin-bottom:40px;

}

#reset{
	width:40% !important;
	margin-top:80px !important;
	background:rgb(195, 199, 199) !important;
	border: solid 2px rgb(195, 199, 199) !important;
}
#check{
	width:40% !important;
	margin-top:10px !important;
}

.register:hover {
    background-color: #FFFFF;
}

</style>
</head>
<body class="signup-body" onLoad="regForm.id.focus()">

<div class="agile-signup">	
			
	      <div class="content2" style="width:50%;">
				<div class="grids-heading gallery-heading signup-heading">
					<a title="HOME" href="mainpage.do"><img src="../view/icon/logowhite.png"><h2>WELLBROAD</h2></a>
				</div>
			<div class="subject"><h3>${member.name} 회원님이 작성하신 내용입니다. 확인해주세요</h3></div> 
			<form class="form-inline" name="regForm" method="post" action="MemberInsert.do">
				
				<table>
				 
					<tr> 
           				 <td class="text">아이디</td>
           				 <td style="width:90%;"> 
           					 <input type="text" name="id" class="form-control input-md"
           					 value="${member.id}" readonly="readonly">
		   				 </td>
        			  </tr>
					<tr> 
          			   <td class="text">패스워드</td>
         			   <td> <input type="password" name="password" class="form-control input-md"
            					value="${member.password }" readonly="readonly">
         			   </td>
          			</tr>
         		 <tr> 
         			   <td class="text">이름</td>
        				    <td> <input type="text" name="name" class="form-control input-md"
        					    value="${member.name }" readonly="readonly"> </td>
        		     </tr>
        			  <tr> 
        			        <td class="text">생년월일</td>
        				    <td> <input type="text" name="birth" class="form-control input-md"
          					  value="${member.birth }" readonly="readonly"> </td>
         		 </tr>
        			  <tr> 
        				    <td class="text">성별</td>
        				    <c:if test="${member.gender == 'm'}">
			             <td><input type="hidden" name="gender" class="form-control input-md"
			           			 value="${member.gender}">
			           			 
			            	<input type="text" name="showGender" class="form-control input-md"
			            			value="남자" readonly="readonly"></td>
			            </c:if>
			            <c:if test="${member.gender == 'f'}">
			            <td> <input type="hidden" name="gender" class="form-control input-md"
			            value="${member.gender}">
			            
			            	<input type="text" name="showGender" class="form-control input-md"
			            	value="여자" readonly="readonly"></td>
			            </c:if>
          		</tr>
          		<tr> 
			            <td class="text">이메일</td>
			            <td> <input type="text" name="mail" class="form-control input-md"
			            value="${member.mail }" readonly="readonly"> </td>
         		 </tr>
         		 <tr> 
			            <td class="text">메일 수신동의</td>
			            <c:if test="${member.mailagree == 'Y'}">
			            <td> <input type="text" name="mailagree" class="form-control input-md"
			            value="O" readonly="readonly"> </td>
			            </c:if>
			            <c:if test="${member.mailagree != 'Y'}">
			            <td> <input type="text" name="mailagree" class="form-control input-md"
			            value="X" readonly="readonly"> </td>
			            </c:if>
         		 </tr>
         		 <tr>  
			            <td class="text">전화번호</td>
			            <td> <input type="text" name="phone" class="form-control input-md"
			            value="${member.phone }" readonly="readonly"> </td>
			            
       		     </tr>
				
			</table>
				<input type="button" class="register" value="Reset" id="reset" onclick="history.back()">
			    <input type="submit" class="register" value="Sign Up" onclick="inputCheck()" id="check">
			</form>
		
				

		</div>
		
 <!--  footer  -->
	<div class="copyright">
		<p>© 2018 Colored . All Rights Reserved . Design by WELLBROAD</p>
	</div>
		
</div>
		
</body>
	
<%-- 

<table align="center" border="0" cellspacing="0" cellpadding="5" >
  <tr> 
    <td align="center" valign="middle"> 
      <table  cellspacing="0" cellpadding="2"  align="center">
          <tr align="center"> 
            <td colspan="3"><font color="#FFFFFF">
            <b><!-- member.getname() -->
              	${member.name} 회원님이 작성하신 내용입니다. 확인해주세요
            </b></font></td>
          </tr>
          <tr> 
            <td width="16%">아이디</td>
            <td width="57%"> 
            <input type="text" name="id" class="form-control input-md"
            value="${member.id}" readonly="readonly">
		    </td>
          </tr>
          <tr> 
            <td>패스워드</td>
            <td> <input type="password" name="password" class="form-control input-md"
            value="${member.password }" readonly="readonly">
         </td>
          </tr>
          <tr> 
            <td>이름</td>
            <td> <input type="text" name="name" class="form-control input-md"
            value="${member.name }" readonly="readonly"> </td>
          </tr>
          <tr> 
            <td>생년월일</td>
            <td> <input type="text" name="birth" class="form-control input-md"
            value="${member.birth }" readonly="readonly"> </td>
          </tr>
          <tr> 
            <td>성별</td>
            <c:if test="${member.gender == 'm'}">
            <td> <input type="hidden" name="gender" class="form-control input-md"
            value="${member.gender}">
            	<input type="text" name="showGender" class="form-control input-md"
            	value="남자" readonly="readonly"></td>
            </c:if>
            <c:if test="${member.gender == 'f'}">
            <td> <input type="hidden" name="gender" class="form-control input-md"
            value="${member.gender}">
            	<input type="text" name="showGender" class="form-control input-md"
            	value="여자" readonly="readonly"></td>
            </c:if>
          </tr>
          <tr> 
            <td>이메일</td>
            <td> <input type="text" name="mail" class="form-control input-md"
            value="${member.mail }" readonly="readonly"> </td>
          </tr>
          <tr> 
            <td>메일 수신동의</td>
            <c:if test="${member.mailagree == 'Y'}">
            <td> <input type="text" name="mailagree" class="form-control input-md"
            value="O" readonly="readonly"> </td>
            </c:if>
            <c:if test="${member.mailagree != 'Y'}">
            <td> <input type="text" name="mailagree" class="form-control input-md"
            value="X" readonly="readonly"> </td>
            </c:if>
          </tr>
          <tr>  
            <td>전화번호</td>
            <td> <input type="text" name="phone" class="form-control input-md"
            value="${member.phone }" readonly="readonly"> </td>
            
          </tr>
          <tr> 
            <td colspan="3" align="center"> 
             <input type="submit" value="확인완료" class="btn btn-info"> 
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
             <input type="button" value="다시쓰기" onclick="history.back()" class="btn btn-info"> 
            </td>
          </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</body> --%>
</html>
