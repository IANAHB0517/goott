<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언부</title>
</head>
<body>
	<%!
	String str = "안녕하세요!"; // 멤버 변수로 선언

	public int abs(int n) {// 멤버 메서드가 됨
		if (n < 0) {
			n = -n;
		}
		return n;
	}%>


	<%
	out.print("<div>" + str + "</div>");
	out.print("<div>" + abs(-3) + "</div>");
	%>
</body>
</html>