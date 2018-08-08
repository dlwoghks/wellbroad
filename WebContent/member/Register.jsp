<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
   var agreementCheck = false;
   var agreeCheck = false;
   var idcheck = $('#checkId').val()
   var idRules = /^[a-z0-9_-]{4,12}$/; //아이디 유효성 검사 4,12자 사이
   
   $(function(){
      $('.agree textarea').hide()
      $('#agreement').click(function(){
         agreementCheck = !agreementCheck
         if(agreementCheck==false) {
            $('.agree textarea').hide()
         }
         else {
            $('.agree textarea').show()
         }
      })
      $('#id').keyup(function(){
         var id = $('#id').val();
         if(id == ''){
            $('#id_signed').html('아이디를 입력하세요.').css('color','red');
            $('#id').focus();
         }else if( !idRules.test($.trim($("#id").val()))){
            $('#id_signed').html('아이디는  4~12자입니다.').css('color','red');
            $('#id').focus();         
         }else{
            $.ajax({ 
                     url:'IdCheck.do', //가져오고자하는 서버페이지 주소를 넣는다. 
                     type:'post', //데이터를 서버로 전송하게 된다. 
                     data:{ 
                            id: $('#id').val()  
                     } , 
                     success : function(data){ 
                                if(data){
                                	data = $.trim(data)
                                 $('#checkId').val("1")
                                 if(data=="1"){
                                	 $('#id_signed').html('사용가능한 아이디입니다').css('color','blue');
                                 }else{
                                	 $('#id_signed').html('중복된 아이디입니다').css('color','red');
                                 }
                                 //$('#id_signed').html(data);
							}
						}//success function
                  })//ajax
            }//id가 null이 아닐때 else
      })//중복체크
   })//$(function()):회원가입 [약관동의 , 아이디 중복체크]
      
</script>
<script>
   //*************비밀번호 실시간 유효성 검사
   
   var passwordRules =/^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
   $(function(){
      $('#password').keyup(function(){
         var password = $('#password').val()
         if(password==''){
            $('#password_signed').html('패스워드를 입력하십시오.').css('color','red');
         }
         else if(!passwordRules.test($.trim($("#password").val()))){
            $('#password_signed').html('패스워드는 8~16자 입니다.').css('color','red');
         }else{
            $('#password_signed').html('사용가능한 패스워드 입니다.').css('color','blue');
         }
      })
   })
   //비밀번호 확인   
   $(function(){
      $('#repassword').keyup(function(){
         if($('#repassword').val()!=$('#password').val()){
          $('#repassword_signed').html("패스워드가 일치하지 않습니다.").css('color','red');
         }else{
         $('#repassword_signed').html("패스워드가 일치합니다.").css('color','blue');
         }
      });
   });
</script>
<script>
   //*************************이메일 유효성 검사
   $(function(){
      var mailRules = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
      $('#mail').keyup(function(){
         var mail = $('#mail').val()
         if(password==''){
            $('#mail_signed').html('패스워드를 입력하십시오.').css('color','red');
         }
         else if(!mailRules.test($.trim($("#mail").val()))){
            $('#mail_signed').html('형식에 맞게 입력하십시오.').css('color','red');
         }else{
            $('#mail_signed').html('사용가능한 메일 입니다.').css('color','blue');
         }
      })
   })
</script>
<script>
   //*********************이름 유효성 검사
   $(function(){
      var nameRules = /^[가-힣]{2,4}$/; //이름 유효성 검사 2~4자 사이
      $('#name').keyup(function(){
         var name = $('#name').val()
         if(name == ''){
            $('#name_signed').html('이름을 입력하십시오.').css('color','red');
         }
         else if(!nameRules.test($.trim($("#name").val()))){
            $('#name_signed').html('형식에 맞게 입력하십시오.').css('color','red');
         }else{
            $('#name_signed').html('사용가능한 이름 입니다.').css('color','blue');
         }
      })
   })
</script>
<script>
$(function(){
   var phoneRules = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
   $('#phone').keyup(function(){
      var phone = $('#phone').val()
      if(phone == ''){
         $('#phone_signed').html('번호를 입력하십시오.').css('color','red');
      }
      else if(!phoneRules.test($.trim($("#phone").val()))){
         $('#phone_signed').html('번호 형식에 맞게 입력하십시오.').css('color','red');
      }else{
         $('#phone_signed').html('사용가능한 번호 입니다.').css('color','blue');
      }
   })
})
</script>
<style>

<style>

.signup-heading {
    padding: 1.5em 0;
    background:rgb(111, 222, 209);
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


table{
width:100%;
text-align:center;
}


.content2 input{
   margin:0px !important;
   width:50% !important;
   /* margin-left:140px; */
}

.content2 .text{
   text-align:right;
   height:49px;
   width:30% !important;
   /* margin-left:140px; */
}

#idbutton{
 text-align:left;
 
}


#select{ /* 성별 */
   
  width: 50%;

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

textarea{
   margin-top:60px;
}

.agree {
   
    width: 90%;
    text-align:center;
    margin:auto;
    margin-top:15px;   
} 

#agreeCheck{
   
   width:20px !important;
}

.inputtext{
   text-align:left;
   padding-left:50px;
}
#mailagree{
   width:15px !important;
}
span{
   margin-left:15px;
   font-size:15px;
}
</style>
</head>
<body class="signup-body">

<div class="agile-signup">   
         
         <div class="content2" style="width:50%;">
            <div class="grids-heading gallery-heading signup-heading">
               <a title="HOME" href="mainpage.do"><img src="../view/icon/logowhite.png"><h2>WELLBROAD</h2></a>
        </div>
         <form class="form-inline" name="regForm" method="post" action="RegisterPro.do">
                  <input type="hidden" id="checkId" name="checkId" value="0">
            
            <table>
                <tr>
                  <div class="subject"><h3>SIGN UP</h3></div>  
               </tr>
            <tr> 
                     <td class="text">아이디</td>
                     <td class="inputtext"> 
                      <input type="text" name="id" placeholder="아이디" id="id">
                      <span id="id_signed"></span>
                   </td>
                      
                   <!-- <span id="id_signed"></span> -->
              </tr>
            <tr> 
                      <td class="text">비밀번호</td>
                     <td class="inputtext">  <input type="password" name="password" id="password" 
                     placeholder="Password">
                     <span id="password_signed"></span>
                     </td>
                </tr>
                
                <tr> 
                      <td class="text">비밀번호 재확인</td>
                     <td class="inputtext"> 
                     <input type="password" id="repassword" name="repassword">
                           <span id="repassword_signed"></span>
                     </td>
                </tr>
                <tr> 
                     <td class="text">이름</td>
                    <td class="inputtext"> 
                        <input type="text" name="name" id="name" placeholder="이름을 입력하십시오.">
                       <span id="name_signed"></span>
                    </td>
                </tr>
                <tr> 
                     <td class="text">생년월일</td>
                    <td class="inputtext"> 
                        <input type="text" name="birth" class="form-control input-md" placeholder="ex)911211">
                    </td>
                </tr>
                   <tr> 
                        <td class="text">성별</td>                     
                     <td class="inputtext">
                        <div class="gendergrou">
                        <select name="gender" class="form-control" id="select">
                           <option>선택</option>
                           <option value="m">남자</option>
                           <option value="f">여자</option>
                        </select>
                     </div>
                  </td>
                </tr>
                <tr> 
                     <td class="text" >이메일</td>
                     <td class="inputtext"> <input type="text" name="mail" id="mail" size="27"
                        placeholder="ex)xxx@xxxx.xxx"> 
                     <span id="mail_signed"></span> 
                  </td>
                </tr>
                <tr> 
                     <td  class="text">메일수신동의</td>
                     <td class="inputtext"><input type="checkbox" name="mailagree" id="mailagree"></td>
                      
                </tr>
                <tr>  
                     <td class="text">전화번호</td>
                     <td class="inputtext">
                        <input type="text" name="phone" id="phone" placeholder="Phone">
                        <span id="phone_signed"></span> 
                     </td>
                  </tr>
                  </table>
            
            <div class="agree">
                  <jsp:include page="./agreement.jsp" />
                  <span>
                  <input type="checkbox" name="agreeCheck" id="agreeCheck">
                  WELLBROAD 이용약관(필수) <a id="agreement"> 보기 </a></span>
            
            </div>
            
            <input type="reset" class="register" value="Reset" id="reset">
            <input type="button" class="register" value="Sign Up" onclick="inputCheck()" id="check">
         </form>
      
      </div>
    <!-- footer -->   
   <div class="copyright">
      <p>© 2018 Colored . All Rights Reserved . Design by WELLBROAD</p>
   </div>
      
</div>
   <script src="../Euro-Travels-master/assets/js/jquery-1.11.2.min.js"></script>
   <script src="../Euro-Travels-master/assets/js/bootstrap.min.js"></script>
   <script src="../Euro-Travels-master/assets/js/owl.carousel.min.js"></script>
   <script src="../Euro-Travels-master/assets/js/contact.js"></script>
   <script src="../Euro-Travels-master/assets/js/jquery.flexslider.js"></script>
   <script src="../Euro-Travels-master/assets/js/script.js"></script>
   <script src="../custom/js/memberCustom.js"></script>

</body>
</html> 