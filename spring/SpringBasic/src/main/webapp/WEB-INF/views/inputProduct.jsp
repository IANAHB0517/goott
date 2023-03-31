<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- action을 지정해주지 않으면 이 페이지를 요청한 uri로 넘어감 -->
	<form action="savePrd" method="post">
		<input type="text" name="productNo" />
		<input type="text" name="productName" />
		<input type="text" name="price" />
		
		<input type="submit" value="저장" />
	</form>
</body>
</html>