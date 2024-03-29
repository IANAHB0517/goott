<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<script>
	$(function() {
			let user = '${sessionScope.loginMember.userId}';
		
			if (user != '') {
				updateMsgCnt(user);
			}
		
		
		setInterval(function() {
			if (user != '') {
				updateMsgCnt(user);
			}
			console.log("메세지 호출");
		}, 1000 * 60 * 1);
	});

	function updateMsgCnt(user) {
		$.ajax({
			url : "/message/updateMsgCnt/" + user, // 데이터가 송수신될 서버의 주소(서블릿의 매핑주소작성)
			type : "post", // 통신 방식 (GET, POST, PUT, DELETE)
			dataType : "text", // 수신받을 데이터 타입
			success : function(data) { // 통신이 성공하면 수행할 함수(콜백 함수)
				console.log(data);
	$(".badge").html(data);
			},
			error : function(data) {
				console.log(data);
			}
		});
	}
</script>

<style>
.uploadMemberImg {
	width: 40px;
	border-radius: 20px;
}
</style>
</head>
<body>
	<!-- 어떤 페이지에서 어떤 탭을 누르더라도 이동이 원할 하도록 해주는 것이 contextPath 이다 잘 사용해야 한다 -->
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">Home</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#mynavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="mynavbar">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link" href="#">회원가입</a></li>
					<li class="nav-item"><a class="nav-link" href="/board/listAll">게시판</a></li>
					<c:choose>
						<c:when test="${sessionScope.loginMember != null }">

							<li class="nav-item">
								<button type="button" class="btn btn-primary">
									Messages <span class="badge bg-danger"></span>
								</button> <a class="nav-link"
								href="/member/myPage?userId=${sessionScope.loginMember}"> <img
									class="uploadMemberImg"
									src="/resources/${sessionScope.loginMember.userImg}" />
									${sessionScope.loginMember.userId}
							</a>
							</li>

							<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>

						</c:otherwise>
					</c:choose>


				</ul>
				<form class="d-flex">
					<input class="form-control me-2" type="text" placeholder="Search">
					<button class="btn btn-primary" type="button">Search</button>
				</form>
			</div>
		</div>
	</nav>

</body>
</html>