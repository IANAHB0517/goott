<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>data life cycle</title>
</head>
<body>
	<h1>scope_1Page</h1>

	<%
	// 각 내장 객체의 영역에 정보 바인딩
	pageContext.setAttribute("name", "page data");
	request.setAttribute("name", "request data");
	session.setAttribute("name", "session data");
	application.setAttribute("name", "application data");
	%>

	<%
	// 각 내장 객체의 영역에 바인딩 된 정보 확인
	out.print("pageContext : " + pageContext.getAttribute("name") + "<br/> ");
	out.print("request : " + request.getAttribute("name")  + "<br/> ");
	out.print("session : " + session.getAttribute("name")  + "<br/> ");
	out.print("application : " + application.getAttribute("name")  + "<br/> ");
	
	request.getRequestDispatcher("scope_2Page.jsp").forward(request, response); // 데이터 포워딩
	%>

</body>
</html>

