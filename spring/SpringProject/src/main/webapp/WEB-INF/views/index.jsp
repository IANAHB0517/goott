<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<title>마이 페이지</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
	
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
	<h1>index.jsp</h1>
	
	<div>
		${sessionScope.loginMember }
	</div>
	
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>