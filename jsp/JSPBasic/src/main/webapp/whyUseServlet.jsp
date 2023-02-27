<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>왜 Servlet을 사용하는가?</title>
<style type="text/css">
h1 {
	background-color: blue;
}
</style>
<script type="text/javascript">
	function hello() {
		alert("!");
	}
</script>
</head>
<body>
	<h1 onclick="hello();">왜 Servlet을 사용하는가?</h1>
	<!-- 변수에 임의의 값을 저장 한 후, 그 값이 홀수이면
	파란색 글씨로 "홀수입니다" 라고 출력하고,
	짝수이면 빨간색 글씨로 "짝수 입니다" 라고 출력해 보자 -->
	<%
	int num = 3;
	out.print(num);
	// 서버에서 실행해서 결과만 보여주기 때문에
	//웹 브라우져에 3 이라는 결과만 출력된다.


	if (num % 2 == 0) { // 짝수
	%>
	<div style="color: red;">짝수입니다</div>
	<%
	} else { // //홀수
	%>
	<div style="color: blue;">홀수입니다</div>
	<%
	}
	%>
</body>
</html>