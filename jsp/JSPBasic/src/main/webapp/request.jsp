<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 내장 객체</title>
</head>
<body>
	<div>context path : <%=request.getContextPath() %></div>
	<div>요청방식 : <%=request.getMethod() %></div>
	<div>요청한 URL : <%=request.getRequestURL() %> (도메인 + 컨테스트패스 + 요청된파일명)</div>
	<div>요청한 URL : <%=request.getRequestURI() %> (컨텍스트패스 + 요청된 파일명) </div>
	<div>요청한 쿼리스트링 : <%=request.getQueryString() %></div>
	<div>요청한 프로토콜 : <%=request.getProtocol() %></div>
	<div>요청한 클라이언트 IP주소 : <%=request.getLocalAddr() %> (네트워크 운영자(통신사)들이 막아 놓는다.)</div>
	
	<div>요청한 파일의 실제 경로 : <%=request.getRealPath(request.getRequestURI()) %></div>
	
	
</body>
</html>