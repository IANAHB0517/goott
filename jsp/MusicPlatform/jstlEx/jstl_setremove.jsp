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
	pageContext.setAttribute("msg", "hello"); // 페이지 영역에 msg 란 이름으로 hello 라는 문자열을 저장
	%>

	<div>${pageScope.msg }</div>

	<c:set var="msg2" value="배고파"></c:set>

	<div>${pageScope.msg2 }</div>
	<div>
		<c:out value="오늘 뭐 먹지?"></c:out>
		<c:out value="${msg2 }"></c:out>
	</div>
	<c:remove var="msg2" />
	<div>${pageScope.msg2 }</div>

</body>
</html>