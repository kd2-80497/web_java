<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
<jsp:useBean id="rb" class="com.sunbeam.bean.RegisterBean"/>
<jsp:setProperty property="*" name="rb"/>
	<% rb.saveUser();%>
	
	<% if(rb.getStatus()){
		out.println("User Registered successfully!");
	}else{
		out.println("User not added!");
	}
		%>
</body>
</html>