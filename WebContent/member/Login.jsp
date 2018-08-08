<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
   <TITLE> 로그인 </TITLE>  
<!--      <link rel="stylesheet" href="../Euro-Travels-master/assets/css/bootstrap.min.css"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Colored Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--   bootstrap-css -->
<link rel="stylesheet" href="../css/bootstrap.css">
<!-- //bootstrap-css -->
<!--  Custom CSS  -->
<link href="../css/style.css?ver=2" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!--  font-awesome icons  -->
<link rel="stylesheet" href="../css/font.css" type="text/css"/>
<link href="../css/font-awesome.css" rel="stylesheet"> 
<!--  //font-awesome icons -->
   
   
   
<!--  <style>
#content{
   padding-top:100px;
   width:100%;
   text-align:center;
}
.logo{
   margin-bottom:30px;
   width:250px;
}
#loginInput{ 
   margin-bottom:10px;
   width:300px;
}
.btn-info{
   background-color:#33CC99;
   margin-top:15px;
   width:300px;
}
.inputStyle{
   height:60px;
}
</style>  -->
<style>
img{
   width:80px;
   height:60px;
}
.signup-heading {
    padding: 2em 0;
    background: rgb(111, 222, 209) !important;
    margin: 10 10;
}
.register {
      background: rgb(111, 222, 209) !important;
      border: solid 2px rgb(111, 222, 209) !important;
      font-size: 16px;
   }

</style>

   
</HEAD>
<BODY class="signup-body" onload="document.login.id.focus()">
   <div class="agile-signup">   
         
         <div class="content2">
            <div class="grids-heading gallery-heading signup-heading">
               <a title="HOME" href="mainpage.do"><img src="../view/icon/logowhite.png"><h2>WELLBROAD</h2></a>
            </div>
   
        <form name="login" method="post" action="LoginPro.do" >
               <input type="text" NAME="id" " value="아이디" placeholder="아이디" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">
               <input type="password"  NAME="password" value="비밀번호" placeholder="비밀번호" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">
               <input type="submit" class="register" value="Login" onclick="loginCheck()">
        </form>
      
               <INPUT TYPE="button" class="btn btn-link" value="아이디 찾기 " onclick="findIdCheck()" style="color:#739bda;text-decoration:none;">
               <INPUT TYPE="button" class="btn btn-link" value="비밀번호 찾기 " onclick="findPasswordCheck()" style="color:#739bda;text-decoration:none;">     
               <INPUT TYPE="submit" class="btn btn-link" value="회원가입" onclick="memberRegister()" style="color:#739bda;text-decoration:none;">
  
               <div class="clearfix"> </div>
                 
         </div>
 

   <!--  footer -->
         <div class="copyright">
            <p>© 2018 Colored . All Rights Reserved . Design by WELLBROAD</p>
         </div>
         <!--  //footer -->
 </div>
 <script src="../custom/js/memberCustom.js?ver=1"></script> 
 </BODY>
</HTML>