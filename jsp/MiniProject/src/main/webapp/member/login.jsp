<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
	<h1>login.jsp</h1>
	
	<form method="post" action="login.mem"> 
		<div style="margin-top: 20px; text-align: center;">
             <button type="submit" class="btn btn-success">로그인</button>
        </div>
	</form>
	
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
	
</body>
</html>