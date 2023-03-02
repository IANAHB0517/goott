<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%> <!-- exception(예외처리 내장객체)를 사용하여 예외처리 가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외 처리 페이지(공통 예외 처리 할 때 사용)</title>
</head>
<body>
	<h1>에러 페이지</h1>
	<div>에러가 났네요.. 에러가 지속되면 나중에 방문하세요</div>
	<h4><%=exception.getMessage()%></h4>
	<div><%=exception.getStackTrace()%></div>
</body>
</html>