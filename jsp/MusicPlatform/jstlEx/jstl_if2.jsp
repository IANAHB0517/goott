<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<c:if test="${param.favSeason == '봄' }">
			<div style="color: yellow;">봄을 좋아 하네요</div>
		</c:if>

		<c:if test="${param.favSeason == '여름' }">
			<div style="color: red;">여름을 좋아 하네요</div>
		</c:if>
		
		<c:if test="${param.favSeason == '가을' }">
			<div style="color: brown;">가을을 좋아 하네요</div>
		</c:if>
		
		<c:if test="${param.favSeason == '겨울' }">
			<div style="color: blue;">겨울을 좋아 하네요</div>
		</c:if>
	</div>
</body>
</html>