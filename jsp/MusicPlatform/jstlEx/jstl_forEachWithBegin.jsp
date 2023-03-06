<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:forEach var="i" begin="0" end="9">
	<div>${i+1 }번째 div</div>
</c:forEach>

<c:forEach var="dan" begin="1" end="9">
	<c:forEach var="i" begin="1" end="9">
		<div>${dan } * ${i } = ${dan * i }</div>
		 &nbsp;&nbsp;&nbsp;
	</c:forEach>

</c:forEach>
</body>
</html>