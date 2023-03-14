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
		당신은
		<c:choose>
			<c:when test="${param.favSeason == '봄' }">
				<span style="color: yellow">봄</span>
			</c:when>
			<c:when test="${param.favSeason == '여름' }">
				<span style="color: red">여름</span>
			</c:when>
			<c:when test="${param.favSeason == '가을' }">
				<span style="color: brown">가을</span>
			</c:when>
			<c:otherwise>
				<span style="color: blue">겨울</span>
			</c:otherwise>
		</c:choose>
		을 좋아 하시네요
	</div>
</body>
</html>