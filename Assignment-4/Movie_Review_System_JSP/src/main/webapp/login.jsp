<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<jsp:useBean id="lb" class="com.sunbeam.bean.LoginBean"/>
	<jsp:setProperty property="email" param="email" name="lb"/>
	<jsp:setProperty property="password" param="password" name="lb"/>
	
	<% lb.authenticate(); %>
	Login Status : <jsp:getProperty property="status" name="lb" /> <br><br>
	
	<% 
	if(lb.getStatus()==true){
		out.println("Welcome User!");
	}else out.println("Invalid User!");
	%>
	
</body>
</html>