<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어로 페이지에 요청하기</title>
</head>
<body>
	<form action="elEx3_1.jsp">
		좋아하는 계절 :
		<select name="favSeason">
			<option>봄</option>
			<option>여름</option>
			<option>가을</option>
			<option>겨울</option>
		</select>
		<div>별명 : <input type="text" name="nickname"> </div>
		<div><input type="submit" value="전송"></div>
		
		
	</form>
</body>
</html>