<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function(){
		$("#result a").each(function(){
			$(this).click(function(e){
				e.preventDefault();
				window.open($(this).attr("href"))
			})
		})
	})
</script>
<style>
a{
	display:block
}
</style>
</head>
<body>
	<div id="result">
		${text}
	</div>
</body>
</html>
