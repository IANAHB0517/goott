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
	$(function() {
		getpopBoardTop3();
	});
	
	function getpopBoardTop3(){
		// ajax로 인기글 3개를 가져와서 .popTop3 에 바인딩
		
		  $.ajax({
	          url: "popTop3.bo", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
	          type: "get", // 통신 방식 (GET, POST, PUT, DELETE)
	          dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
	          success: function (data) {
	            // 통신이 성공하면 수행할 함수(콜백 함수)
	            console.log(data);

	            if (data.status == "success") {
	            
	            } else if (data.status == "fail") {
	              
	            }
	          },
	        });
		
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
	<h1>index.jsp</h1>
	
	<div class="popTop3">
		인기글 3개
	</div>
	
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>