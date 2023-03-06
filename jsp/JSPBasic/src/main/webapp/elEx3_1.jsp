<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어로 페이지에 요청받기</title>
</head>
<body>
	<div>
	<%=request.getParameter("nickname") %>님이 좋아하는 계절은
	<%=request.getParameter("favSeason") %>입니다
	</div>
	
	<hr/>
	
	<div>
	${param.nickname }님이 좋아하는 계절은
	${param.favSeason }입니다
	</div>
	
	
</body>
</html>