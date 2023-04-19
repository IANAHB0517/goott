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
	function showBasic() {
		$(".msg").hide();
		$(".card").show();
	}

	function showMessage() {
		$(".card").hide();
		$(".msg").show();
	
	}
	
	function messageSend() {
		let receiver = $(".receiver").val();
		let messageText = $("#messageContent").val();
		
		$.ajax({
			url : "/message/send", // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
			type : "post", // 통신 방식 (GET, POST, PUT, DELETE)
			dataType : "text",  // 수신받을 데이터 타입
			headers : {
				"Content-Type" : "application/json",
				// PUT, DELETE, PATCH 등의 REST HTTP Method가 동작하지 않는 과거의 웹브라우저에서는 
				// "POST" 방식으로 동작하도록 한다
				"X-HTTP-Method-Override" : "POST"
			},
			data : JSON.stringify ({
				"receiver" : receiver,
				"messageText" : messageText
			}),
			success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
				if (data === 'success'){
					$(".receiver").val('');
					$("#messageContent").val('');
				}
			}, error : function(data) {
				console.log(data);
				
			}
		});
		
	}
</script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h1>index.jsp</h1>

		<div>${memberInfo }</div>

		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active" href="#"
				onclick="showBasic();">기본정보</a>
				<div class="card" style="margin: 20px auto;">
					<img class="card-img-top"
						src="${contextPath }/${memberInfo.userImg }"
						alt="${memberInfo.userId }">
					<div class="card-body">
						<h4 class="card-title">${memberInfo.userId }</h4>
						<p class="card-text">
						<div class="userEmail">${memberInfo.userEmail }</div>
						<div class="userMobile">${memberInfo.userMobile }</div>
						<div class="userGender">${memberInfo.userGender }</div>
						<div class="userhobbies">${memberInfo.hobbies }</div>
						<div class="job">${memberInfo.job }</div>
						<div class="memo">${memberInfo.memo }</div>
						</p>
					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link" href="#"
				onclick="showMessage();">쪽지 주고받기</a>
				<div class="msg">
					<div class="memberMessage"></div>

					<div class="sendMsg">
						<div class="form-check">
							<label for="receiver">보낼 메세지 : </label> <input type="text"
								id="receiver" class="form-control" /> <label
								for="messageContent">보낼 메세지 : </label>
							<textarea class="form-control" rows="2" id="messageContent"></textarea>

							<button type="button" class="btn btn-info"
								onclick="messageSend();">send</button>
							<button type="button" class="btn btn-danger">취소</button>

						</div>
					</div>
				</div></li>

		</ul>

	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>