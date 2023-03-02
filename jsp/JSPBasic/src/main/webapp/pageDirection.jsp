<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시자</title>
</head>
<body>

	<!--  현재 날짜 출력하기 -->
	<%
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
	out.print(sdf.format(cal.getTime()));
	%>

</body>
</html>