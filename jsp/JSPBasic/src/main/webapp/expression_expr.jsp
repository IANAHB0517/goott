<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현식</title>
</head>
<body>
	<%!String str = "안녕하세요!"; // 멤버 변수로 선언

	public int abs(int n) {// 멤버 메서드가 됨
		if (n < 0) {
			n = -n;
		}
		return n;
	}%>

	<!--
		1.문서가 클래스로 변환됨
		2. 자바로 컴파일 됨
		3. 컴파일된 자바 언어 위로 html 태그가 씌워짐
  -->


	<div><%=str%></div>
	<h4><%=abs(-5)%></h4>


</body>
</html>