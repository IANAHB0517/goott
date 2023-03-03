<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>scope_2Page</h1>

	<%
	out.print("pageContext : " + pageContext.getAttribute("name") + "<br/> ");
	out.print("request : " + request.getAttribute("name") + "<br/> ");
	out.print("session : " + session.getAttribute("name") + "<br/> ");
	out.print("application : " + application.getAttribute("name") + "<br/> ");
	%>
	
	<a href="scope_3Page.jsp">세번째 페이지</a>
	
</body>
</html>