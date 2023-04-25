<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
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
		$(".msg").hide();
	}
	

	function showMessage() {
		$(".msg").show();
		$(".card").hide();
		$(".memberPoint").hide();
	
	}

	function showPoint() {
		$(".msg").hide();
		$(".card").hide();
		$(".memberPoint").show();
		getMPList(1);
	
	}

	
	function getMPList(pageNo) {
		$(".msg").hide();
		$(".card").hide();
		
		
				
		$.ajax({
	          url: "getMemPoint?userId=" + '${sessionScope.loginMember.userId }' +'&pageNo='+pageNo, // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
	          type: "get", // 통신 방식 (GET, POST, PUT, DELETE)
	           // 서블릿에 전송할 데이터
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
	
	
	
	
	function messageSend() {
		let receiver = $("#receiver").val();
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
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container">
		<h4>마이 페이지</h4>
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active" href="#"
				onclick="showBasic();">기본정보</a>
				<div class="card" style="width: 400px">
					<img class="card-img-top"
						src="/resources/${sessionScope.loginMember.userImg }"
						alt="${sessionScope.loginMember.userId }">
					<div class="card-body">
						<h4 class="card-title">${sessionScope.loginMember.userId }</h4>
						<div class="card-text">
							<div class="userEmail">${sessionScope.loginMember.userEmail }</div>
							<div class="userMobile">${sessionScope.loginMember.userMobile }</div>
							<div class="userGender">${sessionScope.loginMember.userGender }</div>
							<div class="hobbies">${sessionScope.loginMember.hobbies }</div>
							<div class="userjob">${sessionScope.loginMember.job }</div>
							<div class="usermemo">${sessionScope.loginMember.memo }</div>
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
			 <li class="nav-item"><a class="nav-link" href="#"
				onclick="showMessage();">쪽지 주고받기</a>
				<div class="msg">
					<div class="memberMessage"></div>

					<div class="sendMsg">
						<div class="form-check">
							<label for="receiver">메세지 받을 유저 : </label> <input type="text"
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