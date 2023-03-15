<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page isErrorPage="true" %>
      <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ���� ������</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
	<h3>${requestScope.error.errorMsg }</h3>
	
	<div>
		<c:forEach var="errorInfo" items="${requestScope.error.stackTrace }">
			<div>${errorInfo }</div>
		</c:forEach>
	</div>
	
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>