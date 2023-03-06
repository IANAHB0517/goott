<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL로 표현 가능한 연산자</title>
</head>
<body>
	<div>\${ 5 + 2 } : ${ 5 + 2 }</div>
	<div>\${ 5 / 2 } : ${ 5 / 2 }</div>
	<div>\${ 5 div 2 } : ${ 5 div 2 }</div>
	<div>\${ 5 mod 2 } : ${ 5 mod 2 }</div>
	<div>\${ 5 > 2 } : ${ 5 > 2 }</div>
	<div>\${ 5 gt 2 } : ${ 5 gt 2 }</div>
	<div>\${ 5 le 2 } : ${ 5 le 2 }</div>
	<div>\${ 5 gt 2 and 3 lt 4 } : ${ 5 gt 2 and 3 lt 4 }</div>
	<div>\${ 5 gt 2 and 3 gt 4 } : ${ 5 gt 2 and 3 gt 4 }</div>
	<div>\${ 5 gt 45 ? "참":"거짓" } : ${ 5 gt 45 ? "참":"거짓" }</div>
	<%
	String input = null;
	String full = null;
	%>
	<div>\${empty input } : ${empty input }</div>
	<div>\${empty full } : ${empty full }</div>
</body>
</html>