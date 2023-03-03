<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
request.setCharacterEncoding("utf-8");
%>
	<jsp:forward page="actionTag_forwardParam_1.jsp">
		<jsp:param value="hkd" name="userId" />
		<jsp:param value="16" name="age" />
		<jsp:param value="홍길동" name="name" />
	</jsp:forward>
</body>
</html>