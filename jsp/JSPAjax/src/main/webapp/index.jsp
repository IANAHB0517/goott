<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<title>Employees CRUD with Ajax</title>
<script>
	$(document).ready(function() {

		getAllEmployees();

	});

	// 모든 직원 데이터를 얻어오는 함수
	function getAllEmployees() {
		$.ajax({
			url : "getAllEmployees.do", // 데이터가 송수신될 서버의 주소 (서블릿의 매핑주소 작성)
			type : "GET", // 통신 방식 (GET, POST, PUT, DELETE)
			dataType : "json", // 수신받을 데이터 타입(MIME TYPE)
			success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
			},
			error : function() {

			},
			complete : function() {

			}
		});

	}
</script>
</head>
<body>
	<div class="container">
		<h1>Employees CRUD with Ajax</h1>
	</div>
</body>
</html>