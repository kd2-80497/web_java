<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form method="post" action="login.jsp">
		Email<input type="text" name="email"></input><br>
		Password<input type="password" name="password"></input><br>
		<input type="submit" value="Login">
		<a href="register.jsp" >Sign Up</a>
	</form>
</body>
</html>