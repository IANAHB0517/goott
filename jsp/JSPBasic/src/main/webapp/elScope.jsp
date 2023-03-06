<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL Scope.jsp</h1>
	<h5>바인딩 시킨 영역에 접근하여 출력</h5>
	<div>num1 : ${requestScope.num1 }</div>
	<div>num2 : ${requestScope.num2 }</div>
	<div>result : ${requestScope.result }</div> <!-- null --> 
	<div>result : ${sessionScope.result }</div>
	
	
	<div>member : ${requestScope.member }</div> <!--toString이 호출됨  --> 
</body>
</html>