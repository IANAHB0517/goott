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
	<c:set var="now" value="<%=new java.util.Date() %>"></c:set>
	
	<div>${now }</div>
	<div>time : <fmt:formatDate value="${now }" type="time"></fmt:formatDate></div>
	<div>date : <fmt:formatDate value="${now }" type="date"></fmt:formatDate></div>
	<div>both : <fmt:formatDate value="${now }" type="both"></fmt:formatDate></div>
	
	<hr>
	
	<div>time : <fmt:formatDate value="${now }" type="time" timeStyle="full"></fmt:formatDate></div>
	<div>time : <fmt:formatDate value="${now }" type="time" timeStyle="medium"></fmt:formatDate></div>
	<div>time : <fmt:formatDate value="${now }" type="time" timeStyle="short"></fmt:formatDate></div>
	
	<hr>
	
	<div>date : <fmt:formatDate value="${now }" type="time" timeStyle="full"></fmt:formatDate></div>
	<div>date : <fmt:formatDate value="${now }" type="time" timeStyle="long"></fmt:formatDate></div>
	<div>date : <fmt:formatDate value="${now }" type="time" timeStyle="medium"></fmt:formatDate></div>
	
	<hr>
	
	<div>pattern : <fmt:formatDate value="${now }"  type="date" pattern="yyyy-mm-dd hh:MM:ss"></fmt:formatDate> </div>
	
</body>
</html>