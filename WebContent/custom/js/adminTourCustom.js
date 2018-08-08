/**
 * 
 */
$(function(){
	hideErrorMasseage();
});

function hideErrorMasseage(){
	$('#error-nation').hide();
	$('#error-city').hide();
	$('#error-name').hide();
	$('#error-address').hide();
	$('#error-icon').hide();
}
function insertNationCheck(){
	var isCheck=true;
	if(document.tourForm.code_nation.value==""){
		$('#error-nation').show();
		isCheck=false;
	} else $('#error-nation').hide();
	
	if(document.tourForm.name.value==""){
		$('#error-name').show();
		isCheck=false;
	} else $('#error-name').hide();
	if(isCheck)	document.tourForm.submit();
}

function insertCityCheck(){
	var isCheck=true;
	if(document.tourForm.code_city.value==""){
		$('#error-city').show();
		isCheck=false;
	} else $('#error-city').hide();
	
	if(document.tourForm.name.value==""){
		$('#error-name').show();
		isCheck=false;
	} else $('#error-name').hide();
	if(isCheck)	document.tourForm.submit();
}

function insertTourCheck(){
	var isCheck=true;	
	if(document.tourForm.code_city.value=='choose'){
		$('#error-city').show();
		isCheck=false;
	} else $('#error-city').hide();
	
	if(document.tourForm.name.value==""){
		$('#error-name').show();
		isCheck=false;
	} else $('#error-name').hide();
	
	if(document.tourForm.address.value==""){
		$('#error-address').show();
		isCheck=false;
	} else $('#error-address').hide();	
	if(isCheck)	document.tourForm.submit();
}

/*중복코드체크 해주는 자바스크립트함수 선언*/
//국가
function checkCode_Nation(){
	if(document.tourForm.code_nation.value==""){
		alert("국가코드를 입력하세요.");
		document.tourForm.code_nation.focus();
		return;
	}
	else{
		url="adminTourNationCheck.do?code_nation="+document.tourForm.code_nation.value;
		window.open(url,"post","left=450,top=150,width=300,height=150");
	}
}
//도시
function checkCode_City(){
	if(document.tourForm.code_city.value==""){
		alert("도시코드를 입력하세요.");
		document.tourForm.code_city.focus();
		return;
	}
	else{
		url="adminTourCityCheck.do?code_city="+document.tourForm.code_city.value;
		window.open(url,"post","left=450,top=150,width=300,height=150");
	}
}