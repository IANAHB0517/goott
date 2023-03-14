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
	<%
	String[] heros = { "캡틴아메리카", "헐크", "아이언맨", "토르" };
	pageContext.setAttribute("heros", heros);
	%>
	<table border="1">
		<tr>
			<th>index</th>
			<th>count</th>
			<th>hero</th>
		</tr>
		<c:forEach var="hero" items="${heros }" varStatus="status">
			<tr>
				<c:choose>
					<c:when test="${status.index % 2 == 0 }">
						<td style="background-color: yellow;">${status.index }</td>
					</c:when>
					<c:otherwise>
						<td>${status.index }</td>
					</c:otherwise>

				</c:choose>


				<td>${status.count }</td>
				<td>${hero }</td>
			</tr>
		</c:forEach>
	</table>
	<hr />
	<table border="1">
		<tr>
			<th>index</th>
			<th>count</th>
			<th>hero</th>
		</tr>
		<c:forEach var="hero" items="${heros }" varStatus="status">
			<c:choose>
				<c:when test="${status.first }">
					<tr style="color: red">
				</c:when>

				<c:when test="${status.last }">
					<tr style="color: green">
				</c:when>

				<c:otherwise>
					<tr>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${status.index % 2 == 0 }">
					<td style="background-color: yellow;">${status.index }</td>
				</c:when>

				<c:otherwise>

					<td>${status.index }</td>
				</c:otherwise>

			</c:choose>

			<td>${status.count }</td>
			<td>${hero }</td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>