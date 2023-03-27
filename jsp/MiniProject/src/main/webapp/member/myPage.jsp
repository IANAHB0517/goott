<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script>
	$(function() {
	$(".movePage").click(function(){
		console.log($(this).html())
		movePaing('${memberInfo.userId}',$(this).html())
		
	})
	});
	function showBasic() {
		$(".card").show();
		$(".memberPoint").hide();
	}

	function showPoint(pageNo) {
		$(".card").hide();
		
		
				
		$.ajax({
	          url: "getMemPoint.mem", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
	          type: "get", // 통신 방식 (GET, POST, PUT, DELETE)
	          data: {
	            "userId": "${memberInfo.userId}",
	            "pageNo": pageNo
	          }, // 서블릿에 전송할 데이터
	          dataType: "json", // 수신받을 데이터 타입(MIME TYPE)
	          success: function (data) {
	            // 통신이 성공하면 수행할 함수(콜백 함수)
	            console.log(data);

	            if (data.status == "success") {
	             parsePoint(data);
	            	
	            } else if (data.status == "fail") {
	              alert("잠시 후, 다시 시도해 주세요");
	            }
	          },
	        });
		
		
	}
	
	function parsePoint(json){
		let output = "<table class='table table-striped'><thead><tr><th>적립 일시</th><th>적립 사유</th><th>적립금</th></tr></thead><tbody>";
		
		$.each(json.memberpoints, function(i, item){
			output += "<tr>";
			output += "<td>" + item.when + "</td>";
			output += "<td>" + item.why + "</td>";
			output += "<td>" + item.howmuch + "</td>";
			output += "</tr>";
			console.log(output);
		});
		output += "</tbody></table>";
		
		$(".memberPoint").html(output);
		
		let pagination = "<ul class='pagination'>";
		
		for (let i = json.startNumOfCurrentPagingBlock; i <= json.endNumOfCurrentPagingBlock; i++){
			pagination += "<li class='page-item'><a class='page-link' href='#' onclick='showPoint("+ i +");'> " + i +" </a></li>";
		}
		
		pagination += "</ul>";
		
		$(".mpPagination").html(pagination);
			
		$(".memberPoint").show();
	}
	
	
</script>
<style>
.memberPoint {
	display: none;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />

	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h4>마이 페이지</h4>
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active" href="#"
				onclick="showBasic();">기본정보</a>
				<div class="card" style="width: 400px">
					<img class="card-img-top"
						src="${contextPath}/${memberInfo.userImg}"
						alt="${memberInfo.userId}">
					<div class="card-body">
						<h4 class="card-title">${memberInfo.userId}</h4>
						<div class="card-text">
							<div class="userEmail">${memberInfo.userEmail }</div>
							<div class="userMobile">${memberInfo.userMobile }</div>
							<div class="userGender">${memberInfo.userGender }</div>
							<div class="hobbies">${memberInfo.hobbies }</div>
							<div class="userjob">${memberInfo.job }</div>
							<div class="usermemo">${memberInfo.memo }</div>
						</div>
					</div>
				</div></li>
				
			<li class="nav-item"><a class="nav-link" href="#"
				onclick="showPoint();">포인트 적립내역</a>
				
				<div class="memberPoint">
				</div>

				<div class="mpPagination">
				</div>

				</li>
			<!-- 
			<li class="nav-item"><a class="nav-link" href="#">내가 쓴 글</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Disabled</a>
			</li>
			 -->
		</ul>


	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>