<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 첫 JSP</title>
</head>
<body>

	<h1>Hello, JSP</h1>
	
	
	<%
	out.print("로그인한 유저 : " + (String)session.getAttribute("loginMember"));
	%>

</body>
</html>