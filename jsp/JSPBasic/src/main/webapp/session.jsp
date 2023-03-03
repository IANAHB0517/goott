<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session</title>
</head>
<body>
	<div>세션 id : <%=session.getId() %></div>
	<div>세션 유효 시간 : <%=session.getMaxInactiveInterval() %></div>
	<div>세션 유효 시간 : <%=session.setMaxInactiveInterval(18) %></div>
</body>
</html>