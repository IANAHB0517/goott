<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="inputProd.do" method="post">
		<div>
			상품명 : <input type="text" name="name">
		</div>
		<div>
			상품 개수 : <input type="number" name="qty">
		</div>
		<div>
			상품 가격 : <input type="number" name="price">
		</div>
		<div>
			색상 : <select name="color">
				<option>빨강</option>
				<option>주황</option>
				<option>노랑</option>
				<option>파랑</option>
			</select>
		</div>
		<input type="submit" value="입력">
	</form>
</body>
</html>