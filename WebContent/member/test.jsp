<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
boolean check = (boolean)request.getAttribute("check");
if(check==false){
    %>
    1
    <%
}else{%> 
    2
<%}%>