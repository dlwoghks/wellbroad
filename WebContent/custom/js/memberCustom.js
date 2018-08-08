function loginCheck(){ //Login.jsp
	//document.폼객체명.입력양식객체명.속성명
	if(document.login.id.value==""){
		alert("아이디를 입력 하십시오.");
		document.login.id.focus();
		return false; //return false;
	}
	if(document.login.password.value==""){
		alert("비밀번호를 입력 하십시오.");
		document.login.password.focus();
		return false;	
	}
	//document.폼객체명.submit()->전송하라
	document.login.submit();
}
function findIdCheck(){
//	if(document.findId.name.value==""){
//		alert("이름을 입력 하십시오.");
//		document.findId.name.focus();
//		return; //return false;
//	}
//	if(document.findId.birth.value==""){
//		alert("생년월일을 입력 하십시오.");
//		document.findId.birth.focus();
//		return;	
//	}
	//document.폼객체명.submit()->전송하라
	document.location="FindId.do"
}
function findPasswordCheck(){
//	if(document.findPassword.id.value==""){
//		alert("아이디를 입력 하십시오.");
//		document.findPassword.name.focus();
//		return; //return false;
//	}
//	if(document.findPassword.birth.value==""){
//		alert("생년월일을 입력 하십시오.");
//		document.findPassword.birth.focus();
//		return;	
//	}
	//document.폼객체명.submit()->전송하라
	document.location="FindPassword.do";
}
/* Login.jsp
 * <INPUT TYPE="button" value="회원가입" onclick="memberReg()">
 * <INPUT TYPE="button" value="회원가입" 
 *    onclick="document.location='Register.jsp' ">
 */
function memberRegister(){  //버튼클릭->특정페이지로 이동
	//response.sendRedirect(~)->자바
	//document.location.href="경로포함해서 이동할 페이지명" (자바스크립트)
	//document.location="Register.jsp"
//	document.location="agreement.jsp" //Register.jsp로 이동(동의하면)

	document.location="Register.do" //Register.jsp로 이동(동의하면)

}
//회원가입 버튼을 클릭시 호출되는 함수
function inputCheck(){
	if(document.regForm.id.value==""){
		alert("아이디를 입력해 하십시오.");
		document.regForm.id.focus();
		return false;//return false;
	}
	if(document.regForm.id.value==""){
		alert("아이디를 입력해 하십시오.");
		document.regForm.id.focus();
		return false;//return false;
	}
	if(document.regForm.id.value.length < 4 || document.regForm.id.value.length > 12){
		alert("아이디는 4~12자 입니다.");
		document.regForm.id.focus();
		return false;//return false;
	}
	
	if(document.regForm.password.value==""){
		alert("비밀번호를 입력 하십시오.");
		document.regForm.password.focus();
		return false;
	}
	if(document.regForm.password.value.length<8 || document.regForm.password.value.length>16){
		alert("비밀번호는 8~16자 입니다.");
		document.regForm.password.focus();
		return false;
	}
	
	if(document.regForm.repassword.value==""){
		alert("비밀번호 재입력을 확인 하십시오");
		document.regForm.repassword.focus();
		return false;
	}
	
	
	if(document.regForm.name.value==""){
		alert("이름을 입력 하십시오.");
		document.regForm.name.focus();
		return false;
	}
	var name = document.regForm.name.value;
	var nameRules = /^[가-힣]{2,4}$/;
	if(document.regForm.name.value==""){
		alert("이름을 입력 하십시오.");
		document.regForm.name.focus();
		return false;
	}
	if(document.regForm.name.value.length < 2 || document.regForm.name.value.length > 4 ){
		alert("이름을 형식에 맞게 입력 하십시오.");
		document.regForm.name.focus();
		return false;
	}
	if(nameRules.test(name)==false){
		alert("이름을 형식에 맞게 입력 하십시오.");
		document.regForm.name.focus();
		return false;
	}
	if(document.regForm.birth.value==""){
		alert("생년월일을 입력 하십시오.");
		document.regForm.birth.focus();
		return false;
	}
	if(document.regForm.birth.value.length<6){
		alert("생년월일은 6자입니다.");
		document.regForm.birth.focus();
		return false;
	}
	if(document.regForm.gender.value==""){
		alert("성별을 선택 하십시오.");
		document.regForm.gender.focus();
		return false;
	}
	if(document.regForm.mail.value==""){
		alert("메일을 입력하십시오.");
		document.regForm.mail.focus();
		return false;
	}
	if(document.regForm.mail.value==""){
		alert("메일을 입력하십시오.");
		document.regForm.mail.focus();
		return false;
	}
	var mailRules = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	var mail = document.regForm.mail.value
	if(mailRules.test(mail)==false){
		alert("메일을 형식에 맞게 입력하십시오.");
		document.regForm.mail.focus();
		return false;
	}
	if(document.regForm.phone.value==""){
		alert("연락처를 입력하십시오.");
		document.regForm.phone.focus();
		return false;
	}
	if(document.regForm.phone.value.length < 11){
		alert("연락처는 11자 입니다.");
		document.regForm.phone.focus();
		return false;
	}

	if(document.regForm.password.value != document.regForm.repassword.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.repassword.focus();
		return false;
	}
	document.regForm.mailagree.value = document.regForm.mailagree ? 'Y' : 'N';
	if(!document.regForm.agreeCheck.checked){
		alert("약관에 동의하지 않으셨습니다.");
		document.regForm.agreeCheck.focus();
		return false;
	}
	if(document.regForm.checkId.value=="0"){
		alert("아이디 중복체크 필수.")
		document.regForm.idcheck.focus()
		return false
	}
	document.regForm.submit(); //action="RegisterProc.jsp"
}

//중복ID체크 해주는 자바스크립트함수 선언
function idCheck(id){ //id.value
   if(id==""){
	   alert("아이디를  입력하십시오.")
	   document.regForm.id.focus()//document.폼객체명.입력양식.함수명(~)
   }else{ //IdCheck.jsp?매개변수명=전달할값->IdCheck.jsp->checkId(id)
	   //IdCheck.jsp를 요청->매개변수를 전달하는 이유->DB에 접근=>id확인
	   url="IdCheck.do?id="+id
	   window.open(url,"post","left=450,top=150,width=300,height=150")
   }
}



